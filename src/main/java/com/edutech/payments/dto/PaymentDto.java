package com.edutech.payments.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {


    @NotNull(message = "El ID de usuario no puede ser nulo")
    private Long idUser;

    @NotNull(message = "El ID del curso no puede ser nulo")
    private Long idCourse;

    @NotNull(message = "El monto no puede ser nulo")
    private BigDecimal amount;
}
