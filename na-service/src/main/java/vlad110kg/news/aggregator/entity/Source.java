package vlad110kg.news.aggregator.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table(name = "source")
@EqualsAndHashCode(callSuper = true)
public class Source extends DatedEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SourcePage> pages;
}
