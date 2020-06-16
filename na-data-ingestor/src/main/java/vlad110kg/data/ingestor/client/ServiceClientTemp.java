package vlad110kg.data.ingestor.client;

import org.springframework.stereotype.Service;
import vlad110kg.data.ingestor.domain.Category;
import vlad110kg.data.ingestor.domain.CategoryLocalisation;
import vlad110kg.data.ingestor.domain.Language;
import vlad110kg.data.ingestor.domain.Source;

import java.util.List;

@Service
public class ServiceClientTemp implements ServiceClient {

    @Override
    public void ingestSource(Source source) {
        System.out.println(source.toString());
    }

    @Override
    public void ingestCategories(List<Category> categories) {
        System.out.println(categories);
    }

    @Override
    public void ingestLocalisations(List<CategoryLocalisation> localisations) {
        System.out.println(localisations);
    }

    @Override
    public void ingestLanguages(List<Language> languages) {
        System.out.println(languages);
    }
}
