package com.bxrbasov.app.dto.Contact;

import com.bxrbasov.app.dto.User.UserDto;
import com.bxrbasov.app.entity.User;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ContactDto {
    private UserDto first_user;
    private UserDto second_user;
}
