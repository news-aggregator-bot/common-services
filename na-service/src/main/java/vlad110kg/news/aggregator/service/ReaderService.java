package vlad110kg.news.aggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.news.aggregator.entity.Reader;
import vlad110kg.news.aggregator.repository.ReaderRepository;

import java.util.Optional;

@Service
public class ReaderService implements IReaderService {

    @Autowired
    private ReaderRepository readerRepository;

    @Override
    public Reader create(Reader reader) {
        reader.setStatus(Reader.Status.ENABLED);
        return readerRepository.save(reader);
    }

    @Override
    public Optional<Reader> find(long chatId) {
        return readerRepository.findByChatId(chatId);
    }

    @Override
    public boolean enable(long chatId) {
        return find(chatId).map(r -> {
            r.setStatus(Reader.Status.ENABLED);
            return readerRepository.save(r);
        }).isPresent();
    }

    @Override
    public boolean disable(long chatId) {
        return find(chatId).map(r -> {
            r.setStatus(Reader.Status.DISABLED);
            return readerRepository.save(r);
        }).isPresent();
    }
}
