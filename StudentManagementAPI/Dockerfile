FROM ubuntu:16.04

# Install prerequisites
RUN apt-get -y update && apt-get -y upgrade
RUN apt-get -y install openjdk-8-jdk wget
RUN apt-get -y install curl
RUN apt-get -y install maven
RUN mkdir /usr/local/tomcat
RUN wget https://downloads.apache.org/tomcat/tomcat-8/v8.5.84/bin/apache-tomcat-8.5.84.tar.gz -O /tmp/tomcat.tar.gz
RUN cd /tmp && tar xvfz tomcat.tar.gz
RUN cp -Rv /tmp/apache-tomcat-8.5.84/* /usr/local/tomcat/

EXPOSE 8080
# java
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64

# Define default command.
CMD ["bash"]

#  copy whole directory to docker
COPY . /usr/local/tomcat/webapps/
#  change directory to webapps
WORKDIR /usr/local/tomcat/webapps/
#  build war file
RUN mvn clean install
#  move war file
RUN mv /usr/local/tomcat/webapps/target/backend.war /usr/local/tomcat/webapps/backend.war
#  rename war file
RUN mv /usr/local/tomcat/webapps/backend.war /usr/local/tomcat/webapps/ROOT.war
#  change directory to tomcat
WORKDIR /usr/local/tomcat/
# print ls
RUN ls -l /usr/local/tomcat/webapps/
RUN rm -rf /usr/local/tomcat/webapps/ROOT
# start tomcat
CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]