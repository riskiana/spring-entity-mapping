package dev.riskiana.controller;

import dev.riskiana.entity.Person;
import dev.riskiana.entity.Pet;
import dev.riskiana.exception.PersonNotFoundException;
import dev.riskiana.service.PersonService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {
  private final PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping
  public List<Person> getPersons() {
    return personService.getAllPersons();
  }

  @PostMapping
  public ResponseEntity<String> createPerson(@Valid @RequestBody Person person) {
    Person createdPerson = personService.createPerson(person);
    return  ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Person created: " + createdPerson.getFirstName());
  }

  @PostMapping("/pets")
  ResponseEntity<String> addPet(Long personId, @Valid String petName) {
    try {
      Pet newPet = personService.addPet(personId, petName);
      String result = String.format("Added pet %s, owner name %s", newPet.getName()
          , newPet.getOwnerName());

      return ResponseEntity.status(HttpStatus.CREATED)
          .body(result);

    } catch (PersonNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(e.getMessage());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(e.getMessage());
    }
  }




}
