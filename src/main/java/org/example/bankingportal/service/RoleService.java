package org.example.bankingportal.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    private final RoleRepository roleRepository;

    private final PermissionsRepository permissionsRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, PermissionsRepository permissionsRepository) {
        this.roleRepository = roleRepository;
        this.permissionsRepository = permissionsRepository;
    }

    public Role createRoles(String name) {
        Role role = new Role();
        role.setName(name);

        return roleRepository.save(role);
    }

    public Role addPermisssionToRole(Long roleId, Long permissionId) {
        log.info("adding permissions to role {}", roleId);
        Optional<Role> roles = Optional.of(roleRepository.findRoleById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("role not found!")));
        Set<Permissions> permissions = permissionsRepository.findAllById(permissionId);

        roles.get().getPermissions().addAll(permissions);
        log.info("permissions added to role {}", roleId);
        return roleRepository.save(roles.get());
    }

    @Transactional
    public Role updateRole(Long roleId, String name, Set<Long> permissionIds) {
        log.info("updating role {}", roleId);

        Role roles = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("role not found!"));


        if (name != null && !name.trim().isEmpty()) {
            roles.setName(name);
        }

        if (permissionIds != null && !permissionIds.isEmpty()) {
            Set<Permissions> permissions = new HashSet<>(permissionsRepository.findAllById(permissionIds));

            /*
             * This condition checks the set collections to check if all the permissions are present
             * in the collections
             **/
            if (permissions.size() != permissionIds.size()) {
                throw new EntityNotFoundException("permissionsId invalid");
            }
            roles.setPermissions(permissions);
        }

        return roleRepository.save(roles);
    }


    public void deleteRole(Long roleId) {
        log.info("deleting role {}", roleId);
        Optional<Role> role = Optional.ofNullable(roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Cant find role with id {}",
                        new Throwable(String.valueOf(roleId)))));
        role.ifPresent(roleRepository::delete);
    }


    public Set<Role> getAllRoles() {
        return new HashSet<>(roleRepository.findAll());
    }


}
