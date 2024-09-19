package org.example.bankingportal.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.example.bankingportal.entities.Role;
import org.example.bankingportal.payload.UpdateRoleRequest;
import org.example.bankingportal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @Operation(
            summary = "create roles",
            description = "create roles name a role with a specified name, available to users with name ADMIN",
            security = @SecurityRequirement(name = "bearerAuth"))

    @io.swagger.v3.oas.annotations.responses.ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role successfully created", content = @Content(schema = @Schema(implementation = Role.class))),
            @ApiResponse(responseCode = "400", description = "Invalid role name", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden - You don't have the required permissions", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody String roleName) {
        Role role = roleService.createRoles(roleName);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN') or hasAuthority('USER')")
    @PutMapping("{Ids}/update")
    public ResponseEntity<Role> updateRole(@PathVariable Long Ids, @RequestBody UpdateRoleRequest updateRoleRequest) {
        Role roles = roleService.updateRole(Ids, updateRoleRequest.getName(), updateRoleRequest.getPermissionsId());
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{ids}/addPermissions/{permissionsId}")
    public ResponseEntity<Role> addPermissions(
            @PathVariable Long ids,
            @PathVariable Long permissionsId) {

        Role role = roleService.addPermisssionToRole(ids, permissionsId);

        return new ResponseEntity<>(role, HttpStatus.OK);

    }


}
