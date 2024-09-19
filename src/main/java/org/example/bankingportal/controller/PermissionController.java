package org.example.bankingportal.controller;

import org.example.bankingportal.entities.Permissions;
import org.example.bankingportal.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/createPermissions")
    public ResponseEntity<Permissions> createPermissions(String name) {
        Permissions permissions = permissionService.createPermissions(name);
        return new ResponseEntity<>(permissions, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/updatePermissions")
    public ResponseEntity<Permissions> updatePermissions(@PathVariable Long id, String newPermissions) {
        Permissions permission = permissionService.updatePermissions(id, newPermissions);
        return new ResponseEntity<>(permission, HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Permissions> deletePermissions(@PathVariable Long id) {
        permissionService.deletePermissions(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/readPermission")
    public ResponseEntity<Set<Permissions>> readPermissions(@PathVariable Long id) {
        Set<Permissions> permissionsSet = permissionService.getAllPermissions(id);

        return new ResponseEntity<>(permissionsSet, HttpStatus.OK);
    }

}
