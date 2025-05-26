package com.edutech.payments.controller;

import com.edutech.payments.controller.response.MessageResponse;
import com.edutech.payments.dto.PaymentDto;
import com.edutech.payments.dto.UpdateStatusDto;
import com.edutech.payments.model.Payment;
import com.edutech.payments.model.PaymentStatus;
import com.edutech.payments.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edutech/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // Obtener un pago por ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return ResponseEntity.ok(paymentService.getPaymentById(id));
    }

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<MessageResponse> createPayment(@Valid @RequestBody PaymentDto paymentDto) {
        return paymentService.createPayment(paymentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponse> updatePayment(@PathVariable Long id, @Valid @RequestBody PaymentDto paymentDto) {
        return paymentService.updatePayment(id, paymentDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MessageResponse> updateStatus(@PathVariable Long id, @Valid @RequestBody UpdateStatusDto statusDto) {
        return paymentService.updatePaymentStatus(id, statusDto.getStatus());
    }

    // Eliminar un pago
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deletePayment(@PathVariable Long id) {
        paymentService.deletePaymentById(id);
        return ResponseEntity.ok(new MessageResponse("Pago eliminado exitosamente."));
    }
}
