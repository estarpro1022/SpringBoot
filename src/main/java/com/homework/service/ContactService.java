package com.homework.service;

import com.homework.data.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAll();

    void add(Contact contact);
}
