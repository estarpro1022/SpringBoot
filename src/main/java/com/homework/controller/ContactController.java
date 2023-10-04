package com.homework.controller;

import com.homework.data.Contact;
import com.homework.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Slf4j
@Controller
@RequestMapping("/")
//@SessionAttributes("contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @ModelAttribute
    public void addAttribute(Model model) {
//        log.info("contact number: " + contactService.getAll().size());
//        model.addAttribute("id", (long) contactService.getAll().size());
        model.addAttribute("numbers", contactService.getAll().size());
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
    public String addContact(@Valid Contact contact, Errors errors, Model model) {
        if (errors.hasErrors()) {
            log.info("error occurs");
            return "home";
        }

        contact.setId((long) contactService.getAll().size());
        contactService.add(contact);
        log.info("add a Contact: " + contact);
//        contact.setId((Long) model.getAttribute("id"));
//        status.setComplete();
        return "redirect:/";
    }
}
