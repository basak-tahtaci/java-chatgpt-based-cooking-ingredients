package com.example.chatgptbasedcookingingredients;


import lombok.RequiredArgsConstructor;
import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/ingredients")

public class IngredientController {

    private final RestClient restClient;

    //value key liest schlüssel. restclient wird mit openai adresse und token konfiguriert
    public IngredientController(@Value("${app.openai-api-key}") String openaiApiKey) {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + openaiApiKey)
                .build();
    }

    @PostMapping
    String categorizeIngredient(@RequestBody String ingredient) {
        String prompt = "Bestimme für die folgende Zutat, ob sie 'vegan', 'vegetarian' oder 'regular' ist. " +
                "Antworte ausschließlich mit genau einem dieser drei Wörter und sonst nichts: " + ingredient;
        //baut prompt auf

        //post anfrage an base url senden
        OpenAIResponse response = restClient.post()
                //was soll in der body der anfrage gesendet werden, struktur genau wir unser record, spring wandekt objekt in json
                .body(new OpenAIRequest("gpt-4o-mini", prompt))
                //retrieve führt anfrage aus und wartet auf antwort
                .retrieve()
                //nimmt json antwort entgegen und wandelt in java
                .body(OpenAIResponse.class);

        //verschachtelte antwort wird ausgepackt
        return response.choices().get(0).message().content();
    }

}

//Hey Spring, die Antwort, die vom OpenAI-
//Server als langer JSON-Text zurückkommt, entspricht genau der Struktur unseres OpenAIResponse-Records.
//Bitte übersetze diesen JSON-Text automatisch für mich in eine Java-Instanz von OpenAIResponse.
