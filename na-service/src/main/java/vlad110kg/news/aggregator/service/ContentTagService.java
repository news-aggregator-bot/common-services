package vlad110kg.news.aggregator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;
import vlad110kg.news.aggregator.repository.SourcePageContentTagRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ContentTagService implements IContentTagService {

    @Autowired
    private SourcePageContentTagRepository repository;

    @Override
    public List<SourcePageContentTag> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SourcePageContentTag> findByIds(Collection<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public List<SourcePageContentTag> findBySource(Source source) {
        return repository.findBySource(source);
    }

    @Override
    public Optional<SourcePageContentTag> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public SourcePageContentTag save(SourcePageContentTag tag) {
        return repository.save(tag);
    }

    @Override
    @Transactional
    public List<SourcePageContentTag> saveAll(List<SourcePageContentTag> tags) {
        return repository.saveAll(tags);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        findById(id).ifPresent(p -> repository.deleteById(id));
    }
}
