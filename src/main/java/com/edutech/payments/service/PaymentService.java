package com.edutech.payments.service;

import com.edutech.payments.client.CourseClient;
import com.edutech.payments.client.UserClient;
import com.edutech.payments.controller.response.MessageResponse;
import com.edutech.payments.dto.PaymentDto;
import com.edutech.payments.exception.ResourceNotFoundException;
import com.edutech.payments.exception.ConflictException;
import com.edutech.payments.model.Payment;
import com.edutech.payments.model.PaymentStatus;
import com.edutech.payments.repository.PaymentRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

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
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado: " + id));
    }

    public ResponseEntity<MessageResponse> createPayment(PaymentDto dto) {

        String transactionCode = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();

        validateUser(dto.getIdUser());
        validateCourse(dto.getIdCourse());

        Payment payment = new Payment();
        payment.setTransactionCode(transactionCode);
        payment.setIdUser(dto.getIdUser());
        payment.setIdCourse(dto.getIdCourse());
        payment.setAmount(dto.getAmount());
        payment.setTransactionDate(LocalDateTime.now());
        payment.setStatus(PaymentStatus.PENDING);

        paymentRepository.save(payment);

        return ResponseEntity.ok(new MessageResponse("Pago creado exitosamente."));
    }

    public ResponseEntity<MessageResponse> updatePayment(Long id,PaymentDto dto) {

        validateUser(dto.getIdUser());
        validateCourse(dto.getIdCourse());

        Payment payment = getPaymentById(id);
        payment.setIdUser(dto.getIdUser());
        payment.setIdCourse(dto.getIdCourse());
        payment.setAmount(dto.getAmount());

        paymentRepository.save(payment);

        return ResponseEntity.ok(new MessageResponse("Pago actualizado exitosamente."));
    }

    public ResponseEntity<MessageResponse> updatePaymentStatus(Long id, PaymentStatus newStatus) {
        Payment payment = getPaymentById(id);
        payment.setStatus(newStatus);
        paymentRepository.save(payment);
        return ResponseEntity.ok(new MessageResponse("Estado de pago actualizado a: " + newStatus));
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
            throw new ResourceNotFoundException("Usuario no encontrado.");
        }
    }

    private void validateCourse(Long courseId) {
        try {
            courseClient.getCourseById(courseId);
        } catch (FeignException.NotFound e) {
            throw new ResourceNotFoundException("Curso no encontrado");
        }
    }
}
