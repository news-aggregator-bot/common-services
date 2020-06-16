package vlad110kg.news.aggregator.web.parser;

import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import vlad110kg.news.aggregator.domain.PageParsedData;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebPageParserManager {

    @Autowired
    @Qualifier("defaultParser")
    private WebContentParser defaultParser;

    @Autowired
    private Map<String, WebContentParser> parsers;

    public List<PageParsedData> parse(SourcePage page, SourcePageContentTag contentTag) {
        List<PageParsedData> defaultParsedData = defaultParser.parse(page, contentTag);
        if (!defaultParsedData.isEmpty()) {
            return defaultParsedData;
        }
        return defaultParsedData;
    }

}
