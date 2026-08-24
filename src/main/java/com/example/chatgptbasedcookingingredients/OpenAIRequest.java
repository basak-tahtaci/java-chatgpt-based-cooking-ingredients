package com.example.chatgptbasedcookingingredients;

/*

{
    "model": "VAR_chat_model_id",
    "messages": [

      {
        "role": "user",
        "content": "Hello!"
      }
    ]
  }

 */

import java.util.List;

public record OpenAIRequest(String model,
                            List<OpenAiMessages> messages) {
}
