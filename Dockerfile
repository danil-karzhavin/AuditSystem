FROM openjdk:latest

# Устанавливаем рабочий каталог внутри контейнера
WORKDIR /CSApp

# Копируем jar-файл вашего Spring Boot приложения в контейнер
COPY target/App-0.0.1-SNAPSHOT.jar CSApp.jar

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "CSApp.jar"]