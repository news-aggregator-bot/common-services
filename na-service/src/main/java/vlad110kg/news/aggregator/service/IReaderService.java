package vlad110kg.news.aggregator.service;

import vlad110kg.news.aggregator.entity.Reader;

import java.util.Optional;

public interface IReaderService {

    Reader save(Reader reader);

    Optional<Reader> find(long chatId);

    boolean enable(long chatId);

    boolean disable(long chatId);
}
