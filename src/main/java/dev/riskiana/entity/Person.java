package dev.riskiana.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

@Entity
public class Person {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
  @Column(nullable = false)
  private Long id;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
  private Set<Pet> pets;


}
