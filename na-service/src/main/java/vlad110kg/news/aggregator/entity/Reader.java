package vlad110kg.news.aggregator.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "reader")
@EqualsAndHashCode(callSuper = true)
public class Reader extends DatedEntity {

    @JsonProperty("chat_id")
    @Column(nullable = false, unique = true)
    private Long chatId;

    @Column(nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @JsonProperty("last_name")
    private String lastName;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_language")
    @JsonProperty("primary_language")
    private Language primaryLanguage;

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

    public void addSourcePages(List<SourcePage> sourcePages) {
        if (this.sourcePages == null || this.sourcePages.isEmpty()) {
            this.sourcePages = sourcePages;
        } else {
            this.sourcePages.addAll(sourcePages);
        }
    }

    public enum Status {
        ENABLED, DISABLED
    }
}
