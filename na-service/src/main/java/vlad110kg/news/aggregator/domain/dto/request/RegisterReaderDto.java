package vlad110kg.news.aggregator.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterReaderDto {

    @JsonProperty("chat_id")
    private long chatId;
    @NotBlank
    private String username;
    @NotBlank
    private String name;
    @NotBlank
    private String platform;
    @NotBlank
    @JsonProperty("primary_language")
    private String primaryLanguage;
}
