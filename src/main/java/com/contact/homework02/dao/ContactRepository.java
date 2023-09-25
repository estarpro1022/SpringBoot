package com.contact.homework02.dao;

import com.contact.homework02.data.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();

    void save(Contact contact);

    void clear();
}
