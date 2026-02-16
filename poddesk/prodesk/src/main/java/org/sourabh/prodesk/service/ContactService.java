package org.sourabh.prodesk.service;

import org.sourabh.prodesk.entity.Contact;
import org.sourabh.prodesk.repository.ContactRepository;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository repo;

    public ContactService(ContactRepository repo) {
        this.repo = repo;
    }

    public Contact save(Contact contact) {
        return repo.save(contact);
    }
}
