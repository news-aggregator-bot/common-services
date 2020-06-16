package vlad110kg.news.aggregator.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "source_page_content_tag")
@EqualsAndHashCode(callSuper = true)
public class SourcePageContentTag extends DatedEntity {

    @Column(nullable = false)
    private String main;

    @Column(nullable = false)
    private String title;

    @Column()
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_source")
    private Source source;
}
