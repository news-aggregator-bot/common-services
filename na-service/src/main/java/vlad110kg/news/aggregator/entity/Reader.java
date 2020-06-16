package vlad110kg.news.aggregator.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "reader")
@EqualsAndHashCode(callSuper = true)
public class Reader extends DatedEntity {

    @Column(nullable = false)
    private String nick;

    @Column(nullable = false)
    private String name;

    private boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform")
    private Platform platform;

    @ManyToMany
    @JoinTable(
        name = "reader_source_page",
        joinColumns = {@JoinColumn(name = "id_reader")},
        inverseJoinColumns = {@JoinColumn(name = "id_source_page")}
    )
    private List<SourcePage> sourcePages;

    @ManyToMany
    @JoinTable(
        name = "reader_lang",
        joinColumns = {@JoinColumn(name = "id_reader")},
        inverseJoinColumns = {@JoinColumn(name = "language")}
    )
    private Set<Language> languages;
}
