// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.sathish.taskflow.features.employee.add;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Employee.Role;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.Scanner;

public class EmployeeAddView {
   private final EmployeeAddModel employeeAddModel = new EmployeeAddModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentManager;

   public EmployeeAddView(Employee var1) {
      this.currentManager = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("Add a new employee");
      boolean var1 = this.employeeAddModel.isFirstEmployee();
      String var2 = this.promptName();
      String var3 = this.promptEmail();
      String var4 = this.promptPassword();
      String var5 = this.promptMobile();
      Long var6 = this.promptDob();
      Employee.Role var7;
      Long var8;
      if (var1) {
         System.out.println("As the first user in the system, this employee will be registered as a Manager.");
         var7 = Role.MANAGER;
         var8 = null;
      } else {
         var7 = this.promptRole();
         var8 = this.employeeAddModel.needsReportingManager(var7) ? (this.currentManager == null ? null : this.currentManager.getId()) : null;
      }

      this.employeeAddModel.addEmployee(var2, var3, var4, var5, var6, var7, var8);
   }

   private String promptName() {
      while(true) {
         System.out.print("Enter full name: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.employeeAddModel.validateName(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   private String promptEmail() {
      while(true) {
         System.out.print("Enter email: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.employeeAddModel.validateEmail(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   private String promptPassword() {
      while(true) {
         System.out.print("Enter password (minimum 8 characters with letters and numbers): ");
         String var1 = this.scanner.nextLine();
         String var2 = this.employeeAddModel.validatePassword(var1);
         if (var2 != null) {
            System.out.println(var2);
         } else {
            System.out.print("Confirm password: ");
            String var3 = this.scanner.nextLine();
            String var4 = this.employeeAddModel.validateConfirmPassword(var1, var3);
            if (var4 == null) {
               return var1;
            }

            System.out.println(var4);
         }
      }
   }

   private String promptMobile() {
      while(true) {
         System.out.print("Enter 10 digit mobile number: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.employeeAddModel.validateMobile(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   private Long promptDob() {
      while(true) {
         System.out.print("Enter date of birth (dd-MM-yyyy): ");
         String var1 = this.scanner.nextLine();
         Long var2 = this.employeeAddModel.parseDateOfBirth(var1);
         if (var2 != null) {
            return var2;
         }

         System.out.println("Enter a valid date. Employee must be at least 18 years old.");
      }
   }

   private Employee.Role promptRole() {
      while(true) {
         System.out.println("Select role:");
         System.out.println("1. Manager");
         System.out.println("2. Employee");
         System.out.print("Choose an option: ");
         String var1 = this.scanner.nextLine();
         Employee.Role var2 = this.employeeAddModel.parseRole(var1);
         if (var2 != null) {
            return var2;
         }

         System.out.println("Select a valid role.");
      }
   }

   void onEmployeeAdded(Employee var1) {
      System.out.println();
      System.out.println("Employee added successfully.");
      System.out.println("Employee id: " + var1.getEmployeeId());
   }

   void onEmployeeAddFailed(String var1) {
      System.out.println(var1);
   }
}
