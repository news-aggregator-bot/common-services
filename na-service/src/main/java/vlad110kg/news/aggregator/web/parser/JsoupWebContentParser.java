package vlad110kg.news.aggregator.web.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.select.Evaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import vlad110kg.news.aggregator.domain.PageParsedData;
import vlad110kg.news.aggregator.entity.ContentBlock;
import vlad110kg.news.aggregator.entity.ContentTag;
import vlad110kg.news.aggregator.entity.ContentTagType;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.web.reader.WebPageReader;

import java.util.Collections;
import java.util.List;

@Component("defaultParser")
public class JsoupWebContentParser implements WebContentParser {

    @Autowired
    @Qualifier("jsoupReader")
    private WebPageReader<Document> pgRdr;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<PageParsedData> parse(SourcePage page, ContentBlock block) {
        Document doc = pgRdr.read(page.getUrl());

        ContentTag mainTag = block.findByType(ContentTagType.MAIN);
        ContentTag titleTag = block.findByType(ContentTagType.TITLE);
        ContentTag linkTag = block.findByType(ContentTagType.LINK);
        ContentTag descriptionTag = block.findByType(ContentTagType.DESCRIPTION);
        ContentTag authorTag = block.findByType(ContentTagType.AUTHOR);

        if (mainTag != null) {
            Elements mainClassElems = doc.select(classTagStarts(mainTag.getValue()));

            Builder<PageParsedData> datas = ImmutableList.builder();
            for (Element wrapper : mainClassElems) {

                Element titleEl = wrapper.selectFirst(classTagStarts(titleTag.getValue()));
                PageParsedData.PageParsedDataBuilder dataBuilder = PageParsedData.builder();
                dataBuilder.title(titleEl.text());
                if (linkTag == null) {
                    Element a = titleEl.selectFirst(new Evaluator.Tag("a"));
                    dataBuilder.link(a.attr("href"));
                } else {
                    Element linkEl = wrapper.selectFirst(classTagStarts(linkTag.getValue()));
                    dataBuilder.link(linkEl.attr("href"));
                }
                dataBuilder.description(getDescription(descriptionTag, wrapper))
                    .author(getAuthor(authorTag, wrapper));

                datas.add(dataBuilder.build());
            }
            return datas.build();
        }
        return Collections.emptyList();
    }

    private String getDescription(ContentTag contentTag, Element wrapper) {
        if (contentTag != null) {
            return wrapper.selectFirst(classTagStarts(contentTag.getValue())).text();
        }
        return null;
    }

    private String getAuthor(ContentTag authorTag, Element wrapper) {
        if (authorTag != null) {
            Element element = wrapper.selectFirst(classTag(authorTag.getValue()));
            return element == null ? null : element.text();
        }
        return null;
    }

    private Evaluator.Class classTag(String name) {
        return new Evaluator.Class(name);
    }

    private Evaluator.AttributeWithValueContaining classTagStarts(String name) {
        return new Evaluator.AttributeWithValueContaining("class", name + " ");
    }

}
