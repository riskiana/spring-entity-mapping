package dev.riskiana.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Pet {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_seq")
  @SequenceGenerator(name = "hibernate_seq", sequenceName = "hibernate_sequence", allocationSize = 1)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToOne
  @JoinColumn(
      name = "owner_id"
  )
  private Person owner;

  public void setName(String name){
    this.name = name;
  }

  public Person getOwner() {
    return owner;
  }

  public String getOwnerName() {
    return owner.getFirstName();
  }

  public void setOwner(Person owner) {
    this.owner = owner;
  }


}
