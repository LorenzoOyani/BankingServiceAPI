package org.example.bankingportal.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.bankingportal.entities.Permissions;
import org.example.bankingportal.repository.PermissionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class PermissionService {

    private final PermissionsRepository permissionsRepository;

    @Autowired
    public PermissionService(PermissionsRepository permissionsRepository) {
        this.permissionsRepository = permissionsRepository;
    }

    public Permissions createPermissions(String permissions) {
        log.info("new added permissions {}", permissions);
        Permissions newPermissions = new Permissions().setName(permissions);
        return permissionsRepository.save(newPermissions);

    }

    public Set<Permissions> getAllPermissions(Long permissionsId) {
        log.info("get permissions {}", permissionsId);
        Set<Permissions> permissionObject = new HashSet<>();

        Optional<Permissions> permissions = permissionsRepository.findById(permissionsId);

        permissions.ifPresent(permissionObject::add);
        return permissionObject;
    }

    @Transactional
    public void deletePermissions(Long permissionsId) {
        log.info("delete permissions {}", permissionsId);
        if (permissionsRepository.existsById(permissionsId)) {
            permissionsRepository.deleteById(permissionsId);
        } else {
            throw new IllegalArgumentException("Permission id " + permissionsId + " does not exist");
        }
    }

    public Permissions updatePermissions(Long permissionsId, String newPermissions) {
        log.info("update permissions {}", permissionsId);

        Optional<Permissions> permissionsObject = permissionsRepository.findById(permissionsId);
        if (permissionsObject.isPresent()) {
            Permissions permissions = permissionsObject.get();
            permissions.setName(newPermissions);
            return permissionsRepository.save(permissions);
        } else {
            throw new IllegalArgumentException("Permission id " + permissionsId + " does not exist");
        }
    }

}
