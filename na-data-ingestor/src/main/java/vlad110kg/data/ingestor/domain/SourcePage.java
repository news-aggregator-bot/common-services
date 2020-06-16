package vlad110kg.data.ingestor.domain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class SourcePage {

    private String name;

    private String url;

    private String language;

    private List<String> categories;

    private List<ContentTag> tags = new ArrayList<>();

    public void addTag(ContentTag tag) {
        tags.add(tag);
    }
}
