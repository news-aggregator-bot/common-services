package vlad110kg.data.ingestor.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Category {

    private String name;

    private String parent;
}
