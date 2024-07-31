package com.santhosh.redis.repository;

import com.santhosh.redis.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepository {

    public static final String HASH_KEY = "User";

    @Autowired
    private RedisTemplate redisTemplate;

    public User save(User user){
        redisTemplate.opsForHash().put(HASH_KEY,user.getId(),user);
        return user;
    }

    public List<User> findAll(){
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public User findUserById(int id){
        System.out.println("From db");
        return (User) redisTemplate.opsForHash().get(HASH_KEY,id);
    }

    public String deleteUser(int id){
        redisTemplate.opsForHash().delete(HASH_KEY,id);
        return "User deleted successfully";
    }

}
