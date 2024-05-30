package com.oluwaponire.airline_reservation_ticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.oluwaponire.airline_reservation_ticket.entity.Payment;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;

public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    public void sendRegistrationConfirmationEmail(User user) {
        String emailBody = constructRegistrationConfirmationEmailBody(user);
        sendEmail(user.getEmail(), "Registration Confirmation", emailBody);
    }

    public void sendReservationConfirmationEmail(User user, Reservation reservation) {
        String emailBody = constructReservationConfirmationEmailBody(user, reservation);
        sendEmail(user.getEmail(), "Reservation Confirmation", emailBody);
    }

    public void sendPaymentReceiptEmail(User user, Payment payment) {
        String emailBody = constructPaymentReceiptEmailBody(user, payment);
        sendEmail(user.getEmail(), "Payment Receipt", emailBody);
    }

    // Implement methods for other types of emails

    private String constructRegistrationConfirmationEmailBody(User user) {
        String emailBody = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; }" +
            "h1 { color: #007bff; }" +
            "p { color: #333; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<h1>Welcome to our airline reservation system!</h1>" +
            "<p>Dear " + user.getFirstName() + ",</p>" +
            "<p>Thank you for registering with us. Your account has been successfully created.</p>" +
            "<p>Best regards,<br/>Your Airline Team</p>" +
            "</body>" +
            "</html>";
    return emailBody;
    }

    private String constructReservationConfirmationEmailBody(User user, Reservation reservation) {
        String emailBody = "<!DOCTYPE html>" +
        "<html>" +
        "<head>" +
        "<style>" +
        "body { font-family: Arial, sans-serif; }" +
        "h1 { color: #007bff; }" +
        "p { color: #333; }" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<h1>Reservation Confirmation</h1>" +
        "<p>Dear " + user.getFirstName() + ",</p>" +
        "<p>Your reservation has been confirmed. Below are the flight details:</p>" +
        "<p>Flight Number: " + reservation.getFlight() + "</p>" +
        "<p>Departure Date: " + reservation.getDepartDate() + "</p>" +
        "<p>Return Date: " + reservation.getReturnDate() + "</p>" +
        // Add more reservation details as needed
        "<p>Best regards,<br/>Your Airline Team</p>" +
        "</body>" +
        "</html>";
return emailBody;
    }

    private String constructPaymentReceiptEmailBody(User user, Payment payment) {
        String emailBody = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<style>" +
            "body { font-family: Arial, sans-serif; }" +
            "h1 { color: #007bff; }" +
            "p { color: #333; }" +
            "</style>" +
            "</head>" +
            "<body>" +
            "<h1>Payment Receipt</h1>" +
            "<p>Dear " + user.getFirstName() + ",</p>" +
            "<p>Your payment of $" + payment.getAmount() + " has been received successfully.</p>" +
            "<p>Transaction ID: " + payment.getTransactionId() + "</p>" +
            // Add more payment details as needed
            "<p>Best regards,<br/>Your Airline Team</p>" +
            "</body>" +
            "</html>";
    return emailBody;
    }

}
