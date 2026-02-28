# Используем легкий образ Java
FROM eclipse-temurin:21-jre-alpine

# Создаем рабочую директорию
WORKDIR /app

# Аргумент, который мы будем передавать при сборке (путь к jar)
ARG JAR_FILE

# Копируем скомпилированный jar файл в контейнер
COPY ${JAR_FILE} app.jar

# Запускаем приложение
ENTRYPOINT ["java", "-jar", "app.jar"]