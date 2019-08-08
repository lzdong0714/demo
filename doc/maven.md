###  maven 命令添加自己的JAR

```sh
mvn install:install-file -Dfile=aspose-cells-jdk16-8.5.2.jar -DgroupId=com.aspose.cells -DartifactId=aspose-cells-jdk16 -Dversion=8.5.2 -Dpackaging=jar

mvn install:install-file -Dfile=rules-core-1.1.0.jar -DgroupId=com.hntycloud -DartifactId=rules-core -Dversion=1.1.0 -Dpackaging=jar

mvn install:install-file -Dfile=redis-distributed-lock-starter-1.2.0.jar -DgroupId=com.hntycloud -DartifactId=redis-distributed-lock-starter -Dversion=1.2.0 -Dpackaging=jar
```



