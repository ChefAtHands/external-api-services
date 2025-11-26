<!-- filepath: /path/to/external-api-services/pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.chefathands</groupId>
  <artifactId>external-api-services</artifactId>
  <version>0.1.0-SNAPSHOT</version>
  <packaging>pom</packaging>

  <modules>
    <module>recipe-search-service</module>
    <module>recipe-detail-service</module>
  </modules>

  <parent>
    <groupId>com.chefathands</groupId>
    <artifactId>chefathands-parent</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <relativePath>../pom.xml</relativePath> <!-- Adjust the path to your parent pom.xml -->
  </parent>
</project>