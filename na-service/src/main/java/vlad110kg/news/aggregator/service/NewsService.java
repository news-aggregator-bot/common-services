package vlad110kg.news.aggregator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vlad110kg.news.aggregator.domain.NewsSyncResult;
import vlad110kg.news.aggregator.domain.PageParsedData;
import vlad110kg.news.aggregator.entity.NewsNote;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.exception.SourceNotFoundException;
import vlad110kg.news.aggregator.web.parser.WebPageParserManager;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NewsService implements INewsService {

    @Autowired
    private ISourceService sourceService;

    @Autowired
    private INewsNoteService newsNoteService;

    @Autowired
    private WebPageParserManager webPageParser;

    @Override
    public NewsSyncResult sync(String name) {
        Source source = sourceService.findByName(name).orElseThrow(SourceNotFoundException::new);
        return sync(source);
    }

    @Override
    public List<NewsSyncResult> syncAll() {
        return sourceService.findAll().stream().map(this::sync).collect(Collectors.toList());
    }

    private NewsSyncResult sync(Source source) {
        Set<NewsNote> newlySavedNewsNotes = source.getPages()
            .parallelStream()
            .flatMap(this::process)
            .collect(Collectors.toSet());
        return NewsSyncResult.builder().source(source).newsNotes(newlySavedNewsNotes).build();
    }

    private Stream<NewsNote> process(SourcePage page) {
        return page.getContentTags()
            .parallelStream()
            .map(tag -> webPageParser.parse(page, tag))
            .flatMap(List::stream)
            .filter(d -> !newsNoteService.exists(d.getLink()))
            .map(d -> toNote(page, d))
            .map(newsNoteService::save);
    }

    private NewsNote toNote(SourcePage page, PageParsedData data) {
        NewsNote note = new NewsNote();
        note.setTitle(data.getTitle());
        note.setUrl(data.getLink());
        note.setDescription(data.getDescription());
        note.setSourcePage(page);
        return note;
    }
}
