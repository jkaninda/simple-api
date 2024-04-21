IMAGE_NAME=jkaninda/simple-api:latest
compile:
	./mvnw clean package
build:
	./mvnw clean package
docker-build: build
	docker build -f docker/Dockerfile -t ${IMAGE_NAME} .

docker-build-rootless: build
	docker build -f docker/Dockerfile -t ${IMAGE_NAME} .
docker-run: docker-build-rootless
	docker compose up -d --force-recreate

