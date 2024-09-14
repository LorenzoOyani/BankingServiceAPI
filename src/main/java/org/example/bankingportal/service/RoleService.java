package org.example.bankingportal.service;

import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.entities.Permissions;
import org.example.bankingportal.entities.Role;
import org.example.bankingportal.repository.PermissionsRepository;
import org.example.bankingportal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RoleService {

    @Autowired
    private  RoleRepository roleRepository;

    @Autowired
    private PermissionsRepository permissionsRepository;

    public Role createRoles(String name) {
        Role role = new Role();
        role.setName(name);

        return roleRepository.save(role);
    }

    private Role addPermisssionToRole(Long roleId, Long permissionId) {
        log.info("adding permissions to role {}", roleId);
        Optional<Role> roles = Optional.of(roleRepository.findRoleById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("role not found!")));
        Set<Permissions> permissions = permissionsRepository.findAllById(permissionId);

        roles.get().getPermissions().addAll(permissions);
        log.info("permissions added to role {}", roleId);
        return roleRepository.save(roles.get());
    }

    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }

}
