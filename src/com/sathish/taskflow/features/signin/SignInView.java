package com.sathish.taskflow.features.signin;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.LoginRequest;
import com.sathish.taskflow.features.home.HomeView;
import com.sathish.taskflow.features.signup.SignUpView;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.Scanner;

public class SignInView {
   private final SignInModel signInModel = new SignInModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private boolean authenticated = false;

   public SignInView() {
   }

   public void init() {
      System.out.println();
      System.out.println("Sign in to TaskFlow");

      while(!this.authenticated) {
         this.promptAndAuthenticate();
         if (this.authenticated) {
            return;
         }

         if (!this.promptPostFailureAction()) {
            return;
         }
      }

   }

   private void promptAndAuthenticate() {
      System.out.print("Enter your email: ");
      String var1 = this.scanner.nextLine();
      System.out.print("Enter your password: ");
      String var2 = this.scanner.nextLine();
      LoginRequest var3 = new LoginRequest();
      var3.setEmail(var1 == null ? null : var1.trim());
      var3.setPassword(var2);
      this.signInModel.authenticate(var3);
   }

   private boolean promptPostFailureAction() {
      while(true) {
         System.out.println();
         System.out.println("1. Retry");
         System.out.println("2. Sign Up");
         System.out.println("3. Exit");
         System.out.print("Choose an option: ");
         switch (this.scanner.nextLine().trim()) {
            case "1":
               return true;
            case "2":
               (new SignUpView()).init();
               return false;
            case "3":
               System.out.println("Thank you for using TaskFlow");
               System.exit(0);
               return false;
            default:
               System.out.println("Invalid option. Please try again.");
         }
      }
   }

   void onSignInSuccessful(Employee var1) {
      this.authenticated = true;
      System.out.println("Welcome, " + var1.getName());
      (new HomeView(var1)).init();
   }

   void onSignInFailed(String var1) {
      System.out.println(var1);
   }

   void showErrorMessage(String var1) {
      System.out.println(var1);
   }
}
