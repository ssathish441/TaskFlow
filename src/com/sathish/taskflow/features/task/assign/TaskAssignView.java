package com.sathish.taskflow.features.task.assign;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TaskAssignView {
   private final TaskAssignModel taskAssignModel = new TaskAssignModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentUser;
   private final AssignMode mode;
   private final Task preselectedTask;

   public TaskAssignView(Employee var1, AssignMode var2) {
      this.currentUser = var1;
      this.mode = var2;
      this.preselectedTask = null;
   }

   public TaskAssignView(Employee var1, Task var2) {
      this.currentUser = var1;
      this.mode = AssignMode.MANAGER_ASSIGN;
      this.preselectedTask = var2;
   }

   public void init() {
      System.out.println();
      Task var1 = this.preselectedTask != null ? this.preselectedTask : this.pickTask();
      if (var1 != null) {
         List var2 = this.taskAssignModel.listAssignees(this.currentUser, this.mode);
         if (var2.isEmpty()) {
            System.out.println("No employees available to assign.");
         } else {
            Employee var3 = this.pickAssignee(var2);
            if (var3 != null) {
               PrintStream var10000 = System.out;
               String var10001 = var1.getTitle();
               var10000.print("Confirm assigning task '" + var10001 + "' to " + var3.getName() + "? (Y/N): ");
               if (!ParseHelper.isYes(this.scanner.nextLine())) {
                  System.out.println("Assignment cancelled.");
               } else {
                  this.taskAssignModel.assign(var1, var3.getId());
               }
            }
         }
      }
   }

   private Task pickTask() {
      List var1 = this.taskAssignModel.listAssignableTasks(this.mode, this.currentUser);
      if (var1.isEmpty()) {
         if (this.mode == AssignMode.MANAGER_ASSIGN) {
            System.out.println("No unassigned tasks to assign.");
         } else {
            System.out.println("You have no tasks to reassign.");
         }

         return null;
      } else {
         String var2 = this.mode == AssignMode.MANAGER_ASSIGN ? "Select a task to assign:" : "Select a task to reassign:";

         while(true) {
            System.out.println(var2);

            for(int var3 = 0; var3 < var1.size(); ++var3) {
               Task var4 = (Task)var1.get(var3);
               System.out.println(var3 + 1 + ". " + var4.getTitle() + " [" + (var4.getPriority() == null ? "-" : var4.getPriority().name()) + ", " + (var4.getStatus() == null ? "-" : var4.getStatus().name()) + "]");
            }

            System.out.print("Choose an option: ");
            Integer var5 = ParseHelper.parseNonNegativeInt(this.scanner.nextLine());
            if (var5 != null && var5 >= 1 && var5 <= var1.size()) {
               return (Task)var1.get(var5 - 1);
            }

            System.out.println("Select a valid option.");
         }
      }
   }

   private Employee pickAssignee(List<Employee> var1) {
      while(true) {
         System.out.println("Select an employee:");

         for(int var2 = 0; var2 < var1.size(); ++var2) {
            Employee var3 = (Employee)var1.get(var2);
            System.out.println(var2 + 1 + ". " + var3.getName() + " (" + var3.getEmployeeId() + ", " + (var3.getRole() == null ? "-" : var3.getRole().name()) + ")");
         }

         System.out.print("Choose an option: ");
         Integer var4 = ParseHelper.parseNonNegativeInt(this.scanner.nextLine());
         if (var4 != null && var4 >= 1 && var4 <= var1.size()) {
            return (Employee)var1.get(var4 - 1);
         }

         System.out.println("Select a valid option.");
      }
   }

   void onAssignSuccessful(Task var1) {
      System.out.println("Task assigned successfully.");
   }

   void onAssignFailed(String var1) {
      System.out.println(var1);
   }
}
