package application.data.service;

import application.data.model.Author;
import application.data.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public void addNewAuthor(Author author) {
        authorRepository.save(author);
    }

    @Transactional
    public void addNewListAuthor(List<Author> authorList) {
        authorRepository.save(authorList);
    }

    public Author findOne(int id) {
        return authorRepository.findOne(id);
    }

    public boolean updateAuthor(Author author) {
        try {
            authorRepository.save(author);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteAuthor(int id){
        try{
            authorRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}

