package com.oluwaponire.airline_reservation_ticket.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oluwaponire.airline_reservation_ticket.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByRegistrationDateBetween(Date startDate, Date endDate);

}

