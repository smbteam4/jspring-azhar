FROM openjdk:11-jdk-slim

RUN mkdir /APPDIR

RUN groupadd -r appuser && \
    useradd -r appuser -g appuser

COPY ./build/libs/robot-*.jar /APPDIR/app.jar
COPY ./entrypoint.sh /APPDIR/entrypoint.sh


RUN chown -R appuser:appuser /APPDIR
USER appuser


RUN chmod +x /APPDIR/app.jar
RUN chmod +x /APPDIR/entrypoint.sh

WORKDIR /APPDIR
ENTRYPOINT ["sh", "entrypoint.sh", "APPDIR"]
EXPOSE 8080
