package vlad110kg.data.ingestor.domain;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class Source {

    private String name;
    private List<SourcePage> pages = new ArrayList<>();

    public void addPage(SourcePage page) {
        pages.add(page);
    }
}
