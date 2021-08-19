package com.example.controller;


import com.example.mapper.UserMapper;
import com.example.models.User;
import com.example.models.ReqUser;
import com.example.models.TokenBody;
import com.example.service.*;
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
   private JWTManager jwt;
   private Bcrypt bcrypt;

   public UserController(UserMapper userMapper, JWTManager jwt, Bcrypt bcrypt) {
       this.userMapper = userMapper;
       this.jwt = jwt;
       this.bcrypt = bcrypt;
   }

   @GetMapping("/all")
   public List<User> getAll() {
       return userMapper.findAll();
   }

   @PostMapping("/create")
   public ResponseEntity<Map<String, String>> createUser(@RequestBody ReqUser req) {
       User user = new User();
       user.setUsername(req.getUsername());
       String hashpassword = bcrypt.HashPassword(req.getPassword()); 
       user.setPassword(hashpassword);
       userMapper.create(user);
       Map<String, String> map = new HashMap<>();
       map.put("result", "success");
       return new ResponseEntity<>(map, HttpStatus.OK);
   }

   @PostMapping("/login")
   public ResponseEntity<Map<String, String>> LoginUser(@RequestBody ReqUser req) {
    User user = new User();
    //JWTManager jwt = new JWTManager();
    user = userMapper.findOne(req.getUsername());
    Boolean res = bcrypt.CompareHash(req.getPassword(), user.getPassword());
    if (res) {
    String token = jwt.CreateToken(user.getUsername());

    Map<String, String> map = new HashMap<>();
    map.put("result", token);
    return new ResponseEntity<>(map, HttpStatus.OK);
    } else {
        Map<String, String> map = new HashMap<>();
        map.put("result", "password is not correct");
        return new ResponseEntity<>(map, HttpStatus.OK);        
    }
   }

   @PostMapping("/verify")
   public ResponseEntity<Map<String, String>> getOauthid(@RequestBody TokenBody req) {
        //JWTManager jwt = new JWTManager();

        String payload = jwt.VerifyToken(req.getXauth());
        Map<String, String> map = new HashMap<>();
        map.put("result", payload);
        return new ResponseEntity<>(map, HttpStatus.OK);
   }
}