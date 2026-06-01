package com.sathish.taskflow.features.task.status;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TaskStatusUpdateView {
   private final TaskStatusUpdateModel taskStatusUpdateModel = new TaskStatusUpdateModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentUser;

   public TaskStatusUpdateView(Employee var1) {
      this.currentUser = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("Update task status");
      List var1 = this.taskStatusUpdateModel.getMyTasks(this.currentUser);
      if (var1.isEmpty()) {
         System.out.println("You have no tasks to update.");
      } else {
         Task var2 = this.pickTask(var1);
         if (var2 != null) {
            PrintStream var10000 = System.out;
            String var10001 = this.nameOr(var2.getStatus());
            var10000.println("Current status: " + var10001);
            Task.TaskStatus var3 = this.pickStatus();
            if (var3 != null) {
               String var4 = this.promptRemarks();
               this.taskStatusUpdateModel.updateStatus(var2, var3, var4, this.currentUser);
            }
         }
      }
   }

   private Task pickTask(List<Task> var1) {
      while(true) {
         System.out.println("Select a task:");

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            Task var3 = (Task)var1.get(var2);
            System.out.println(var2 + 1 + ". " + var3.getTitle() + " [" + this.nameOr(var3.getStatus()) + "]");
         }

         System.out.print("Choose an option: ");
         Integer var4 = ParseHelper.parseNonNegativeInt(this.scanner.nextLine());
         if (var4 != null && var4 >= 1 && var4 <= var1.size()) {
            return (Task)var1.get(var4 - 1);
         }

         System.out.println("Select a valid option.");
      }
   }

   private Task.TaskStatus pickStatus() {
      while(true) {
         System.out.println("Select new status:");
         System.out.println("1. OPEN");
         System.out.println("2. IN_PROGRESS");
         System.out.println("3. COMPLETED");
         System.out.println("4. ON_HOLD");
         System.out.println("5. CANCELLED");
         System.out.println("6. REOPENED");
         System.out.print("Choose an option: ");
         Task.TaskStatus var1 = this.taskStatusUpdateModel.parseStatus(this.scanner.nextLine());
         if (var1 != null) {
            return var1;
         }

         System.out.println("Select a valid status.");
      }
   }

   private String promptRemarks() {
      while(true) {
         System.out.print("Enter remarks: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.taskStatusUpdateModel.validateRemarks(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   void onUpdateSuccessful(Task var1, Task.TaskStatus var2) {
      PrintStream var10000 = System.out;
      String var10001 = this.nameOr(var2);
      var10000.println("Status updated from " + var10001 + " to " + this.nameOr(var1.getStatus()) + ".");
   }

   void onUpdateFailed(String var1) {
      System.out.println(var1);
   }

   private String nameOr(Enum<?> var1) {
      return var1 == null ? "-" : var1.name();
   }
}