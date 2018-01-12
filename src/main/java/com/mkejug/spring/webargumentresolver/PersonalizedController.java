package com.mkejug.spring.webargumentresolver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalizedController {

    /*
    * curl -H "Accept: application/json" --cookie "k_e_ses=myUserId.mySessionId" http://localhost:8080/personalization | jq
    * https://developer.kenticocloud.com/docs/retrieving-user-and-session-id
    */

    @GetMapping("/personalization")
    public PersonalizationToken getToken(PersonalizationToken token) {
        return token;
    }
}
