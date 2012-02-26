Microblog
---------
This is a simple Microblogging web application.  Any resemblance to existing internet services is coincidental.

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].

The source code can be built with Maven and run using embedded Tomcat 6.  This project is setup to be edited using [SpringSource Tool Suite].

This uses [Spring MVC], [Spring Data JPA], and [Spring Security].

Database Setup
--------------
This webapp requires a database, so setup one and add the following JNDI entry to your servlet container: `jdbc/microblog`

Add your database settings like username & password to this file: `src/main/webapp/META-INF/context.xml`


Building
--------
First, download the latest and greatest source code:

    git clone git://github.com/d-rep/Microblog.git

Build and run the code with the following commands:

    cd Microblog/microblog
    mvn clean package
    mvn tomcat6:run

[Drew Repasky]: http://twitter.com/drewrepasky
[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[SpringSource Tool Suite]: http://www.springsource.com/downloads/sts
[Spring Security]: http://static.springsource.org/spring-security/site/docs/3.1.x/reference/springsecurity.html
[Spring MVC]: http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/mvc.html
[Spring Data JPA]: http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/

