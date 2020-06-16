package vlad110kg.news.aggregator.web.parser;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator.Class;
import vlad110kg.news.aggregator.domain.PageParsedData;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;
import vlad110kg.news.aggregator.web.reader.WebPageReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("defaultParser")
public class JsoupWebContentParser implements WebContentParser {

    @Autowired
    @Qualifier("jsoupReader")
    private WebPageReader<Document> pgRdr;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<PageParsedData> parse(SourcePage page, SourcePageContentTag tag) {
        Document doc = pgRdr.read(page.getUrl());
        Elements mainClassElems = doc.select(classTag(tag.getMain()));

        Builder<PageParsedData> datas = ImmutableList.builder();
        for (Element wrapper : mainClassElems) {

            Element titleEl = wrapper.selectFirst(classTag(tag.getTitle()));

            datas.add(PageParsedData.builder()
                .title(titleEl.text())
                .link(titleEl.attr("href"))
                .description(getDescription(tag, wrapper))
                .build());
        }
        return datas.build();
    }

    private String getDescription(SourcePageContentTag contentTag, Element wrapper) {
        if (contentTag.getDescription() != null) {
            return wrapper.selectFirst(classTag(contentTag.getDescription())).text();
        }
        return null;
    }

    private Class classTag(String name) {
        return new Class(name);
    }

}
