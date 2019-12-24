package application.data.repository;

import application.data.model.NewsImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsImageRepository extends JpaRepository<NewsImage,Integer> {
}
