package pl.orange.NextDoorBook.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleRepository {

    private final IRoleRepository iRoleRepository;


    public Optional<Role> findByName(ERole name) {
        return iRoleRepository.findByName(name);
    }
    public Role save(Role role) {
        return iRoleRepository.save(role);
    }
    public boolean existsByName(ERole name) {
        return iRoleRepository.existsByName(name);
    }
}
