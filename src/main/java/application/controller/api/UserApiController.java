package application.controller.api;

import application.data.model.User;
import application.data.service.UserService;
import application.model.api.BaseApiResult;
import application.model.dto.ChangePasswordDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserApiController {
    private static final Logger logger = LogManager.getLogger(UserApiController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/change-password")
    public BaseApiResult changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
        BaseApiResult result = new BaseApiResult();
        System.out.println(1);
        try{
            if(changePasswordDTO.getCurrentPassword() != null && changePasswordDTO.getNewPassword() !=null && changePasswordDTO.getConfirmPassword() != null){
                System.out.println(2);
                String userName = SecurityContextHolder.getContext().getAuthentication().getName();
                if(userName == null) {
                    System.out.println(2);
                }
                System.out.println(userName);
                System.out.println(3);
                User userEntity = userService.findUserByUsername(userName);
                if(passwordEncoder.matches(changePasswordDTO.getCurrentPassword(),userEntity.getPasswordHash())==true){
                    System.out.println(4);
                    if(changePasswordDTO.getConfirmPassword().equals(changePasswordDTO.getNewPassword())){
                        userEntity.setPasswordHash(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
                        userService.updateUser(userEntity);
                        result.setSuccess(true);
                        result.setMessage("change password success");
                        return result;
                    }
                }
            }

        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Fail!");
        result.setSuccess(false);
        return result;
        }
    }
