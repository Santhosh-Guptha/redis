package com.santhosh.redis.controller;

import com.santhosh.redis.entity.User;
import com.santhosh.redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
@EnableCaching
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public User save(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping
    public List<User> getAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "User",unless = "#result.price >10")
    //it helps the api by not hitting the db again and again , once the api hits then it will store the data in cache
    public User getUser(@PathVariable int id){
        return userRepository.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id){
        return userRepository.deleteUser(id);
    }

}
