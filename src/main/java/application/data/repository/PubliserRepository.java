package application.data.repository;

import application.data.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PubliserRepository extends JpaRepository<Publisher,Integer> {
}
