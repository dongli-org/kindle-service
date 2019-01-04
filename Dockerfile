FROM openjdk:8
MAINTAINER wanli <wanlinus@qq.com>
VOLUME /kindle
COPY target/*.jar kindle.jar
ENTRYPOINT ["java", "-jar", "kindle.jar"]