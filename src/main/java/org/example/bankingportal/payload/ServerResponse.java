package org.example.bankingportal.payload;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ServerResponse {

     Boolean isSuccess;
     String message;


     public ServerResponse(String message) {
          this.message = message;
     }


}
