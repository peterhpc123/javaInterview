## Extension Framework
This is an extension framework based on Junit5 Jupiter Extension Model. 
Also, this is an example case for test assembler-maven-plugin when i worked in XXX(company). 
I just realized the overall functions without some details interacting with XXX internal service.

## Basic Usage
Add Junit 5 dependency. If you use springboot, you can easily add this in your pom.xml:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>

<dependency>
    <groupId>com.example</groupId>
    <artifactId>AssemblerFramework</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
After import the above dependencies, you can use it in your unit test like the following snippts:

```java
import org.springframework.beans.factory.annotation.Autowired;

@Autowired
AssemblerTestCPDLCSpringBoot assembler;
public void testA(){
    assembler.testwithCMSAndGDS();//this is common test logic, you can define some assertions
}
```

