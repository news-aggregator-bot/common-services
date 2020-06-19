package vlad110kg.news.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "source_page")
@EqualsAndHashCode(callSuper = true)
public class SourcePage extends DatedEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_source")
    @JsonIgnore
    private Source source;

    @ManyToMany
    @JoinTable(
        name = "source_page_category",
        joinColumns = {@JoinColumn(name = "id_source_page")},
        inverseJoinColumns = {@JoinColumn(name = "id_category")}
    )
    private List<Category> categories;

    @OneToMany(mappedBy = "sourcePage")
    @JsonIgnore
    private List<ContentBlock> contentBlocks;

}
