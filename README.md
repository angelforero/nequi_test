# Ejecutar en entorno local

Lo primero es clonar el proyecto almacenado en un repositorio [GitHub](https://github.com/angelforero/nequi_test).

Para ejecutar la aplicación en un entorno local es importante primero configurar el entorno bajo JAVA 15 o superior. Tambien es importante contar previamente con la base de datos, Para ello dirijase al archivo application.properties donde podra configurar el nombre de la base de datos, el usuario, contraseña y host de la base de datos.

```
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/nequi_demo
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres
```

## Esquema de la base de datos

La aplicación contiene los esquemas necesarios para poder correr, de manera que al iniciar la aplicación un Bean inicializador crea las tablas insumo de la apliación. Los esquemas mencionados se encuntran en el archivo schema_db.sql 


## Endpoints configurados

Se crearon un total de 12 endpoint que permiten interactual con el servicio y modificar la información acorde con los puntos solitados.

Consulta de información:

* Consultar todas las franquicias

```
curl --location 'http://localhost:8084/nequi_test/franquicias'
```
* Consultar todas las sucursales

```
curl --location 'http://localhost:8084/nequi_test/sucursales'
```
* Consultar todas las productos

```
curl --location 'http://localhost:8084/nequi_test/productos'
```
* Consultar todas las sucursales por franquicia

```
curl --location 'http://localhost:8084/nequi_test/all2/3'
```
* Consultar los productos maximos por sucursal para una franquicia ingresada por el usuario

```
curl --location 'http://localhost:8084/nequi_test/productos/maximos/3'
```

Creación de información:
* Crear una nueva franquicia

```
curl --location 'http://localhost:8084/nequi_test/franquicia' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_franquicia_postman-post"
}'
```
* Crear una nueva sucursal

```
curl --location 'http://localhost:8084/nequi_test/4/sucursal' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_sucursal_postman-post"
}'
```
* Crear un nuev producto

```
curl --location 'http://localhost:8084/nequi_test/4/producto' \
--header 'Content-Type: application/json' \
--data '{
    "nombre":"name_producto_postman2-post",
    "cantidad":211
}'
```
Para modificar alguno de los atributos existentes:

* Modifica una franquicia

```
curl --location --request PUT 'http://localhost:8084/nequi_test/franquicia/3' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_franquicia_postman-updated-post"
}'
```
* Modifica una sucursal

```
curl --location --request PUT 'http://localhost:8084/nequi_test/sucursal/1' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_sucursal_postman-updated-post",
    "id_franquicia": 3
}'
```
* Modifica un producto

```
curl --location --request PUT 'http://localhost:8084/nequi_test/producto/3' \
--header 'Content-Type: application/json' \
--data '{
    "nombre": "name_producto_postman2-post",
    "cantidad": 99,
    "id_sucursal": 1
}'
```

Eliminar un producto de una sucursal:
* Crear una nueva franquicia

```
curl --location --request DELETE 'http://localhost:8084/nequi_test/producto/8'
```


## Observaciones importantes

Se parte de la logíca de negocio para modelar las tablas y construir el servicio. Una franquicia puede tener muchas sucursales. Asimismo, cada una de las sucursales maneja su inventario separado por lo que los productos son independientes para cada sucursal. Tambien es posible que una sucursal cambie de franquicia.


