package com.edutech.payments.client;

import com.edutech.payments.dto.CourseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "courses", path = "localhost:8081/edutech/courses")
public interface CourseClient {

    @GetMapping("/{id}")
    CourseDto getCourseById(@PathVariable("id") Long id);
}
