package com.contact.homework02.dao;

import com.contact.homework02.data.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContactRepositoryImpl implements ContactRepository {
    List<Contact> contacts = new ArrayList<>();

    @Autowired
    private RedisTemplate<String, Contact> redisTemplate;

    @Override
    public List<Contact> findAll() {
        return redisTemplate.opsForList().range("contact", 0, -1);
//        redisTemplate.opsForList().leftPush("contact", new Contact());
//        return contacts;
    }

    @Override
    public void save(Contact contact) {
//        redisTemplate.opsForList().rightPush("contact", contact);
//        contacts.add(contact);
        redisTemplate.opsForList().rightPush("contact", contact);
    }

    @Override
    public void clear() {
        redisTemplate.delete("contact");
//        contacts.clear();
    }
}
