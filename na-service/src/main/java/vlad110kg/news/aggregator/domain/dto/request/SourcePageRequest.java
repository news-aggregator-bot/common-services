package vlad110kg.news.aggregator.domain.dto.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ToString
public class SourcePageRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String url;

    @NotBlank
    private String language;

    private List<String> categories;

    private List<Long> contentTagIds;
}
