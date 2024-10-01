# Copyright (c) HashiCorp, Inc.
# SPDX-License-Identifier: MPL-2.0

provider "aws" {
  region = var.region
  access_key = ""
  secret_key = ""
}

resource "aws_s3_bucket" "s3_bucket_myapp" {
  bucket = "myapp-nequi"
  #acl = "private"
}

resource "aws_s3_object" "s3_bucket_object_myapp" {
  bucket = aws_s3_bucket.s3_bucket_myapp.id
  key = "beanstalk/myapp"
  source = "../target/demo-nequi-0.0.1-SNAPSHOT.jar"
}

resource "aws_elastic_beanstalk_application" "beanstalk_myapp" {
  name = "nequi-app"
  description = "The description of my application"
}

resource "aws_elastic_beanstalk_application_version" "beanstalk_myapp_version" {
  application = aws_elastic_beanstalk_application.beanstalk_myapp.name
  bucket = aws_s3_bucket.s3_bucket_myapp.id
  key = aws_s3_object.s3_bucket_object_myapp.id
  name = "nequi_app-1.0.0"
}


resource "aws_iam_role" "elastic_beanstalk_ec2_role" {
  name = "aws-elasticbeanstalk-ec2-role"

  assume_role_policy = jsonencode({
    Version = "2012-10-17",
    Statement = [{
      Action    = "sts:AssumeRole",
      Effect    = "Allow",
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
  })
}

# Adjuntar la política AWS administrada para Elastic Beanstalk EC2 Role
resource "aws_iam_role_policy_attachment" "elastic_beanstalk_ec2_role_policy" {
  role       = aws_iam_role.elastic_beanstalk_ec2_role.name
  policy_arn = "arn:aws:iam::aws:policy/AWSElasticBeanstalkWebTier"
}

# Crear un perfil de instancia y asociar el rol
resource "aws_iam_instance_profile" "elastic_beanstalk_instance_profile" {
  name = "aws-elasticbeanstalk-ec2-role"
  role = aws_iam_role.elastic_beanstalk_ec2_role.name
}


resource "aws_elastic_beanstalk_environment" "beanstalk_myapp_env" {
  name = "myapp-nequi"
  application = aws_elastic_beanstalk_application.beanstalk_myapp.name
  solution_stack_name = "64bit Amazon Linux 2023 v4.3.1 running Corretto 17"
  version_label = aws_elastic_beanstalk_application_version.beanstalk_myapp_version.name
 
  setting {
    name = "SERVER_PORT"
    namespace = "aws:elasticbeanstalk:application:environment"
    value = "5000"
  }

  setting {
    namespace = "aws:elasticbeanstalk:environment:process:default"
    name      = "Port"
    value     = "8080"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "IamInstanceProfile"
    value     = "aws-elasticbeanstalk-ec2-role"
  }

  setting {
    namespace = "aws:autoscaling:launchconfiguration"
    name      = "instanceType"
    value     = "t2.micro"
  }
}

data "aws_availability_zones" "available" {}

module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "5.12.0"

  name                 = "nequidemo"
  cidr                 = "10.0.0.0/16"
  azs                  = data.aws_availability_zones.available.names
  public_subnets       = ["10.0.4.0/24", "10.0.5.0/24", "10.0.6.0/24"]
  enable_dns_hostnames = true
  enable_dns_support   = true
}

resource "aws_db_subnet_group" "nequidemo" {
  name       = "nequidemo"
  subnet_ids = module.vpc.public_subnets

  tags = {
    Name = "nequidemo"
  }
}

resource "aws_security_group" "rds" {
  name   = "nequidemo_rds"
  vpc_id = module.vpc.vpc_id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "nequidemo_rds"
  }
}

resource "aws_db_parameter_group" "nequidemo" {
  name   = "nequidemo"
  family = "postgres16"

  parameter {
    name  = "log_connections"
    value = "1"
  }
}

resource "aws_db_instance" "nequidemo" {
  identifier             = "nequidemo"
  instance_class         = "db.t4g.micro"
  allocated_storage      = 5
  engine                 = "postgres"
  engine_version         = "16.1"
  db_name                = "nequi_demo"
  username               = "postgres"
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.nequidemo.name
  vpc_security_group_ids = [aws_security_group.rds.id]
  parameter_group_name   = aws_db_parameter_group.nequidemo.name
  publicly_accessible    = true
  skip_final_snapshot    = true
}
