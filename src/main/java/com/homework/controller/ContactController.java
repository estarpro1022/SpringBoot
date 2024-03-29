package com.homework.controller;

import com.homework.data.Contact;
import com.homework.service.ContactService;
//import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("contacts", contactService.getAll());
    }

    @ModelAttribute(name = "contact")
    public Contact getContact() {
        return new Contact();
    }

    @GetMapping
    public String getHome() {
        log.info("home view");
        return "home";
    }

    @PostMapping
    public String addContact(@Valid Contact contact, Errors errors) {
        if (errors.hasErrors()) {
            log.info("error occurs");
            return "home";
        }

        contactService.add(contact);
        log.info("add a Contact: " + contact);
        return "redirect:/";
    }
}
