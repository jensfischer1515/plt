FROM java:8u66-jre

ENV JAVA_TOOL_OPTIONS="-Xmx350m -Xss512k -Dfile.encoding=UTF-8 -Xss512k -XX:+UseCompressedOops -Duser.timezone=Europe/Paris"

COPY target/plt-DEV-SNAPSHOT.jar /app/plt.jar

WORKDIR /app

#ENTRYPOINT ["java","-jar","/app/plt.jar"]
#ENTRYPOINT ["java", "-Dspring.jpa.generateDdl=true", "-Dspring.jpa.showSql=true", "-Dspring.jpa.hibernate.ddlAuto=create", "-jar","/app/plt.jar"]
ENTRYPOINT ["java", "-Dspring.jpa.generateDdl=false", "-Dspring.jpa.showSql=true", "-Dspring.jpa.hibernate.ddlAuto=validate", "-jar","/app/plt.jar"]
