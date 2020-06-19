package vlad110kg.news.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "content_block")
@EqualsAndHashCode(callSuper = true)
public class ContentBlock extends IdEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_source_page")
    @JsonIgnore
    private SourcePage sourcePage;

    @ManyToMany
    @JoinTable(
        name = "content_block_tag",
        joinColumns = {@JoinColumn(name = "id_block")},
        inverseJoinColumns = {@JoinColumn(name = "id_tag")}
    )
    @JsonIgnore
    private List<ContentTag> tags;

    private Map<ContentTagType, ContentTag> typeMap;

    public ContentTag findByType(ContentTagType type) {
        if (typeMap == null) {
            typeMap = tags.stream()
                .collect(Collectors.toMap(ContentTag::getType, Function.identity()));
        }
        return typeMap.get(type);
    }
}
