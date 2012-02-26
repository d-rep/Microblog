Microblog
---------
This is a simple Microblogging web application.  Any resemblance to existing internet services is coincidental.

Copyright &copy; 2012-, [Drew Repasky].  Licensed under [Apache License, Version 2.0].

The source code can be built with Maven and run using embedded Tomcat 6.  This project is setup to be edited using [SpringSource Tool Suite].

Database Setup
--------------
This webapp requires a database, so setup one and add the following JNDI entry to your servlet container: `jdbc/microblog`

Add your database settings like username & password to this file: `src/main/webapp/META-INF/context.xml`

This uses [Spring Security], and the schema can be setup using the following DDL (this is the MySQL dialect):

    create table users (
        username varchar(50) not null primary key,
        password varchar(80) not null,
        enabled boolean not null
    ) engine = InnoDb;

    create table authorities (
        username varchar(50) not null,
        authority varchar(50) not null,
        foreign key (username) references users (username),
        unique index authorities_idx_1 (username, authority)
    ) engine = InnoDb;

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

