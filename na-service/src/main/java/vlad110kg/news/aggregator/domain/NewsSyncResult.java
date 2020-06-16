package vlad110kg.news.aggregator.domain;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import vlad110kg.news.aggregator.entity.NewsNote;
import vlad110kg.news.aggregator.entity.Source;

@Builder
@Getter
public class NewsSyncResult {

    @JsonProperty
    private final Source source;

    @JsonProperty("news_notes")
    private final Set<NewsNote> newsNotes;
}
