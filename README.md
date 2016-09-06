# ReDo Air

[![Build Status](https://travis-ci.org/HerrSubset/redo-air.svg?branch=master)](https://travis-ci.org/HerrSubset/redo-air)
[![Coverage Status](https://coveralls.io/repos/github/HerrSubset/redo-air/badge.svg?branch=master)](https://coveralls.io/github/HerrSubset/redo-air?branch=master)

Repostitory of team Nick & PJ for the first project of the acaddemict track at Realdolmen. Based on [jee7-starter](https://github.com/kvanrobbroeck/jee7-starter).

Platform
--------
Verified for use on Wildfly 10.0.0.Final. Apply the "wildfly-10.0.0.Final-diff.zip" file over a clean wildfly 10.0.0 installation to set things up correctly.

Handy features
--------------
    * Unit tests with either H2 or mysql (-DdatabaseEngine=h2)
    * DBUnit data loading in data.xml
    * Unit tests for remote EJB and JMS interaction enabled only when passing -Dintegration
    * Support for Travis CI

Prerequisites
-------------
    * JDK 8
    * Maven 3
    * MySQL server (schema "realdolmen", username="root", password="")

Resources provided by wildfly configuration
-------------------------------------------
    * JMS Queue (non-durable): java:jboss/exported/rd/queues/RealDolmenQueue
    * JTA Datasource java:/rd/queues/RealDolmenDataSource (jdbc:mysql://localhost:3306/realdolmen user: root password: (blank)
    * Administrator user for management and application user: administrator/Azerty123! (used for admin console, and JMS session authentication)
