package com.homework.service;

import com.homework.data.Contact;

public interface ContactService {
    Iterable<Contact> getAll();

    void add(Contact contact);
}
