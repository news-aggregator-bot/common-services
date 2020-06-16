package vlad110kg.data.ingestor.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Language {

    private String lang;
    private String name;
    private String localised;
}
