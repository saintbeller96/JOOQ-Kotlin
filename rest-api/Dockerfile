FROM eclipse-temurin:17-jdk
USER app
WORKDIR /home/app
COPY --chown=app:app ./rest-api/build/libs/rest-api.jar ./rest-api.jar

EXPOSE 8080

ENV LC_ALL=C.UTF-8
ENV TZ=Asia/Seoul
ENV JAVA_OPTS=""
ENV GC_OPTS="-XX:+UseG1GC -XX:MetaspaceSize=96m -Xms256m -Xmx256m"
ENTRYPOINT ["sh", \
            "-c", \
            "java -server $JAVA_OPTS $GC_OPTS -Djava.security.egd=file:/dev/./urandom -jar ./rest-api.jar"]
