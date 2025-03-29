package com.storefront.forms;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserForm {


    private String email;
    private String password; 


    private String phoneNumber;
    private String firstName;
    private String lastName;



   @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate DOB;

}
