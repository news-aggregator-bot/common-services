package vlad110kg.news.aggregator.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "platform")
public class Platform {

    @Id
    private String name;
}
