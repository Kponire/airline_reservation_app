package com.oluwaponire.airline_reservation_ticket.service;

import com.oluwaponire.airline_reservation_ticket.dto.UserDto;
import com.oluwaponire.airline_reservation_ticket.entity.User;

public interface UserService {

    User registerUser(UserDto userDto);

    String loginUser(UserDto userDto);
    
    User getUserProfile(String username);

    User updateUserProfile(String username, UserDto userDto);

    boolean deleteUserProfile(String username);

}
