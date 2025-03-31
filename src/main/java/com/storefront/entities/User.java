package com.storefront.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class User implements UserDetails{

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

    @Column(nullable = false)
    private String phoneNumber;

    private String emailVerified;
    private String phoneVerified; 

    @Enumerated(value = EnumType.STRING)
    private Providers provider = Providers.SELF;
    private String providerUserID;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList =new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        Collection<SimpleGrantedAuthority> roles  =roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    } 

    @Override
    public String getUsername() {
        return this.email; 
    } 

 
//  @OneToOne
//     @MapsId  // This makes userLogin's ID the same as this entity's ID
//     @JoinColumn(name = "id")  // Foreign key column
//     private UserLogin userLogin;
}

