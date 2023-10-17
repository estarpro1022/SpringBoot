package com.homework.service;

import com.homework.dao.ContactRepository;
import com.homework.data.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Iterable<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public void add(Contact contact) {
        contactRepository.save(contact);
    }
}
