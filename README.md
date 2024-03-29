# AndroidTestCeiba
Ejercicio de ingreso android para Ceiba.

## Lenguaje
 * Kotlin

## Arquitectura
  * MVVM
  
## Tecnologias implementadas
  * Dagger-Hilt
  * Retrofit
  * Room
  * Binding
  * Navigation
  * Lineamientos gitflow
  * Unit Test
  
## Alcance
  El alcance de la actual App abarcó como anteriormente se especificó diferentes tecnologias y conocimientos, entre ellos se cuentan los casos de uso,
  la segregación de interfaces necesaria para ordenar y permitir la implementación de las pruebas unitarias, se trabajó con binding para tener control total 
  sobre los id de los distintos layout, se gestionó el flujo de vistas mediante navigation, se delegaron las tareas de obtener las distintas instancias como del repositorio, 
  la base de datos local, el api y el viewmodel a la inyección de depencias mediante dagger-hilt, se listaron los datos a traves de un recyclerview genérico, 
  se generó el flujo de datos desde la BD mediante flow y los observadores se implementaron con livedata, para la conversión de datos se creo un DTO que 
  aunque se dejó en su mas mínima expresión funcionalmente entrega los datos de manera correcta, para el parseo del archivo JSon se utilizaron 
  las librerias propias de retrofit y okhttp, se generaron las diferentes validaciones para las respuestas de la red, por lo que aunque se pierda la conexión a internet 
  la aplicación no falla y en tiempo real se puede conectar y desconectar y la misma app guia al usuario para que obtenga los datos de manera exitosa, se implementaron las pruebas   unitarias para los daos, tanto para la obtención del listado de usuarios como del listado de los post, tambien se implementaron las pruebas para los casos de uso, implementando   un repositorio de pruba.
  
## Tareas faltantes
  * No se terminaron de implementar las pruebas unitarias para la inyección de dependencias y el viewmodel.
  * Implementación de los Dto, Esta no es una tarea faltante pero si con una implementación muy básica.
