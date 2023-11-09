package com.homework.service;

import com.homework.data.Contact;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<Contact> getAll();

    Optional<Contact> get(Long id);

    Contact add(Contact contact);

    Contact substitute(Contact contact);

    void delete(Long id);
}
