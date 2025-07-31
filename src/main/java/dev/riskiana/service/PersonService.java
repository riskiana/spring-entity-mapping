package dev.riskiana.service;

import dev.riskiana.entity.Person;
import dev.riskiana.entity.Pet;
import dev.riskiana.exception.PersonNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

  private final EntityManager entityManager;

  public PersonService(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Person getPersonById(Long id) {
    return entityManager.find(Person.class, id);
  }

  public Person getPersonByName(String name) {
    String jpql = "SELECT p FROM Person p WHERE p.firstName = :name";
    return entityManager.createQuery(jpql, Person.class)
        .setParameter("name", name)
        .getSingleResult();
  }

  public List<Person> getAllPersons() {
    return entityManager.createQuery("select p from Person p", Person.class).getResultList();
  }

  @Transactional
  public Pet addPet(Long personId, String petName) {
    Person owner = entityManager.find(Person.class, personId);
    if (owner == null) {
      throw new PersonNotFoundException("Person with id " + personId + " not found");
    }

    Pet pet = new Pet();
    pet.setName(petName);
    pet.setOwner(owner);
    entityManager.persist(pet);

    return pet;
  }


  @Transactional
  public Person createPerson(@Valid Person person) {
    Person p = new Person();
    p.setFirstName(person.getFirstName());
    entityManager.persist(person);
    return getPersonByName(person.getFirstName());
  }
}
