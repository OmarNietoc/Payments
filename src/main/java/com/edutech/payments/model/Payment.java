package com.edutech.payments.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
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

    @NotNull(message = "transactionCode no puede ser nulo")
    private String transactionCode;

    @NotNull(message = "idUser no puede ser nulo")
    private Long idUser;

    @NotNull(message = "idCourse no puede ser nulo")
    private Long idCourse;

    @NotNull(message = "monto no puede ser nulo")
    private BigDecimal amount;

    @NotNull(message = "transactionDate no puede ser nulo")
    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "status no puede ser nulo")
    private PaymentStatus status;

    public Payment(String transactionCode, Long idUser, Long idCourse, BigDecimal amount, LocalDateTime transactionDate, PaymentStatus status) {
        this.transactionCode = transactionCode;
        this.idUser = idUser;
        this.idCourse = idCourse;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.status = status;
    }
}
