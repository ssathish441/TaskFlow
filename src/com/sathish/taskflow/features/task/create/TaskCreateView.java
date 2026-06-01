package com.sathish.taskflow.features.task.create;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.features.task.assign.TaskAssignView;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.util.Scanner;

public class TaskCreateView {
   private final TaskCreateModel taskCreateModel = new TaskCreateModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentUser;

   public TaskCreateView(Employee var1) {
      this.currentUser = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("Add a new task");
      String var1 = this.promptTitle();
      String var2 = this.promptDescription();
      Task.Priority var3 = this.promptPriority();
      Long var4 = this.promptDueDate();
      this.taskCreateModel.createTask(this.currentUser, var1, var2, var3, var4);
   }

   private String promptTitle() {
      while(true) {
         System.out.print("Enter task title: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.taskCreateModel.validateTitle(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   private String promptDescription() {
      while(true) {
         System.out.print("Enter task description: ");
         String var1 = this.scanner.nextLine();
         String var2 = this.taskCreateModel.validateDescription(var1);
         if (var2 == null) {
            return var1.trim();
         }

         System.out.println(var2);
      }
   }

   private Task.Priority promptPriority() {
      while(true) {
         System.out.println("Select priority:");
         System.out.println("1. P1");
         System.out.println("2. P2");
         System.out.println("3. P3");
         System.out.print("Choose an option: ");
         Task.Priority var1 = this.taskCreateModel.parsePriority(this.scanner.nextLine());
         if (var1 != null) {
            return var1;
         }

         System.out.println("Select a valid priority.");
      }
   }

   private Long promptDueDate() {
      while(true) {
         System.out.print("Enter due date (dd-MM-yyyy): ");
         Long var1 = this.taskCreateModel.parseDueDate(this.scanner.nextLine());
         if (var1 != null) {
            return var1;
         }

         System.out.println("Enter a valid date. Due date must be today or later.");
      }
   }

   void onTaskCreated(Task var1) {
      System.out.println();
      System.out.println("Task created successfully.");
      System.out.println("Task id: " + var1.getId());
      System.out.print("Do you want to assign this task now? (Y/N): ");
      if (ParseHelper.isYes(this.scanner.nextLine())) {
         (new TaskAssignView(this.currentUser, var1)).init();
      } else {
         System.out.println("Task saved without an assignee. Use Assign a task later.");
      }

   }

   void onTaskCreateFailed(String var1) {
      System.out.println(var1);
   }
}
