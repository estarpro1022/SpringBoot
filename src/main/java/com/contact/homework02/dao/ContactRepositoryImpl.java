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

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    @Override
    public void save(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public void clear() {
        contacts.clear();
    }
}
