package vlad110kg.news.aggregator.repository;

import vlad110kg.news.aggregator.entity.Reader;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
}
