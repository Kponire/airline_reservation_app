package com.oluwaponire.airline_reservation_ticket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oluwaponire.airline_reservation_ticket.dto.PaymentDto;
import com.oluwaponire.airline_reservation_ticket.entity.Payment;
import com.oluwaponire.airline_reservation_ticket.entity.Reservation;
import com.oluwaponire.airline_reservation_ticket.entity.User;
import com.oluwaponire.airline_reservation_ticket.repository.PaymentRepository;
import com.oluwaponire.airline_reservation_ticket.repository.ReservationRepository;
import com.oluwaponire.airline_reservation_ticket.repository.UserRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Payment processPayment(PaymentDto paymentDto) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(paymentDto.getReservationId());
        if (!reservationOpt.isPresent()) {
            throw new RuntimeException("Reservation not found");
        }

        Optional<User> userOpt = userRepository.findById(paymentDto.getUserId());
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }

        Payment payment = new Payment();
        payment.setReservation(reservationOpt.get());
        payment.setUser(userOpt.get());
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setAmount(paymentDto.getAmount());
        payment.setTransactionId(paymentDto.getTransactionId());
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePayment(Long id, PaymentDto paymentDto) {
        Optional<Payment> paymentOpt = paymentRepository.findById(id);
        if (!paymentOpt.isPresent()) {
            throw new RuntimeException("Payment not found");
        }

        Payment payment = paymentOpt.get();
        payment.setPaymentMethod(paymentDto.getPaymentMethod());
        payment.setPaymentStatus(paymentDto.getPaymentStatus());
        payment.setPaymentDate(paymentDto.getPaymentDate());
        payment.setAmount(paymentDto.getAmount());
        payment.setTransactionId(paymentDto.getTransactionId());
        return paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    @Override
    public List<Payment> getPaymentsByReservationId(Long reservationId) {
        if (!reservationRepository.existsById(reservationId)) {
            throw new RuntimeException("Reservation not found");
        }
        return paymentRepository.findByReservationId(reservationId);
    }

    @Override
    public List<Payment> getPaymentsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found");
        }
        return paymentRepository.findByUserId(userId);
    }

    @Override
    public List<Payment> getPaymentsByStatus(String paymentStatus) {
        if (paymentStatus == null || paymentStatus.isEmpty()) {
            throw new RuntimeException("Payment status is required");
        }
        return paymentRepository.findByPaymentStatus(paymentStatus);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

}
