package application.data.service;

import application.data.model.AuthorProduct;
import application.data.model.Category;
import application.data.model.Product;
import application.data.repository.AuthorProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductAuthorService {
    @Autowired
    AuthorProductRepository authorProductRepository;
    @Transactional
    public void addNewCategory(AuthorProduct category){
        authorProductRepository.save(category);
    }
    public void addNewListCategory(List<AuthorProduct> listCategory){
        authorProductRepository.save(listCategory);
    }
    public AuthorProduct findOne(int categoryId){
        return authorProductRepository.findOne(categoryId);
    }
    public boolean updateCategory(AuthorProduct category){
        try{
            authorProductRepository.save(category);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public  boolean daleteCategory(int categoryId){
        try {
            authorProductRepository.delete(categoryId);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public List<AuthorProduct> getListAllCategories() {
        try {
            return authorProductRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    }

