package com.bxrbasov.app.service;

import com.bxrbasov.app.dao.ContactDao;
import com.bxrbasov.app.dto.Contact.ContactDto;
import com.bxrbasov.app.entity.Contact;
import java.util.List;

public class ContactService {
    private static ContactDao contactDao = ContactDao.getInstance();
    private static UserService userService = UserService.getInstance();

    private static final ContactService instance = new ContactService();

    public List<ContactDto> getAllByUserId(Integer id) {
        List<Contact> contactList = contactDao.getAllByUserId(id);
        List<ContactDto> contactDtoList = contactList.stream().map(contact -> buildContactDto(contact)).toList();
        return contactDtoList;
    }

    public ContactDto buildContactDto(Contact contact) {
        return ContactDto.builder()
                .first_user(userService.buildUserDto(contact.getFirst_user()))
                .second_user(userService.buildUserDto(contact.getSecond_user()))
                .build();
    }

    public static ContactService getInstance() {
        return instance;
    }
}
