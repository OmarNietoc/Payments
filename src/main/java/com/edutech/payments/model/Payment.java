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
    private Long transactionCode;

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
}
