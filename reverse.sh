liquibase --driver=com.mysql.jdbc.Driver \
      --classpath=/home/alex/.m2/repository/com/mysql/jdbc/driver/mysql-connector-java-5.1.23.jar \
      --changeLogFile=/src/main/resources/org/vetech/lrms/core/db/liquibase.xml \
      --url="jdbc:mysql://localhost:3306/coreLRMS?autoReconnect=true&allowMultiQueries=true&createDatabaseIfNotExist=true" \
      --username=root \
      --password=root \
      generateChangeLog