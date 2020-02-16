package application.data.repository;

import application.data.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
        @Query("select da from dbo_author da"+
                " where(:authorName is NULL or UPPER(da.name) like concat('%',UPPER(:authorName),'%'))")
    Page<Author> getAllAuthorOrByAuthorName(Pageable page, @Param(value = "authorName") String authorName);

    @Query("select da from dbo_author da")
    List<Author> getListAllAuthor();
}
