# Copyright (c) HashiCorp, Inc.
# SPDX-License-Identifier: MPL-2.0

variable "region" {
  default     = "us-east-2"
  description = "AWS region"
}

variable "db_password" {
  description = "RDS root user password"
  sensitive   = true
}

variable "postgres_database_name" {
  default     = "nequi_demo"
  description = "postgres_database_name"
}

variable "postgres_username" {
  default     = "postgres"
  description = "postgres_username"
}
