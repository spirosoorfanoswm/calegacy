FROM openjdk:8-jre
COPY target/creditarkservices-0.0.1-SNAPSHOT.jar app.jar
#RUN apk add tzdata
#RUN cp /usr/share/zoneinfo/Asia/Qatar /etc/localtime
#RUN echo "Asia/Qatar" >  /etc/timezone
#RUN date
#RUN apk del tzdata
RUN apt-get update && apt-get install libfontconfig
ENV JAVA_OPTS $JAVA_OPTS
#CMD ["java", "-jar", "app.jar"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Denvironment=$HOST_IP","-jar","/app.jar"]
ENTRYPOINT java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar