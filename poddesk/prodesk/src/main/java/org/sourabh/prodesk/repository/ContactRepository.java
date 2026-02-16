package org.sourabh.prodesk.repository;

import org.sourabh.prodesk.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
