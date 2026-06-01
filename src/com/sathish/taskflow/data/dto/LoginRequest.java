package com.sathish.taskflow.data.dto;

public class LoginRequest {
   private String email;
   private String password;

   public LoginRequest() {
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String var1) {
      this.email = var1;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }
}
