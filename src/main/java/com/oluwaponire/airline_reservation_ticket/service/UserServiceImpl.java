package com.oluwaponire.airline_reservation_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.UserDto;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;
import com.oluwaponire.airline_reservation_ticket.security.JwtTokenUtil;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public User registerUser(UserDto userDto) {
        if (userRepository.findByUsername(userDto.getUsername()) != null) {
            throw new RuntimeException("Username already exits");
        }

        if (!userDto.getPassword().equals(userDto.getRetypePassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        User user = new User();
        user.setTitle(userDto.getTitle());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setGender(userDto.getGender());
        user.setNationality(userDto.getNationality());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles("ROLE_USER");
        return userRepository.save(user);
    }

    @Override
    public User getUserProfile(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User updateUserProfile(String username, UserDto userDto) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setTitle(userDto.getTitle());
        user.setDateOfBirth(userDto.getDateOfBirth());
        user.setGender(userDto.getGender());
        user.setNationality(userDto.getNationality());

        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            if (!userDto.getPassword().equals(userDto.getRetypePassword())) {
                throw new RuntimeException("Passwords do not match");
            }
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserProfile(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    @Override
    public String loginUser(UserDto userDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
        return jwtTokenUtil.generateToken(userDetails);
    }

}
