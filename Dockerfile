FROM openjdk:17-oracle
RUN mkdir datashared
COPY target/ms-document-manager.jar ms-document-manager.jar
EXPOSE 8012
ENTRYPOINT ["java","-jar","/ms-document-manager.jar"]