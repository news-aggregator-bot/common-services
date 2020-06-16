package vlad110kg.news.aggregator.service;

import java.util.Optional;

import vlad110kg.news.aggregator.entity.Language;

public interface ILanguageService {

    Language save(Language language);

    Optional<Language> find(String name);
}
