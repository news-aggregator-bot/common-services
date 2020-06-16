package vlad110kg.news.aggregator.repository;

import vlad110kg.news.aggregator.entity.CategoryLocalisation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryLocalisationRepository extends JpaRepository<CategoryLocalisation, Long> {
}
