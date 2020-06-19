package vlad110kg.news.aggregator.service;

import vlad110kg.news.aggregator.entity.Language;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ILanguageService {

    Language save(Language language);

    List<Language> saveAll(Collection<Language> languages);

    Optional<Language> find(String name);
}
