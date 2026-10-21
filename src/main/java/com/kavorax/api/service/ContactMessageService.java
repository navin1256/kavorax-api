package com.kavorax.api.service;

import com.kavorax.api.dto.ContactMessageRequest;
import com.kavorax.api.entity.ContactMessage;
import com.kavorax.api.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    @Transactional
    public ContactMessage createContactMessage(ContactMessageRequest request) {
        ContactMessage contactMessage = new ContactMessage();
        contactMessage.setName(request.getName().trim());
        contactMessage.setEmail(request.getEmail().trim().toLowerCase());
        contactMessage.setPhone(request.getPhone());
        contactMessage.setSubject(request.getSubject());
        contactMessage.setMessage(request.getMessage().trim());
        
        return contactMessageRepository.save(contactMessage);
    }
}
