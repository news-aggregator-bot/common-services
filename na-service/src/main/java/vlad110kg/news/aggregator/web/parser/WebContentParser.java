package vlad110kg.news.aggregator.web.parser;

import java.util.List;

import vlad110kg.news.aggregator.domain.PageParsedData;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;

public interface WebContentParser {

    List<PageParsedData> parse(SourcePage page, SourcePageContentTag tag);
}
