package com.homework.utils;

import com.homework.data.Contact;

import java.util.ArrayList;
import java.util.List;

public class GetContact {
    public static List<Contact> expectedContacts() {
        List<Contact> contacts = new ArrayList<>();

        Contact contact1 = new Contact();
        contact1.setFirstName("san");
        contact1.setLastName("zhang");
        contact1.setPhoneNumber("12345678901");
        contact1.setEmailAddress("zhangsan@163.com");
        contacts.add(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstName("si");
        contact2.setLastName("li");
        contact2.setPhoneNumber("12345678902");
        contact2.setEmailAddress("lisi@163.com");
        contacts.add(contact2);

        return contacts;
    }
}
