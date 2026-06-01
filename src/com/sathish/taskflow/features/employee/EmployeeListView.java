// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.sathish.taskflow.features.employee;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class EmployeeListView {
   private final EmployeeListModel employeeListModel = new EmployeeListModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();

   public EmployeeListView() {
   }

   public void init() {
      List var1 = this.employeeListModel.getAllEmployees();
      System.out.println();
      System.out.println("All Employees");
      if (var1.isEmpty()) {
         System.out.println("No employees yet.");
      } else {
         System.out.println("#   Employee Id   Name                        Email                           Role      Status");

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            Employee var3 = (Employee)var1.get(var2);
            String var4 = String.format("%-3d %-13s %-27s %-31s %-9s %s", var2 + 1, this.safe(var3.getEmployeeId()), this.truncate(this.safe(var3.getName()), 27), this.truncate(this.safe(var3.getEmail()), 31), var3.getRole() == null ? "-" : var3.getRole().name(), var3.getStatus() == null ? "-" : var3.getStatus().name());
            System.out.println(var4);
         }
      }

      System.out.print("Press Enter to return: ");
      this.scanner.nextLine();
   }

   private String safe(String var1) {
      return var1 == null ? "-" : var1;
   }

   private String truncate(String var1, int var2) {
      if (var1.length() <= var2) {
         return var1;
      } else {
         String var10000 = var1.substring(0, var2 - 1);
         return var10000 + "~";
      }
   }
}
