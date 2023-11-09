package com.homework.service;

import com.homework.dao.ContactRepository;
import com.homework.data.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> get(Long id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact add(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact substitute(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public void delete(Long id) {
        contactRepository.deleteById(id);
    }
}
