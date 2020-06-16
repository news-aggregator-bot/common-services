package vlad110kg.news.aggregator.service;

import java.util.List;
import java.util.Optional;

import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.repository.SourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceService implements ISourceService {

    @Autowired
    private SourceRepository repository;

    @Override
    public Source create(String name) {
        Source src = new Source();
        src.setName(name);
        return save(src);
    }

    @Override
    public Source save(Source name) {
        return repository.save(name);
    }

    @Override
    public List<Source> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Source> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Source> findByName(String name) {
        return repository.findByName(name);
    }
}
