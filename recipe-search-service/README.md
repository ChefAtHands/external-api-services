### Step 1: Create the Repository Structure

1. **Create a new directory for the repository** (e.g., `external-api-services`).
2. **Inside this directory**, create two subdirectories: `recipe-search-service` and `recipe-detail-service`.

### Step 2: Create the Parent `pom.xml`

In the root of your new repository (`external-api-services`), create a `pom.xml` file that connects to the parent `pom.xml` of your main project.

```xml
<!-- filepath: /Users/anejtomplak/School/PRPO/ChefAtHands/external-api-services/pom.xml -->
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
</project>
```

### Step 3: Create the `pom.xml` for Each Service

#### Recipe Search Service

Create a `pom.xml` file in the `recipe-search-service` directory:

```xml
<!-- filepath: /Users/anejtomplak/School/PRPO/ChefAtHands/external-api-services/recipe-search-service/pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>com.chefathands</groupId>
    <artifactId>external-api-services</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <relativePath>../pom.xml</relativePath>
  </parent>

  <artifactId>recipe-search-service</artifactId>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

#### Recipe Detail Service

Create a `pom.xml` file in the `recipe-detail-service` directory:

```xml
<!-- filepath: /Users/anejtomplak/School/PRPO/ChefAtHands/external-api-services/recipe-detail-service/pom.xml -->
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <parent>
    <groupId>com.chefathands</groupId>
    <artifactId>external-api-services</artifactId>
    <version>0.1.0-SNAPSHOT</version>
    <relativePath>../pom.xml</relativePath>
  </parent>

  <artifactId>recipe-detail-service</artifactId>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-test</artifactId>
      <scope>test</scope>
    </dependency>
  </dependencies>
</project>
```

### Step 4: Implement the Services

You can now implement the logic for the Recipe Search Service and Recipe Detail Service in their respective directories. This will typically involve creating controllers, services, and repositories as needed.

### Step 5: Build the Project

To build the entire project, navigate to the `external-api-services` directory and run:

```bash
mvn clean install
```

This will compile and package both services, allowing them to be run independently or deployed as needed.

### Conclusion

You now have a new repository structure for external API services that includes a Recipe Search Service and a Recipe Detail Service, both connected to the parent `pom.xml`. You can expand on this by adding the necessary business logic, database connections, and any other features you require.