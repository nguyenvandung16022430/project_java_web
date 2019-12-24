package application.data.service;

import application.data.model.Category;
import application.data.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired CategoryRepository categoryRepository;

    @Transactional
        public long getTotalCategories() {
            return categoryRepository.getTotalCategories();
        }
        public void addNewCategory(Category category){
            categoryRepository.save(category);
        }
        public void addNewListCategory(List<Category> listCategory){
            categoryRepository.save(listCategory);
        }
        public Category findOne(int categoryId){
            return categoryRepository.findOne(categoryId);
        }
        public boolean updateCategory(Category category){
            try{
                categoryRepository.save(category);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
            return false;
        }
        public  boolean daleteCategory(int categoryId){
            try {
                categoryRepository.delete(categoryId);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
            return false;
        }
       public List<Category> getListAllCategories() {
           try {
               return categoryRepository.findAll();
           } catch (Exception e) {
               e.printStackTrace();
               return new ArrayList<>();
           }
       }
       }
