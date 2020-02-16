package application.controller.web;

import application.data.model.Author;
import application.data.model.Category;
import application.data.model.Order;
import application.data.model.Product;
import application.data.service.*;
import application.model.viewModel.admin.*;
import application.model.viewModel.common.AuthorVM;
import application.model.viewModel.common.CategoryVM;
import application.model.viewModel.common.ProductVM;
import application.model.viewModel.order.OrderVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("")
    public String admin(Model model){
        HomeAdminVM vm = new HomeAdminVM();
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        model.addAttribute("vm",vm);
        return "/admin/home";
    }
    @GetMapping("/product")
    public String product(Model model,
                          @Valid @ModelAttribute("productname")ProductVM productName,
                          @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                          @RequestParam(name = "size", required = false, defaultValue = "8") Integer size)
    {
        AdminProductVM vm = new AdminProductVM();
        List<Category> categoryList = categoryService.getListAllCategories();
        List<CategoryVM> categoryVMList = new ArrayList<>();
        for(Category category : categoryList){
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setId(category.getId());
            categoryVM.setName(category.getName());
            categoryVMList.add(categoryVM);
        }
        Pageable pageable = new PageRequest(page,size);

        Page<Product> productPage = null;
        if (productName.getName() != null && !productName.getName().isEmpty()) {
            productPage = productService.getListProductbyProductNameContaining(pageable,productName.getName().trim());
            vm.setKeyWord("Find with key: " + productName.getName());
        } else {
            productPage = productService.getListProductbyProductNameContaining(pageable,null);
        }

        List<ProductVM> productVMList = new ArrayList<>();

        for (Product product : productPage.getContent()){
            ProductVM productVM = new ProductVM();
            if(categoryService.findOne(productCategoryService.getcategoryIdByProductid(product.getId())) ==null){
                    productVM.setCategoryName("Unknown");
            }else {
                productVM.setCategoryName(categoryService.findOne(productCategoryService.getcategoryIdByProductid(product.getId())).getName());
            }
            productVM.setId(product.getId());
            productVM.setName(product.getName());
            productVM.setMainImage(product.getMainImage());
            productVM.setPrice(product.getPrice());
            productVM.setShortDesc(product.getShortDesc());
            productVM.setCreatedDate(product.getPublishedDate());
            productVM.setAmount(product.getAmount());

            productVMList.add(productVM);
        }
        vm.setCategoryVMList(categoryVMList);
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setProductVMList(productVMList);
        if(productVMList.size() == 0){
            vm.setKeyWord("not found any product");
        }
        model.addAttribute("vm",vm);
        model.addAttribute("page",productPage);
        return "/admin/product";
    }
    @GetMapping("/category")
    public String category(Model model,
                           @Valid @ModelAttribute("categoryname") CategoryVM categoryName,
                           @RequestParam(name = "page",required = false,defaultValue ="0" )Integer page,
                           @RequestParam(name = "size",required = false,defaultValue = "8") Integer size){
        AdminCategoryVM vm = new AdminCategoryVM();
        Pageable pageable = new PageRequest(page,size);
        Page<Category> categoryPage = null;
        if(categoryName.getName() != null && !categoryName.getName().isEmpty()){
            categoryPage = categoryService.getListCategoryByContainingCategoryName(pageable,categoryName.getName());
        }else {
            categoryPage = categoryService.getListCategoryByContainingCategoryName(pageable,null);
        }
        List<CategoryVM> categoryVMList = new ArrayList<>();
        for (Category category : categoryPage.getContent()){
            CategoryVM categoryVM = new CategoryVM();
            categoryVM.setName(category.getName());
            categoryVM.setId(category.getId());
            categoryVM.setCteatedDate(category.getCreatedDate());
            categoryVM.setShortDesc(category.getShortDesc());
            categoryVMList.add(categoryVM);
        }
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        vm.setCategoryVMList(categoryVMList);

        model.addAttribute("vm",vm);
        model.addAttribute("page",categoryPage);
        return "/admin/category";
    }

@GetMapping("/order")
    public String checkOrder(Model model,
                             @RequestParam(name = "page",required = false,defaultValue ="0" )Integer page,
                             @RequestParam(name = "size",required = false,defaultValue = "8") Integer size,
                             @RequestParam(name = "sort",required = false,defaultValue = "1")Integer sort){
    OrderAdminVM vm = new OrderAdminVM();
    Pageable pageable = new PageRequest(page,size);
    Page<Order> orderPage = orderService.getListOrderByStatusUnconfirmed(pageable,sort);
    List<OrderVM> orderVMList = new ArrayList<>();
    for(Order order : orderPage.getContent()){
        OrderVM orderVM = new OrderVM();
        orderVM.setId(order.getId());
        if(order.getCustomerName()== null){
            orderVM.setCustomerName("unknown");
        }else {
            orderVM.setCustomerName(order.getCustomerName());
        }
        if(order.getEmail() == null){
            orderVM.setEmail("unknown");
        }else {
            orderVM.setEmail(order.getEmail());
        }
        if(order.getUserName() == null){
            orderVM.setUserName("anonymous");
        }else {
            orderVM.setUserName(order.getUserName());
        }
        orderVM.setPhoneNumber(order.getPhoneNumber());
        orderVM.setAddress(order.getAddress());
        orderVM.setCreatedDate(order.getCreatedDate());
        orderVM.setStatusId(order.getStatusId());
        orderVMList.add(orderVM);
    }
    vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
    vm.setOrderVMList(orderVMList);
    model.addAttribute("vm",vm);
    model.addAttribute("page",orderPage);
    return "/admin/order";
}
@GetMapping("/author")
    public String getAuthor(Model model,
                            @Valid @ModelAttribute("authorname")AuthorVM authorVM,
                            @RequestParam(name = "page",required = false,defaultValue ="0" )Integer page,
                            @RequestParam(name = "size",required = false,defaultValue = "8") Integer size){
    AuthorAdminVM vm = new AuthorAdminVM();
    Pageable pageable = new PageRequest(page,size);
    Page<Author> authorPage = null;
    if(authorVM.getName() != null && !authorVM.getName().isEmpty()){
        System.out.println(authorVM.getName());
        authorPage = authorService.getAllAuthorOrByAuthorName(pageable,authorVM.getName());
        vm.setKeyWord("Find with key: " + authorVM.getName());
    }else {
        authorPage = authorService.getAllAuthorOrByAuthorName(pageable,null);
    }
    List<AuthorVM> authorVMList = new ArrayList<>();
    for (Author author : authorPage.getContent()){
        AuthorVM authorVM1 = new AuthorVM();
        authorVM1.setId(author.getId());
        authorVM1.setBirthDay(author.getBirthDay());
        authorVM1.setName(author.getName());
        authorVM1.setProfile(author.getProfile());
        authorVMList.add(authorVM1);
    }
    vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
    vm.setAuthorVMList(authorVMList);
    model.addAttribute("vm",vm);
    model.addAttribute("page",authorPage);
    return "/admin/author";
}
}
