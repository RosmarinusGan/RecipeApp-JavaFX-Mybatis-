# E-CookBook

Environment:

> <ol>
> <li>OS: Windows</li>
> <li>SDK: Java 15+</li>
> <li>IDE: IntelliJ IDEA</li>
> <li>Database: MySQL Server 8+</li>
> <li>Maven build tool(https://maven.apache.org)</li>
> </ol> 
You need to initialize Maven for downloading dependencies.

In Maven projects, IntelliJ IDEA usually automatically detects and downloads the required dependencies. If your project is set to download dependencies manually, click the "Reimport" button in the Maven tool window to have Maven reimport and download dependencies.


Database configuration:
> <ol>
> <li>Modify src->main->resources->jdbc.properties file to adapt to your local database</li>
> Including driver url username userpassword
> <li>connect to your local MySQL</li>
> <li>use the .sql file in the package to import the database</li>
> </ol>
