package dev.riskiana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import dev.riskiana.entity.Person;
import dev.riskiana.entity.Pet;
import dev.riskiana.service.PersonService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class PersonServiceTests {


  EntityManager entityManager;
  PersonService personService = new PersonService(entityManager);

  @BeforeEach
  public void init() {
    entityManager = Mockito.mock(EntityManager.class);
    personService = new PersonService(entityManager);
  }

  @Test
  public void testAddPet_shouldCreatePetWithOwner() {
    Long personId = 1L;
    String petName = "molly";
    Person mockPerson = new Person();
    mockPerson.setFirstName("John");

    Mockito.when(entityManager.find(Mockito.eq(Person.class), Mockito.any()))
        .thenReturn(mockPerson);

    Pet pet = personService.addPet(personId, petName);

    assertNotNull(pet);
    assertEquals(mockPerson, pet.getOwner());

    verify(entityManager, times(1)).persist(pet);

  }

}
