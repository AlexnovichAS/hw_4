Домашняя работа 4
#Запуск проекта

- добавить библиотеку: com.google.code.gson <br> 
в файл pom.xml добавить код:
  ```
  <properties>
  <maven.compiler.source>11</maven.compiler.source>
  <maven.compiler.target>11</maven.compiler.target>
  <gson.version>2.8.9</gson.version>
  </properties>

    <dependencies>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>${gson.version}</version>
        </dependency>
    </dependencies>
  ```