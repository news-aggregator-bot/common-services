package vlad110kg.news.aggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.news.aggregator.entity.Category;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.repository.SourcePageRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SourcePageService implements ISourcePageService {

    @Autowired
    private SourcePageRepository repository;

    @Override
    public List<SourcePage> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SourcePage> findBySource(Source source) {
        return repository.findAllBySource(source);
    }

    @Override
    public List<SourcePage> findByCategory(Category category) {
        return repository.findAllByCategories(category);
    }

    @Override
    public Optional<SourcePage> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    @Override
    public SourcePage save(SourcePage page) {
        return repository.save(page);
    }

    @Override
    public Collection<SourcePage> save(Collection<SourcePage> pages) {
        return repository.saveAll(pages);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
