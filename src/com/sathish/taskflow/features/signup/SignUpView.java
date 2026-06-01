package com.sathish.taskflow.features.signup;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Employee.Role;
import com.sathish.taskflow.features.signin.SignInView;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.util.List;
import java.util.Scanner;

public class SignUpView {
   private final SignUpModel signUpModel = new SignUpModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();

   public SignUpView() {
   }

   public void init() {
      this.startSignUp();
   }

   private void startSignUp() {
      System.out.println();
      System.out.println("Create your TaskFlow account");
      boolean var1 = this.signUpModel.isFirstEmployee();
      String var2 = this.promptName();
      String var3 = this.promptEmail();
      String var4 = this.promptPassword();
      String var5 = this.promptMobile();
      Long var6 = this.promptDob();
      Employee.Role var7;
      Long var8;
      if (var1) {
         System.out.println("As the first user in the system, you will be registered as a Manager.");
         var7 = Role.MANAGER;
         var8 = null;
      } else {
         var7 = this.promptRole();
         var8 = var7 == Role.EMPLOYEE ? this.promptReportingManager() : null;
      }

      this.signUpModel.registerEmployee(var2, var3, var4, var5, var6, var7, var8);
   }

   private String promptName() {
      while(true) {
         System.out.print("Enter your full name: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.signUpModel.validateName(var1);
         if (var2 == null) {
            return var1.trim();
         }

         this.showErrorMessage(var2);
      }
   }

   private String promptEmail() {
      while(true) {
         System.out.print("Enter your email: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.signUpModel.validateEmail(var1);
         if (var2 == null) {
            return var1.trim();
         }

         this.showErrorMessage(var2);
      }
   }

   private String promptPassword() {
      while(true) {
         System.out.print("Enter password (minimum 8 characters with letters and numbers): ");
         String var1 = this.scanner.nextLine();
         String var2 = this.signUpModel.validatePassword(var1);
         if (var2 != null) {
            this.showErrorMessage(var2);
         } else {
            System.out.print("Confirm password: ");
            String var3 = this.scanner.nextLine();
            String var4 = this.signUpModel.validateConfirmPassword(var1, var3);
            if (var4 == null) {
               return var1;
            }

            this.showErrorMessage(var4);
         }
      }
   }

   private String promptMobile() {
      while(true) {
         System.out.print("Enter your 10 digit mobile number: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.signUpModel.validateMobile(var1);
         if (var2 == null) {
            return var1.trim();
         }

         this.showErrorMessage(var2);
      }
   }

   private Long promptDob() {
      while(true) {
         System.out.print("Enter your date of birth (dd-MM-yyyy): ");
         String var1 = this.scanner.nextLine();
         Long var2 = this.signUpModel.parseDateOfBirth(var1);
         if (var2 != null) {
            return var2;
         }

         this.showErrorMessage("Enter a valid date. You must be at least 18 years old.");
      }
   }

   private Employee.Role promptRole() {
      while(true) {
         System.out.println("Select your role:");
         System.out.println("1. Manager");
         System.out.println("2. Employee");
         System.out.print("Choose an option: ");
         String var1 = this.scanner.nextLine();
         Employee.Role var2 = this.signUpModel.parseRole(var1);
         if (var2 != null) {
            return var2;
         }

         this.showErrorMessage("Select a valid role.");
      }
   }

   private Long promptReportingManager() {
      List var1 = this.signUpModel.getActiveManagers();
      if (var1.isEmpty()) {
         System.out.println("No manager is available yet. You can update your reporting manager later.");
         return null;
      } else {
         while(true) {
            System.out.println("Select your reporting manager:");

            for(int var2 = 0; var2 < var1.size(); ++var2) {
               Employee var3 = (Employee)var1.get(var2);
               System.out.println(var2 + 1 + ". " + var3.getName() + " (" + var3.getEmployeeId() + ")");
            }

            System.out.print("Choose an option: ");
            Integer var4 = ParseHelper.parseNonNegativeInt(this.scanner.nextLine());
            if (var4 != null && var4 >= 1 && var4 <= var1.size()) {
               return ((Employee)var1.get(var4 - 1)).getId();
            }

            this.showErrorMessage("Select a valid option.");
         }
      }
   }

   void onSignUpSuccessful(Employee var1) {
      System.out.println();
      System.out.println("Account created successfully.");
      System.out.println("Your employee id is " + var1.getEmployeeId() + ".");
      System.out.println("Please sign in to continue.");
      (new SignInView()).init();
   }

   void showErrorMessage(String var1) {
      System.out.println(var1);
   }
}
