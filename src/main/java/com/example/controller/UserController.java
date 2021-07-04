package com.example.controller;


import com.example.mapper.UserMapper;
import com.example.models.User;
import com.example.models.ReqUser;
import com.example.models.TokenBody;
import com.example.service.JWTManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*", allowedHeaders="*")
public class UserController {

   private UserMapper userMapper;

   public UserController(UserMapper userMapper) {
       this.userMapper = userMapper;
   }

   @GetMapping("/all")
   public List<User> getAll() {
       return userMapper.findAll();
   }

   @PostMapping("/create")
   public ResponseEntity<Map<String, String>> createUser(@RequestBody ReqUser req) {
       User user = new User();
       user.setUsername(req.getUsername());
       user.setPassword(req.getPassword());
       userMapper.create(user);
       Map<String, String> map = new HashMap<>();
       map.put("result", "success");
       return new ResponseEntity<>(map, HttpStatus.OK);
   }

   @PostMapping("/getoauth")
   public ResponseEntity<Map<String, String>> getOauthid(@RequestBody TokenBody req) {
        JWTManager jwt = new JWTManager();

        String oauthid = jwt.decodeJWTToken(req.getXauth());
        Map<String, String> map = new HashMap<>();
        map.put("result", oauthid);
        return new ResponseEntity<>(map, HttpStatus.OK);
   }
}