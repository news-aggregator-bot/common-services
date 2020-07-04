package vlad110kg.news.aggregator.domain.response;

import lombok.Builder;
import lombok.Data;
import vlad110kg.news.aggregator.entity.Language;

@Data
@Builder
public class LanguageResponse {

    private String lang;

    private String name;

    private String localized;

    public LanguageResponse(Language language) {
        this.lang = language.getLang();
        this.name = language.getName();
        this.localized = language.getLocalized();
    }
}
