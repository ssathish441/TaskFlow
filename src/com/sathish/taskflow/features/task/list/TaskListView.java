package com.sathish.taskflow.features.task.list;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.util.List;
import java.util.Scanner;

public class TaskListView {
   private final TaskListModel taskListModel = new TaskListModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentUser;

   public TaskListView(Employee var1) {
      this.currentUser = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("My Tasks");
      Long var1 = this.currentUser == null ? null : this.currentUser.getId();
      List var2 = this.taskListModel.getMyTasks(var1);
      if (var2.isEmpty()) {
         System.out.println("You have no tasks assigned.");
      } else {
         System.out.println("#   Id   Title                          Priority  Status         Due Date     Assigned By");

         for(int var3 = 0; var3 < var2.size(); ++var3) {
            Task var4 = (Task)var2.get(var3);
            String var5 = String.format("%-3d %-4s %-30s %-9s %-14s %-12s %s", var3 + 1, var4.getId() == null ? "-" : String.valueOf(var4.getId()), this.truncate(this.safe(var4.getTitle()), 30), this.nameOr(var4.getPriority()), this.nameOr(var4.getStatus()), ParseHelper.formatDate(var4.getDueDate()), this.taskListModel.getEmployeeName(var4.getAssignedBy()));
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

   private String nameOr(Enum<?> var1) {
      return var1 == null ? "-" : var1.name();
   }
}
