package org.example.bankingportal.payload;

import lombok.Getter;

import java.util.Set;

@Getter
public class UpdateRoleRequest {

    String  name;
    Set<Long> permissionsId;
}
