package vlad110kg.data.ingestor.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContentTag {

    private final ContentTagType type;
    private String value;
}
