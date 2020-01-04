package application.controller.web;

import application.data.model.Category;
import application.data.service.CategoryService;
import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.LayoutHeaderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


public class BaseController {
    @Autowired
    private CategoryService categoryService;
    private List<CategoryVM> getCategoryVMList(){
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();
        for(Category category : categoryList){
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVM.setCteatedDate(category.getCreatedDate());
            categoryVMList.add(categoryVM);
        }
        return categoryVMList;
    }
    public LayoutHeaderVM getLayoutHeaderVM(){
        LayoutHeaderVM vm = new LayoutHeaderVM();
        vm.setCategoryVMList(this.getCategoryVMList());
        return vm;
    }
}
