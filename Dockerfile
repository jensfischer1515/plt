FROM java:8u66-jre

ENV JAVA_TOOL_OPTIONS="-Xmx256m -Duser.timezone=Europe/Paris"

COPY target/plt-DEV-SNAPSHOT.jar /app/plt.jar

WORKDIR /app

#ENTRYPOINT ["java","-jar","/app/plt.jar"]
ENTRYPOINT ["java", "-Dspring.jpa.generateDdl=true", "-Dspring.jpa.showSql=true", "-Dspring.jpa.hibernate.ddlAuto=update", "-jar","/app/plt.jar"]
