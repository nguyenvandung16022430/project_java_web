package application.controller.web;

import application.data.model.Category;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.ProductVM;
import application.model.viewModel.home.HomeLandingVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping(value = "")
    public String home(Model model,
                       @Valid @ModelAttribute("productname") ProductVM productName,
                       @RequestParam(name = "categoryId",required = false) Integer categoryId,
                       @RequestParam(name = "page",required = false,defaultValue = "0") Integer page,
                       @RequestParam(name = "size",required = false,defaultValue = "12")Integer size,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       final Principal principal){
        this.checkCookie(response,request,principal);
        HomeLandingVM vm = new HomeLandingVM();
        Pageable pageable =  new PageRequest(page,size);
        Page<Product> productPage = null;
        if(categoryId !=null){
                productPage = productService.getListProductbyCategoryOrProductNameContaining(pageable,categoryId);
        } else if (productName.getName() != null && !productName.getName().isEmpty()) {
            String nameProduct= productName.getName().trim();
             productPage = productService.getListProductbyProductNameContaining(pageable,nameProduct);
             }
        else{
            productPage = productService.getListProductbyCategoryOrProductNameContaining(pageable,null);
        }
        List<ProductVM> productVMList = new ArrayList<>();
        for(Product product : productPage.getContent()){
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getPublishedDate());
            productVM.setPrice(product.getPrice());
            productVMList.add(productVM);

        }
        List<Product> hotProductList = productService.getListHotProduct();
        List<ProductVM> hotProductVMlist = new ArrayList<>();
        for(Product product : hotProductList){
            ProductVM productVM = new ProductVM();
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getPublishedDate());
            productVM.setPrice(product.getPrice());
            hotProductVMlist.add(productVM);
        }
        vm.setProductHotVMList(hotProductVMlist);
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setProductVMList(productVMList);
        model.addAttribute("vm",vm);
        model.addAttribute("page",productPage);
        return "home";
    }

}
