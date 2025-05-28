package com.edutech.payments.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El código de transacción no puede ser nulo")
    private String transactionCode;

    @NotNull(message = "El ID de la inscripción no puede ser nulo")
    private Long enrollmentId;

    @NotNull(message = "El monto no puede ser nulo")
    private BigDecimal amount;

    @NotNull(message = "La fecha de transacción no puede ser nula")
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado de la transacción no puede ser nulo")
    private PaymentStatus status;

    public Payment(String transactionCode, Long enrollmentId, BigDecimal amount,
                   LocalDateTime transactionDate, PaymentStatus status) {
        this.transactionCode = transactionCode;
        this.enrollmentId = enrollmentId;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.status = status;
    }
}
