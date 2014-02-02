Microblog [![Build Status](https://travis-ci.org/d-rep/Microblog.png?branch=master)](https://travis-ci.org/d-rep/Microblog)
=========
This is a simple Microblogging web application.  Any resemblance to existing internet services is coincidental.

This project uses [Spring MVC], [Spring Data JPA], [Spring Security], and [Apache Tiles].

Copyright &copy; 2014-, Drew Repasky.  Licensed under [Apache License, Version 2.0].


Prerequisites
=============
This project requires a [Java Development Kit] v1.6 or newer and [Apache Maven] 3 to compile the source code.  If you simply want to test drive the application, it does not require a stand-alone Java application server.


Database Setup
--------------
This webapp requires a database, so setup one and add the following JNDI entry to your servlet container: `jdbc/microblog`

Add your database settings like username & password to this file if using Tomcat: `src/main/webapp/META-INF/context.xml` and the schema will be created when the application starts.

The app includes database drivers for [MySQL] 5 and uses that dialect by default.


Building
========
First, download the latest and greatest source code:

    git clone git://github.com/d-rep/Microblog.git

Build and run the code with the following commands:

    cd Microblog
    mvn clean install
    mvn tomcat7:run


Then open your browser to this address: [http://localhost:8080/microblog/](http://localhost:8080/microblog/)

Editing
=======
This project does not require an IDE, but is setup to be edited using an [Eclipse]-based editor like [SpringSource Tool Suite].  You should also install the m2eclipse plugin from Eclipse Marketplace.

1. Create a new workspace
2. click File -> Import -> General -> Existing Projects into Workspace
3. For the root directory, select the microblog directory from the git repository you cloned locally.
4. click Finish

[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Java Development Kit]: http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Apache Maven]: http://maven.apache.org/download.html
[MySQL]: http://dev.mysql.com/downloads/
[Spring MVC]: http://docs.spring.io/spring/docs/4.0.1.RELEASE/spring-framework-reference/htmlsingle/#mvc
[Spring Data JPA]: http://docs.spring.io/spring-data/jpa/docs/1.4.3.RELEASE/reference/html/
[Spring Security]: http://docs.spring.io/spring-security/site/docs/3.2.0.RELEASE/reference/htmlsingle/
[Apache Tiles]: http://tiles.apache.org/2.2/framework/tutorial/index.html
[Eclipse]: http://www.eclipse.org/downloads/
[SpringSource Tool Suite]: http://www.springsource.com/downloads/sts

