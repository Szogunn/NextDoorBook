package pl.orange.NextDoorBook.role;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    public void initialize() {
        for (ERole roleName : ERole.values()) {
            if (!roleRepository.existsByName(roleName)) {
                Role role = new Role(roleName);
                roleRepository.save(role);
            }
        }
    }
}
