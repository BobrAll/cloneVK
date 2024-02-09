./mvnw package
docker image rm -f backend
docker build . -t "backend"
docker compose up
