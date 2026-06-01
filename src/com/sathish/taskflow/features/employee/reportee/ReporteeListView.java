// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.sathish.taskflow.features.employee.reportee;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class ReporteeListView {
   private final ReporteeListModel reporteeListModel = new ReporteeListModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee manager;

   public ReporteeListView(Employee var1) {
      this.manager = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("My Reportees");
      Long var1 = this.manager == null ? null : this.manager.getId();
      List var2 = this.reporteeListModel.getReportees(var1);
      if (var2.isEmpty()) {
         System.out.println("You have no reporting employees.");
      } else {
         System.out.println("#   Employee Id   Name                        Email                           Role      Status");

         for(int var3 = 0; var3 < var2.size(); ++var3) {
            Employee var4 = (Employee)var2.get(var3);
            String var5 = String.format("%-3d %-13s %-27s %-31s %-9s %s", var3 + 1, this.safe(var4.getEmployeeId()), this.truncate(this.safe(var4.getName()), 27), this.truncate(this.safe(var4.getEmail()), 31), var4.getRole() == null ? "-" : var4.getRole().name(), var4.getStatus() == null ? "-" : var4.getStatus().name());
            System.out.println(var5);
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
