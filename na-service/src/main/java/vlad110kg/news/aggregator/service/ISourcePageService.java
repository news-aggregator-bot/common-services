package vlad110kg.news.aggregator.service;

import java.util.Collection;
import java.util.List;

import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;

public interface ISourcePageService {

    List<SourcePage> findAll();

    List<SourcePage> findBySource(Source source);

    SourcePage save(SourcePage page);

    Collection<SourcePage> save(Collection<SourcePage> pages);

    void delete(long id);
}
