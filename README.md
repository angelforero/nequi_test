# App prueba Nequi
La aplicación se encuentra desplegada en AWS desde terraform creando la base de datos en RDS y la aplicación desplegandola en BeanStalk. Se deja configurado que el cambiente pueda ser levantado en cualquien cuenta de AWS sin tener que hacer creación ni de base de datos o tablas manualmente. Ya que todo lo realiza la solución. La url base que puede ser probada se presenta a continuación y puede ser reemplazada en todos los endpoint abajo presentados

```
URL_BASE=http://myapp-nequi.eba-6ctyyccn.us-east-2.elasticbeanstalk.com
```



## Ejecutar en entorno local

Lo primero es clonar el proyecto almacenado en un repositorio [GitHub](https://github.com/angelforero/nequi_test).

Para ejecutar la aplicación en un entorno local es importante primero configurar el entorno bajo JAVA 15 o superior. Tambien es importante contar previamente con la base de datos, Para ello dirijase al archivo application.properties donde podra configurar el nombre de la base de datos, el usuario, contraseña y host de la base de datos.

```
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/nequi_demo
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres
```

## Esquema de la base de datos

La aplicación contiene los esquemas necesarios para poder correr, de manera que al iniciar la aplicación un Bean inicializador crea las tablas insumo de la apliación. Los esquemas mencionados se encuntran en el archivo schema_db.sql 

## Terraform para AWS

Se deja la carpeta **terraform** con todos los archivos para aprovicionar la infraestructura necesaria en AWS. Solo es necesario actualizar las credenciales de la cuenta en la cual se quiere desplegar. Desde alli se aproviciona la base de datos en postgres y se despliega la aplicación de backend desarrollada.

```
provider "aws" {
  region = var.region
  access_key = "valor_access"
  secret_key = "valor_secret"
}
```

### Comandos Terraform
Una vez actualizadas las credenciales ejecute los siguientes comando y tenga presente la contraseña digitada durante el proceso, ya que será la clave usada por la aplicación en el archivo antes mencionado.

```
$ terraform init
```

```
$ terraform plan
```

```
$ terraform apply
```

---

## Contenedores Docker
Asimismo se pueden ver los archivos docketfile y docker-compose.yml donde se deja configurada unicamente la aplicación para ser deplegada en un ambiente de contenedores de manera local.

---

## Endpoints configurados

Se crearon un total de 12 endpoint que permiten interactual con el servicio y modificar la información acorde con los puntos solitados.

Consulta de información:

* Consultar todas las franquicias

```
curl --location '{{url_base}}/nequi_test/franquicias'
```
* Consultar todas las sucursales

```
curl --location '{{url_base}}/nequi_test/sucursales'
```
* Consultar todas las productos

```
curl --location '{{url_base}}/nequi_test/productos'
```
* Consultar todas las sucursales por franquicia

```
curl --location '{{url_base}}/nequi_test/all2/{franquicia}'
```
* Consultar los productos maximos por sucursal para una franquicia ingresada por el usuario

```
curl --location '{{url_base}}/nequi_test/productos/maximos/{franquicia}'
```

Creación de información:
* Crear una nueva franquicia

```
curl --location '{{url_base}}/nequi_test/franquicia' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_franquicia_postman-post"
}'
```
* Crear una nueva sucursal

```
curl --location '{{url_base}}/nequi_test/{franquicia}/sucursal' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_sucursal_postman-post"
}'
```
* Crear un nuev producto

```
curl --location '{{url_base}}/nequi_test/{sucursal}/producto' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_producto_postman2-post",
    "cantidad":211
}'
```
Para modificar alguno de los atributos existentes:

* Modifica una franquicia

```
curl --location --request PUT '{{url_base}}/nequi_test/franquicia/{franquicia}' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_franquicia_postman-updated-post"
}'
```
* Modifica una sucursal

```
curl --location --request PUT '{{url_base}}/nequi_test/sucursal/{sucursal}' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_sucursal_postman-updated-post",
    "id_franquicia": 3
}'
```
* Modifica un producto

```
curl --location --request PUT '{{url_base}}/nequi_test/producto/{producto}' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_producto_postman2-post",
    "cantidad": 99,
    "id_sucursal": 1
}'
```

Eliminar un producto de una sucursal:
* Eliminar una franquicia

```
curl --location --request DELETE '{{url_base}}/nequi_test/producto/{franquicia}'
```

---

## Observaciones importantes

Se parte de la logíca de negocio para modelar las tablas y construir el servicio. Una franquicia puede tener muchas sucursales. Asimismo, cada una de las sucursales maneja su inventario separado por lo que los productos son independientes para cada sucursal. Tambien es posible que una sucursal cambie de franquicia.

La aplicación fue desarrollada bajo principios de programación reactiva y arquitectura hexagonal facilitando el mantenimiento y desacople de componentes.


