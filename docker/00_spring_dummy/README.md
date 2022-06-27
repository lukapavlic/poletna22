Empty SpringBoot Rest

docker build -t=springdummy:latest .

docker run -d -p 8080:8080 springdummy:latest

curl http://127.0.0.1:8080/info

http://127.0.0.1:8080/swagger-ui/