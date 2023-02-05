package com.HW2.HW2RestAPIMaven;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Hw2RestApiMavenController {
    @GetMapping("/Homework2")
    public Object starWars() {
        try {
            Random rand = new Random();
            int randomInt = rand.nextInt(10 - 1 + 1) + 1;

            String url = "https://swapi.py4e.com/api/people/" + randomInt;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String jSonSWFacts = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(jSonSWFacts);

            //Print the whole response to console.
            //System.out.println(root);

            //Parse out the most important info from the response.
            String name = root.get("name").asText();
            String birthYear = root.get("birth_year").asText();
            String eyeColor = root.get("eye_color").asText();
            String hairColor = root.get("hair_color").asText();
            String height = root.get("height").asText();

            System.out.println("\nName: " + name);
            System.out.println("Birth Year: " + birthYear);
            System.out.println("Height: " + height + "cm");
            System.out.println("Eye Color: " + eyeColor);
            System.out.println("Hair Color: " + hairColor);


            return root;

        } catch (JsonProcessingException ex) {
            Logger.getLogger(Hw2RestApiMavenController.class.getName()).log(Level.SEVERE, null, ex);
            return "error in /Homework2";
        }
    }
}
