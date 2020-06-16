package vlad110kg.news.aggregator.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.news.aggregator.entity.Language;
import vlad110kg.news.aggregator.repository.LanguageRepository;

import java.util.Optional;

@Service
@Slf4j
public class LanguageService implements ILanguageService {

    @Autowired
    private LanguageRepository repository;

    @Override
    public Language save(Language language) {
        return repository.save(language);
    }

    @Override
    public Optional<Language> find(String name) {
        return repository.findById(name);
    }
}
