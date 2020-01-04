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
        if(productEntity != null){
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
        }
        vm.setLayoutHeaderVM(this.getLayoutHeaderVM());
        vm.setProductVM(productVM);
        model.addAttribute("vm",vm);
        return "/product_detail";
        }
    }

