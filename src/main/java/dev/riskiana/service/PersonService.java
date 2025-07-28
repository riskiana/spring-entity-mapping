package dev.riskiana.service;

import dev.riskiana.entity.Person;
import dev.riskiana.entity.Pet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

  private final EntityManager entityManager;

  public PersonService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Transactional
  public Pet addPet(Long personId, String petName) {
    Person owner = entityManager.find(Person.class, personId);
    Pet pet = new Pet();
    pet.setName(petName);
    pet.setOwner(owner);
    entityManager.persist(pet);

    return pet;
  }


}
