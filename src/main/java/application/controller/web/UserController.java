package application.controller.web;

import application.data.model.User;
import application.data.service.UserService;
import application.model.viewModel.common.ProductVM;
import application.model.viewModel.user.UserDetailVM;
import application.model.viewModel.user.UserVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(path = "/user")
public class UserController extends BaseController  {
        private static final Logger logger = LogManager.getLogger(UserController.class);

        @Autowired
    private PasswordEncoder passwordEncoder;

        @Autowired
    private UserService userService;

        @GetMapping("/detail")
    public String getUserDetail(@Valid @ModelAttribute("productname")ProductVM productname,
            Model model, HttpServletResponse response,
                                HttpServletRequest request,
            final Principal principal ){

            System.out.println("chay den day");
            this.checkCookie(response,request,principal);
            UserDetailVM vm = new UserDetailVM();

            UserVM userVM = new UserVM();
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            System.out.println("chay den day 2");
            User userEntity = userService.findUserByUsername(username);
            userVM.setName(userEntity.getName());
            userVM.setAddress(userEntity.getAddress());
            userVM.setAvatar(userEntity.getAvatar());
            userVM.setEmail(userEntity.getEmail());
            userVM.setId(userEntity.getId());
            userVM.setPhoneNumber(userEntity.getPhoneNumber());
            userVM.setGender(userEntity.getGender());
            vm.setCartHeaderVM(this.getCartHeaderVM());
            model.addAttribute("vm",vm);
            model.addAttribute("user",userVM);
            return "user-detail";

        }
        @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("user") User user){
            try {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                User userEntity = userService.findUserByUsername(username);

                userEntity.setAddress(user.getAddress());
                userEntity.setAvatar(user.getAvatar());
                userEntity.setEmail(user.getEmail());
                userEntity.setName(user.getName());
                userEntity.setPhoneNumber(user.getPhoneNumber());
                userEntity.setGender(user.getGender());
                userService.addNewUser(userEntity);
                return "redirect:/user/detail?updateSuccess";
            }catch (Exception e){
                logger.error(e.getMessage());

            }
            return "redirect:/user/detail?updateFail";
        }
}
