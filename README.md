# where-to-live

```shell 
1. mvn test 
/*
not obvious, but it will populate DB by
countries and cities
Temp DB will be stored in <userFolder>:
compare_countries.mv.db
compare_countries.trace.db
*/
``` 
```shell
2. mvn spring-boot:run -Drun.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005"
/*
the jvm args will allow you to remotely debug your 
application in RT.
Just add new Remote configuration in IntelliJ IDEA
*/
```

## To install profile (dev or prod), use
```shell
2. mvn clean install -P<dev|prod>
```