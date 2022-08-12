# Desafío técnico en Selenium 
#### Este es un proyecto maven que está desarrollado en Selenium Java, WebDriverManager para las instancias del navegador, además se utiliza el patrón de diseño Page Object Model. TestNG para la parte de unit test y configuración del xml. También se trabaja con Extent report para mostrar evidencia y Apache POI para gestionar Data driven.
## Requisitos del sistema para abrir  y ejecutar solución
- Java
- IDE (IntelliJ o Eclipse por ejemplo) *Opcional
- Maven
### Estructura del proyecto:
- DesafioFalabella: Solución
  - datafiles: Contiene un archivo excel en donde escribimos y leemos la data
  - reports: Contiene el documento HTML con el reporte generado
  - reports/screenshots: Contiene las capturas de pantallas
  - src/config: contiene el archivo .properties
  - src/main/java/: Contiene los paquetes del Page Object Model con sus respectivas clases
  - src/test/java/: Contiene las pruebas separadas entre paquetes
  
### Ejecución del proyecto
- Através del IDE: Instalar dependencias del POM.xml y ejecutar con click derecho sobre el archivo testng.xml
- Atraés de consola: Posicionado en la raíz del proyecto, ejecutar: ```mvn clean test``` o ```mvn clean install``` en caso de ser necesario

### Reportería
- En la carpeta reports que se encuentra en la raíz del proyecto, abrir con el navegador el archivo 'AutomationReport.html' tras haber ejecutado los tests

- Si desea probar con el navegador edge, especificar en archivo testng.xml en la linea de < parameter name="browserName" value="chrome"/ > especificar valor edge y volver a ejecutar
