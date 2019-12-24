package application.data.service;

import application.data.model.Role;
import application.data.model.User;
import application.data.model.UserRole;
import application.data.repository.RoleRepository;
import application.data.repository.UserRepository;
import application.data.repository.UserRoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public void addNewUser(User user){
        userRepository.save(user);
    }

    @Transactional
    public void addNewListUser(List<User> user){
        userRepository.save(user);
    }

    public User findOne(int id){
     return  userRepository.findOne(id);
    }

    public boolean updateUser(User user){
        try{
            userRepository.save(user);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean daleteUser(int id){
        try{
            userRepository.delete(id);
            return true;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<Role> getListRole() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public User findUserByUsername(String username) {
        return StreamSupport.stream(userRepository.findByUsername(username).spliterator(), false).findFirst().orElse(null);
    }

    public User findUserByEmail(String email) {
        return StreamSupport.stream(userRepository.findByEmail(email).spliterator(), false).findFirst().orElse(null);
    }

    public List<Role> getListActiveRole(int userId) {
        List<UserRole> userRoleList = StreamSupport.stream(userRoleRepository.findRoleOfUser(userId).spliterator(), false)
                                                   .collect(Collectors.toList());

        return getListRole().stream().filter(role -> {
            return (userRoleList.stream().filter(userRole -> userRole.getRoleId() == role.getId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }

}
