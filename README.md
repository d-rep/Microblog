Microblog
---------
This is a simple Microblogging web application.  Any resemblance to existing internet services is coincidental.

This project uses [Spring MVC], [Spring Data JPA], and [Spring Security].  It is setup to be edited using [SpringSource Tool Suite].

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].


Prerequisites
-------------
This project requires a [Java Development Kit] v1.6 or newer and [Apache Maven] 3 to compile the source code.  If you simply want to test drive the application, it does not require a stand-alone Java application server.


Database Setup
==============
This webapp requires a database, so setup one and add the following JNDI entry to your servlet container: `jdbc/microblog`

Add your database settings like username & password to this file if using Tomcat: `src/main/webapp/META-INF/context.xml`

The app includes database drivers for [MySQL] 5 and uses that dialect by default.


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
[Java Development Kit]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Apache Maven]: http://maven.apache.org/download.html
[MySQL]: http://dev.mysql.com/downloads/
[SpringSource Tool Suite]: http://www.springsource.com/downloads/sts
[Spring Security]: http://static.springsource.org/spring-security/site/docs/3.1.x/reference/springsecurity.html
[Spring MVC]: http://static.springsource.org/spring/docs/3.1.x/spring-framework-reference/html/mvc.html
[Spring Data JPA]: http://static.springsource.org/spring-data/data-jpa/docs/current/reference/html/

