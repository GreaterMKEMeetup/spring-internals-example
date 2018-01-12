package com.mkejug.spring.messageconverter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    /*
    * curl -H "Accept: text/pipe" http://localhost:8080/person
    * curl -H "Accept: application/json" http://localhost:8080/person | jq
    */

    @GetMapping("/person")
    public Person getPerson() {
        Person person = new Person();
        person.setFirstName("Tommy");
        person.setLastName("Wiseau");
        return person;
    }
}
