include .env
export

JAR=target/peerToPeer-0.0.1-SNAPSHOT.jar
IMAGE=eachare-peer

.PHONY: build run clean stop

build:
	mvn clean package

docker-build:
	build
	docker build -t $(IMAGE) .

run:
	docker run --rm -it --name $(CONTAINER_NAME) --env-file .env -p $(PORT) $(IMAGE)
	run: docker-build

stop:
	-docker stop $(CONTAINER_NAME)

clean:
	mvn clean
	rm -rf target
