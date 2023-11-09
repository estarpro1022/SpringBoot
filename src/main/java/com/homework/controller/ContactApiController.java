package com.homework.controller;

import com.homework.dao.ContactRepository;
import com.homework.data.Contact;
import com.homework.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class ContactApiController {
    @Autowired
    private ContactService contactService;

    @GetMapping(path = "/contacts", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Contact> getContacts() {
        return contactService.getAll();
    }

    @GetMapping("/contact/{id}")
//    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Contact> getContact(@PathVariable Long id) {
        Optional<Contact> result = contactService.get(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/contact", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.add(contact);
    }

    @PutMapping(value = "/contact/{id}", consumes = "application/json")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        return contactService.substitute(contact);
    }

    @DeleteMapping("/contact/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@PathVariable Long id) {
        contactService.delete(id);
    }
}
