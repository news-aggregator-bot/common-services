package vlad110kg.news.aggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad110kg.news.aggregator.entity.NewsNote;
import vlad110kg.news.aggregator.repository.NewsNoteRepository;

import java.util.Optional;

@Service
public class NewsNoteService implements INewsNoteService {

    @Autowired
    private NewsNoteRepository repository;

    @Override
    @Transactional
    public NewsNote save(NewsNote note) {
        return repository.save(note);
    }

    @Override
    public Optional<NewsNote> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<NewsNote> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public boolean exists(String url) {
        return repository.existsByUrl(url);
    }
}
