FROM tomcat:latest
LABEL authors="babu"

COPY build/libs/exploded /usr/local/tomcat/webapps

