package vlad110kg.data.ingestor.service;

@FunctionalInterface
public interface IngestionConsumer {

    void consume(String value, Object entity);
}
