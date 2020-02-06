package application.controller.web;

import application.data.service.CategoryService;
import application.data.service.ProductService;
import application.model.viewModel.admin.HomeAdminVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class AdminController extends BaseController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String admin(Model model){
        HomeAdminVM vm = new HomeAdminVM();
        vm.setLayoutHeaderAdminVM(this.getLayoutHeaderAdminVM());
        model.addAttribute("vm",vm);
        return "/admin/home";
    }
}
