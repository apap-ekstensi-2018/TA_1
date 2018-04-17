# Kelompok Besar 2
## KELOMPOK 1 - SIASISTEN

## Members:
- Andesta Putra (1706106633)
- Julio Muhammad Pranadano (1706106785)
- Muhammad Fajar Pamungkas (1706106835)
- Satrio Nugroho (1706106993)
- Winda Dumaria Simanjuntak (1706107075)

## Documentation
Our Application Programming Interface is available at [API page.](API.md)

## Styleguide
Our styleguide to write this application available at [styleguide.](STYLEGUIDE.md)

## Installing
Install `java` at least version `1.8.0`, follow [this instruction](https://www.java.com/en/download/help/download_options.xml) to install Java 1.8 JDK.

After Java installed, Follow this instructions
```bash
$ git clone https://github.com/apap-ekstensi-2018/TA_1 tugas_akhir
$ cd tugas_akhir
$ ./mvnw install
$ ./mvnw build
$ ./mwnw spring-boot:run
```
Go to `http://localhost:8080/` to [start this application.](http://localhost:8080/)

## Deployment
Once `spring-boot-maven-plugin` has been included in your `pom.xml`, it automatically tries to rewrite archives to make them executable by using the `spring-boot:repackage` goal.
You should configure your project to build a jar or war (as appropriate) by using the usual packaging element, as shown in the following example:
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<!-- ... -->
	<packaging>jar</packaging>
	<!-- ... -->
</project>
```
[See more](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#build-tool-plugins-maven-packaging)

## Important links
- [Rest Cheatsheet](https://github.com/RestCheatSheet/api-cheat-sheet#api-design-cheat-sheet)
- [Spring Boot Homepage](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)
- [JUnit Test Framework](https://junit.org/junit5/)
- [Java Documentation](https://docs.oracle.com/javase/8/docs/api/)
