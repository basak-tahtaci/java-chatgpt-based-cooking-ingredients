package com.example.chatgptbasedcookingingredients;


/*
{

  "choices": [
    {
      "index": 0,
      "message": {
        "role": "assistant",
        "content": "Hello! How can I assist you today?",
      }
    }
  ]
}
 */

import java.util.List;

public record OpenAIResponse(List<OpenAiChoice> choices) {
}
