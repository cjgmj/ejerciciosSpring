# ejerciciosSpring

### Para desplegar la aplicación desde terminal con el tomcat embebido
1. Realizamos un `mvn install` al proyecto para compilarlo.
2. Accedemos desde el terminal a la raíz del proyecto.
3. Ejecutamos `java -jar` al JAR generado por maven, dentro de la carpeta `target`.

### Para desplegar la aplicación en un Tomcat externo
1. Cambiar el packaging a `war`.
2. Añadir la dependencia de tomcat con scope `provided`.
3. Quitar la dependencia de `devtools`.
4. Si el proyecto es JSP, quitar la dependencia `tomcat-embed-jasper`.
5. Al cambiar de `jar` a `war`, realizar un `Update Maven Project`.
6. Incluir en la URL de AJAX el nombre del proyecto, obteniendo el valor desde JavaScript.
7. Ejecutar `mvn clean` para limpiar el contenido de la carpeta `target`.
8. Crear en el paquete raíz la clase `ServletInitializer`. Sobreescribir el método `configure` introduciendo como `sources` en el `builder` la clase anotada con `@SpringBootApplication`.
9. Si no encuentra la clase, realizar un `Update Maven Project`.
10. Realizamos un `mvn install` al proyecto para compilarlo.
11. Iniciar el servido lanzando `.\startup.bat` dentro de la carpeta `bin` del servidor.
12. Copiar el war generado por maven dentro de la carpeta `target` a la carpeta `webapps` del servidor. Para ello, nos situamos dentro de la carpeta y ejecutamos `cp ruta_war` donde `ruta_war` es la dirección dentro de la carpeta `target`.
13. Para acceder a la aplicación, abrir en el navegador la ruta `localhost:8080/nombre_proyecto` donde `nombre_proyecto` es el nombre del archivo `war`.
14. Para cambiar el nombre del proyecto ejecutar `Rename-Item nombre_inicio nombre_final` donde `nombre_inicio` es el nombre del `war` generado y `nombre_final` el nombre que se quiere poner, se tiene que dejar la extensión `war`.
15. Para eliminar una aplicación desplegada en el servidor lanzar `rm nombre_proyecto` dentro de la carpeta `webapps`, también hay que eliminar el fichero `war`.
16. Para parar el servidor lanzar `.\shutdown.bat` dentro de la carpeta `bin` del servidor.