// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.sathish.taskflow.features.task.create;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.dto.Task.Priority;
import com.sathish.taskflow.data.dto.Task.TaskStatus;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import com.sathish.taskflow.util.ParseHelper;

class TaskCreateModel {
   private static final int MIN_TITLE = 3;
   private static final int MAX_TITLE = 100;
   private static final int MAX_DESCRIPTION = 500;
   private final TaskCreateView taskCreateView;

   TaskCreateModel(TaskCreateView var1) {
      this.taskCreateView = var1;
   }

   String validateTitle(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         String var2 = var1.trim();
         return var2.length() >= 3 && var2.length() <= 100 ? null : "Title must be between 3 and 100 characters";
      } else {
         return "Title cannot be empty";
      }
   }

   String validateDescription(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         return var1.trim().length() > 500 ? "Description cannot exceed 500 characters" : null;
      } else {
         return "Description cannot be empty";
      }
   }

   Task.Priority parsePriority(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim();
         if (!var2.equals("1") && !var2.equalsIgnoreCase("P1")) {
            if (!var2.equals("2") && !var2.equalsIgnoreCase("P2")) {
               return !var2.equals("3") && !var2.equalsIgnoreCase("P3") ? null : Priority.P3;
            } else {
               return Priority.P2;
            }
         } else {
            return Priority.P1;
         }
      }
   }

   Long parseDueDate(String var1) {
      Long var2 = ParseHelper.parseDate(var1);
      if (var2 == null) {
         return null;
      } else {
         return !ParseHelper.isTodayOrFuture(var2) ? null : var2;
      }
   }

   void createTask(Employee var1, String var2, String var3, Task.Priority var4, Long var5) {
      Task var6 = new Task();
      var6.setTitle(var2.trim());
      var6.setDescription(var3.trim());
      var6.setPriority(var4);
      var6.setDueDate(var5);
      var6.setAssignedBy(var1 == null ? null : var1.getId());
      var6.setAssignedTo((Long)null);
      var6.setStatus(TaskStatus.OPEN);
      Task var7 = TaskFlowDB.getInstance().addTask(var6);
      if (var7 == null) {
         this.taskCreateView.onTaskCreateFailed("Could not create task. Please try again.");
      } else {
         this.taskCreateView.onTaskCreated(var7);
      }
   }
}
