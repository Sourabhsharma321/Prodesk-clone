package org.sourabh.prodesk.controller;

import org.sourabh.prodesk.entity.Contact;
import org.sourabh.prodesk.service.ContactService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactService service;

    public ContactController(ContactService service) {
        this.service = service;
    }

    @PostMapping
    public Contact saveContact(@RequestBody Contact contact) {
        System.out.println("Received contact: " + contact.getEmail());
        return service.save(contact);
    }
}
