# How to use Pongo #

  * Download the latest version from the [Downloads page](http://code.google.com/p/pongo/downloads/list)
  * Extract the zip file you downloaded. Inside it you will find 3 jars:
    * `pongo.jar`
    * `pongo-runtime.jar`
    * `pongo-runtime-and-dependencies.jar`
  * To generate code from your .emf file use the following command: `java -jar pongo.jar full-path-of-your.emf`
  * If everything goes well, you'll find the generated code next to your .emf file
  * The next step is to add one of the two jars below to the classpath of your project
    * **(Recommended)** `pongo-runtime-and-dependencies.jar` contains all the extras you will need to run your generated code (including the MongoDB driver)
    * `pongo-runtime.jar` contains the Pongo runtime classes but not their dependencies. If you choose this jar file, you will need to add the [MongoDB driver](http://docs.mongodb.org/ecosystem/drivers/java/) and the [Apache Commons Collections](http://commons.apache.org/collections/) jars to your classpath separately