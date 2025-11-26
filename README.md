### Step 1: Create the Repository Structure

1. **Create a new directory for the repository**:
   ```
   ChefAtHands/external-api-services
   ```

2. **Inside the `external-api-services` directory, create the following structure**:
   ```
   external-api-services/
   ├── pom.xml
   ├── recipe-search-service/
   │   ├── pom.xml
   │   └── src/
   │       ├── main/
   │       │   ├── java/
   │       │   │   └── com/
   │       │   │       └── chefathands/
   │       │   │           └── recipesearch/
   │       │   │               ├── RecipeSearchServiceApplication.java
   │       │   │               └── controller/
   │       │   └── resources/
   │       │       └── application.properties
   │       └── test/
   │           └── java/
   │               └── com/
   │                   └── chefathands/
   │                       └── recipesearch/
   │                           └── RecipeSearchServiceApplicationTests.java
   └── recipe-detail-service/
       ├── pom.xml
       └── src/
           ├── main/
           │   ├── java/
           │   │   └── com/
           │   │       └── chefathands/
           │   │           └── recipedetail/
           │   │               ├── RecipeDetailServiceApplication.java
           │   │               └── controller/
           │   └── resources/
           │       └── application.properties
           └── test/
               └── java/
                   └── com/
                       └── chefathands/
                           └── recipedetail/
                               └── RecipeDetailServiceApplicationTests.java
   ```

### Step 2: Create the Parent `pom.xml`

In the `external-api-services/pom.xml`, add the following content:

```xml
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

### Step 3: Create the Recipe Search Service `pom.xml`

In the `recipe-search-service/pom.xml`, add the following content:

```xml
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

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

### Step 4: Create the Recipe Detail Service `pom.xml`

In the `recipe-detail-service/pom.xml`, add the following content:

```xml
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

  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

### Step 5: Create Basic Application Classes

1. **Recipe Search Service Application**:
   In `recipe-search-service/src/main/java/com/chefathands/recipesearch/RecipeSearchServiceApplication.java`:

   ```java
   package com.chefathands.recipesearch;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class RecipeSearchServiceApplication {
       public static void main(String[] args) {
           SpringApplication.run(RecipeSearchServiceApplication.class, args);
       }
   }
   ```

2. **Recipe Detail Service Application**:
   In `recipe-detail-service/src/main/java/com/chefathands/recipedetail/RecipeDetailServiceApplication.java`:

   ```java
   package com.chefathands.recipedetail;

   import org.springframework.boot.SpringApplication;
   import org.springframework.boot.autoconfigure.SpringBootApplication;

   @SpringBootApplication
   public class RecipeDetailServiceApplication {
       public static void main(String[] args) {
           SpringApplication.run(RecipeDetailServiceApplication.class, args);
       }
   }
   ```

### Step 6: Add Controllers

You can create controllers for both services to handle incoming requests. For example:

- **Recipe Search Controller** in `recipe-search-service/src/main/java/com/chefathands/recipesearch/controller/RecipeSearchController.java`:

   ```java
   package com.chefathands.recipesearch.controller;

   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.RequestParam;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   public class RecipeSearchController {
       @GetMapping("/api/recipes/search")
       public String searchRecipes(@RequestParam String query) {
           // Implement search logic here
           return "Searching for recipes with query: " + query;
       }
   }
   ```

- **Recipe Detail Controller** in `recipe-detail-service/src/main/java/com/chefathands/recipedetail/controller/RecipeDetailController.java`:

   ```java
   package com.chefathands.recipedetail.controller;

   import org.springframework.web.bind.annotation.GetMapping;
   import org.springframework.web.bind.annotation.PathVariable;
   import org.springframework.web.bind.annotation.RestController;

   @RestController
   public class RecipeDetailController {
       @GetMapping("/api/recipes/{id}")
       public String getRecipeDetail(@PathVariable String id) {
           // Implement detail retrieval logic here
           return "Details for recipe ID: " + id;
       }
   }
   ```

### Step 7: Build and Run

1. Navigate to the `external-api-services` directory.
2. Run the following command to build the project:
   ```bash
   mvn clean install
   ```
3. You can run each service independently by navigating to their respective directories and using:
   ```bash
   mvn spring-boot:run
   ```

### Conclusion

You now have a new repository for external API services with a Recipe Search Service and a Recipe Detail Service. Each service has its own `pom.xml` that connects to the parent `pom.xml`, and basic controllers are set up to handle requests. You can expand the functionality as needed!