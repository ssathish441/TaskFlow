package com.sathish.taskflow;

import com.sathish.taskflow.features.signin.SignInView;
import com.sathish.taskflow.features.signup.SignUpView;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.Scanner;

class TaskFlowApplication {
   public static final int VERSION_NO = 2;
   public static final String VERSION_NAME = "1.0.0";

   TaskFlowApplication() {
   }

   public static void main(String[] var0) {
      System.out.println("Welcome to TaskFlow");
      System.out.println("Version 1.0.0");
      showLandingMenu();
   }

   private static void showLandingMenu() {
      Scanner var0 = ConsoleInput.getScanner();

      while(true) {
         System.out.println();
         System.out.println("1. Sign Up");
         System.out.println("2. Sign In");
         System.out.println("3. Exit");
         System.out.print("Choose an option: ");
         switch (var0.nextLine().trim()) {
            case "1":
               (new SignUpView()).init();
               break;
            case "2":
               (new SignInView()).init();
               break;
            case "3":
               System.out.println("Thank you for using TaskFlow");
               return;
            default:
               System.out.println("Invalid option. Please try again.");
         }
      }
   }
}