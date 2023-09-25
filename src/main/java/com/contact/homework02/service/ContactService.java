package com.contact.homework02.service;

import com.contact.homework02.data.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    void add(Contact contact);
}
