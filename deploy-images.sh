mvn clean package
docker build na-registry -t ghcr.io/news-aggregator-bot/bepicky.na-registry:latest
docker push ghcr.io/news-aggregator-bot/bepicky.na-registry:latest
