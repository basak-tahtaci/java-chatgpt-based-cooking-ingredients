package com.example.chatgptbasedcookingingredients;

/*
      {
        "role": "user",
        "content": "Hello!"
      }
 */

public record OpenAiMessages (String role,
                              String content) {
}
