package org.example.bankingportal.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.bankingportal.Util.LoggedUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final LoggedUser loggedUser;

    @GetMapping("/findUser")
    public ResponseEntity<Map<String, Object>> getUser() {
        Map<String, Object> map = loggedUser.getLoggedInUsers();
        return ResponseEntity.ok(map);
    }
}
