FROM ubuntu:16.04

# Install prerequisites
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-8-jdk wget
RUN apt-get -y install curl
RUN mkdir /usr/local/tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-8/v8.5.84/bin/apache-tomcat-8.5.84.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-8.5.84/* /usr/local/tomcat/

EXPOSE 8080
# java
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Define default command.
CMD ["bash"]


# rebuild the war file
RUN mvn clean package

# copy wwar file from target to webapps
COPY target/backend.war /usr/local/tomcat/webapps/
#  rename war file to ROOT.war
RUN mv /usr/local/tomcat/webapps/backend.war /usr/local/tomcat/webapps/ROOT.war
# print ls
RUN ls -l /usr/local/tomcat/webapps/
RUN rm -rf /usr/local/tomcat/webapps/ROOT
# start tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]