package com.edutech.payments.controller;

import com.edutech.payments.controller.response.MessageResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/edutech/payments")
public class PaymentController {

    @GetMapping
    public ResponseEntity<?> getPayments(){
        return  ResponseEntity.ok(new MessageResponse("Listado de pagos"));
    }

}
