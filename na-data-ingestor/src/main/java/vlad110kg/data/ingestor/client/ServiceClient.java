package vlad110kg.data.ingestor.client;

import vlad110kg.data.ingestor.domain.Category;
import vlad110kg.data.ingestor.domain.CategoryLocalisation;
import vlad110kg.data.ingestor.domain.Language;
import vlad110kg.data.ingestor.domain.Source;

import java.util.List;

public interface ServiceClient {

    void ingestSource(Source source);

    void ingestCategories(List<Category> categories);

    void ingestLocalisations(List<CategoryLocalisation> localisations);

    void ingestLanguages(List<Language> languages);
}
