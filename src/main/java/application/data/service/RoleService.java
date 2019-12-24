package application.data.service;

import application.data.model.Role;
import application.data.repository.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    private static final Logger logger = LogManager.getLogger(RoleService.class);
    public void addNewRole(Role role){
        roleRepository.save(role);
    }
    @Transactional
    public void addListNewRole(List<Role> role){
        roleRepository.save(role);
    }
}
