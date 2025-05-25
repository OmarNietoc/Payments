package com.edutech.payments.service;

import com.edutech.payments.client.CourseClient;
import com.edutech.payments.client.UserClient;
import com.edutech.payments.controller.response.MessageResponse;
import com.edutech.payments.dto.PaymentDto;
import com.edutech.payments.exception.ResourceNotFoundException;
import com.edutech.payments.model.Payment;
import com.edutech.payments.model.PaymentStatus;
import com.edutech.payments.repository.PaymentRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserClient userClient;
    private final CourseClient courseClient;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado con ID: " + id));
    }

    public ResponseEntity<MessageResponse> createPayment(PaymentDto dto) {

        validateUser(dto.getIdUser());
        validateCourse(dto.getIdCourse());

        Payment payment = new Payment();
        payment.setTransactionCode(dto.getTransactionCode());
        payment.setIdUser(dto.getIdUser());
        payment.setIdCourse(dto.getIdCourse());
        payment.setAmount(dto.getAmount());
        payment.setTransactionDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);

        paymentRepository.save(payment);

        return ResponseEntity.ok(new MessageResponse("Pago creado exitosamente."));
    }

    public void deletePaymentById(Long id) {
        Payment payment = getPaymentById(id);
        paymentRepository.delete(payment);
    }

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }

    private void validateUser(Long userId) {
        try {
            userClient.getUserById(userId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Usuario con ID " + userId + " no existe.");
        }
    }

    private void validateCourse(Long courseId) {
        try {
            courseClient.getCourseById(courseId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Curso con ID " + courseId + " no existe.");
        }
    }
}
