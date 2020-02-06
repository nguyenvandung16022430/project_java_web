package application.controller.web;

import application.data.model.Product;
import application.data.model.ProductImage;
import application.data.service.ProductService;
import application.model.viewModel.common.ProductImageVM;
import application.model.viewModel.common.ProductVM;
import application.model.viewModel.productdetail.ProductDetailVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping(path = "/product")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;
    @GetMapping("/{productId}")
    public String ProductDetail(@PathVariable Integer productId, Model model,
                                @Valid @ModelAttribute("productname")ProductVM productname){
        ProductDetailVM vm = new ProductDetailVM();
        Product productEntity = productService.findOne(productId);
        ProductVM productVM = new ProductVM();
        Random random = new Random();
        List<ProductVM> productVMList = new ArrayList<>();
        if(productEntity != null){
            System.out.println("abc");
            Integer categoryId = productEntity.getProductCategories().get(random.nextInt(productEntity.getProductCategories().size())).getCategoryId();
            System.out.println(categoryId);
            productVM.setId(productEntity.getId());
            productVM.setName(productEntity.getName());
            productVM.setShortDesc((productEntity.getShortDesc()));
            productVM.setPrice(productEntity.getPrice());
            productVM.setMainImage(productEntity.getMainImage());

            List<ProductImageVM> productImageVMS = new ArrayList<>();
            for(ProductImage productImage : productEntity.getProductImagelist()) {
                ProductImageVM productImageVM = new ProductImageVM();
                productImageVM.setLink(productImage.getLink());

                productImageVMS.add(productImageVM);
            }

            productVM.setProductImageVMS(productImageVMS);
            List<Product> productList = productService.getListProductbyCategoryIdContaining(categoryId);
            for(int i=0;i<productList.size();i++){
                System.out.println( productList.get(i).getName());
            }
            for(Product product : productService.getListProductbyCategoryIdContaining(categoryId)){
                ProductVM productVMs= new ProductVM();
                productVMs.setId(product.getId());
                productVMs.setName(product.getName());
                productVMs.setMainImage(product.getMainImage());
                productVMs.setShortDesc(product.getShortDesc());
                productVMs.setCreatedDate(product.getPublishedDate());
                productVMs.setPrice(product.getPrice());
                productVMList.add(productVMs);
            }

        }
        vm.setProductVMList(productVMList);
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setProductVM(productVM);
        model.addAttribute("vm",vm);
        return "/product_detail";
        }
    }

