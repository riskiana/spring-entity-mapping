package dev.riskiana;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import dev.riskiana.entity.Person;
import dev.riskiana.entity.Pet;
import dev.riskiana.exception.PersonNotFoundException;
import dev.riskiana.service.PersonService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    when(entityManager.find(Mockito.eq(Person.class), any()))
        .thenReturn(mockPerson);

    Pet pet = personService.addPet(personId, petName);

    assertNotNull(pet);
    assertEquals(mockPerson, pet.getOwner());

    verify(entityManager, times(1)).persist(pet);

  }

  @Test
  void testAddPetPersonNotFound() {
    Long personId = 99L;
    when(entityManager.find(Person.class, personId)).thenReturn(null);

    PersonNotFoundException ex = assertThrows(
        PersonNotFoundException.class,
        () -> personService.addPet(personId, "Buddy")
    );

    assertEquals("Person with id 99 not found", ex.getMessage());
    verify(entityManager, never()).persist(any());
  }

}
