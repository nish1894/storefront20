package com.storefront.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//names can be changed 
@Entity(name = "users") // default is class name
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    private String userId; 

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String name;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate DOB;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    private String phoneNumber;

    private String emailVerified;
    private String phoneVerified; 

    private Providers provider = Providers.SELF;
    private String providerUserID; 

 
//  @OneToOne
//     @MapsId  // This makes userLogin's ID the same as this entity's ID
//     @JoinColumn(name = "id")  // Foreign key column
//     private UserLogin userLogin;
}

