package vlad110kg.news.aggregator.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vlad110kg.news.aggregator.domain.dto.request.ContentTagRequest;
import vlad110kg.news.aggregator.domain.dto.request.SourcePageRequest;
import vlad110kg.news.aggregator.domain.dto.request.SourceRequest;
import vlad110kg.news.aggregator.entity.Category;
import vlad110kg.news.aggregator.entity.Language;
import vlad110kg.news.aggregator.entity.Source;
import vlad110kg.news.aggregator.entity.SourcePage;
import vlad110kg.news.aggregator.entity.SourcePageContentTag;
import vlad110kg.news.aggregator.exception.ResourceNotFoundException;
import vlad110kg.news.aggregator.exception.SourceNotFoundException;
import vlad110kg.news.aggregator.service.ICategoryService;
import vlad110kg.news.aggregator.service.IContentTagService;
import vlad110kg.news.aggregator.service.ILanguageService;
import vlad110kg.news.aggregator.service.ISourcePageService;
import vlad110kg.news.aggregator.service.ISourceService;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SourceFacade {

    @Autowired
    private ISourceService sourceService;

    @Autowired
    private ISourcePageService sourcePageService;

    @Autowired
    private IContentTagService contentTagService;

    @Autowired
    private ILanguageService languageService;

    @Autowired
    private ICategoryService categoryService;

    public List<Source> getAllSources() {
        return sourceService.findAll();
    }

    public List<SourcePage> getAllSourcePages(Long sourceId) {
        Source source = sourceService.find(sourceId).orElseThrow(SourceNotFoundException::new);
        return sourcePageService.findBySource(source);
    }

    public List<SourcePageContentTag> getAllSourceContentTags(Long sourceId) {
        Source source = sourceService.find(sourceId).orElseThrow(SourceNotFoundException::new);
        return contentTagService.findBySource(source);
    }

    public Source createSource(SourceRequest request) {
        return sourceService.create(request.getName());
    }

    public SourcePage createSourcePage(String name, SourcePageRequest page) {
        Source source = getSource(name);
        SourcePage srcPage = toSrcPage(source, page);
        return sourcePageService.save(srcPage);
    }

    public Collection<SourcePage> createSourcePages(
        String name,
        List<SourcePageRequest> pages
    ) {
        Source source = getSource(name);
        List<SourcePage> srcPages = pages.stream().map(p -> toSrcPage(source, p)).collect(Collectors.toList());
        return sourcePageService.save(srcPages);
    }

    public SourcePageContentTag createContentTag(
        String name,
        ContentTagRequest tag
    ) {
        Source source = getSource(name);
        return contentTagService.save(toSourceContentTag(source, tag));
    }

    public Collection<SourcePageContentTag> createContentTags(
        String name,
        List<ContentTagRequest> tags
    ) {
        Source source = getSource(name);
        List<SourcePageContentTag> contentTags = tags.stream()
            .map(p -> toSourceContentTag(source, p))
            .collect(Collectors.toList());
        return contentTagService.saveAll(contentTags);
    }

    private Source getSource(String name) {
        return sourceService.findByName(name).orElseThrow(SourceNotFoundException::new);
    }

    private SourcePage toSrcPage(Source source, SourcePageRequest p) {
        Language language = languageService.find(p.getLanguage()).orElseThrow(ResourceNotFoundException::new);
        List<Category> categories = p.getCategories().stream().map(categoryService::findByName)
            .map(o -> o.orElseThrow(ResourceNotFoundException::new))
            .collect(Collectors.toList());
        List<SourcePageContentTag> contentTags = contentTagService.findByIds(p.getContentTagIds());

        SourcePage srcPage = new SourcePage();
        srcPage.setName(p.getName());
        srcPage.setUrl(p.getUrl());
        srcPage.setSource(source);
        srcPage.setCategories(categories);
        srcPage.setLanguage(language);
        srcPage.setContentTags(contentTags);
        return srcPage;
    }

    private SourcePageContentTag toSourceContentTag(Source source, ContentTagRequest tagRequest) {
        SourcePageContentTag tag = new SourcePageContentTag();
        tag.setMain(tagRequest.getMain());
        tag.setTitle(tagRequest.getTitle());
        tag.setDescription(tagRequest.getDescription());
        tag.setSource(source);
        return tag;
    }
}
