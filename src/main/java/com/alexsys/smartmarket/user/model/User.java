package com.alexsys.smartmarket.user.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  private String avatar;
  private String firstName;
  private String lastName;
  
  @Column(nullable = false, unique = true)
  private String username;
  
  @Column(nullable = false, unique = true)
  private String email;
  
  private String password;
  
  private java.time.LocalDate birthOfDate;
  private String phoneNumber;

  @ElementCollection
  private List<Integer> addressIds;
}
