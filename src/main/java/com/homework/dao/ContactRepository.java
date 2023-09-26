package com.homework.dao;

import com.homework.data.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();

    void save(Contact contact);

    void clear();
}
