package com.sathish.taskflow.features.task.status;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Notification;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.dto.TaskStatusHistory;
import com.sathish.taskflow.data.dto.Notification.NotificationType;
import com.sathish.taskflow.data.dto.Task.TaskStatus;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class TaskStatusUpdateModel {
   private static final int MAX_REMARKS = 500;
   private final TaskStatusUpdateView taskStatusUpdateView;

   TaskStatusUpdateModel(TaskStatusUpdateView var1) {
      this.taskStatusUpdateView = var1;
   }

   List<Task> getMyTasks(Employee var1) {
      Long var2 = var1 == null ? null : var1.getId();
      return TaskFlowDB.getInstance().getTasksAssignedTo(var2);
   }

   Task.TaskStatus parseStatus(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim();
         if (!var2.equals("1") && !var2.equalsIgnoreCase("OPEN")) {
            if (!var2.equals("2") && !var2.equalsIgnoreCase("IN_PROGRESS")) {
               if (!var2.equals("3") && !var2.equalsIgnoreCase("COMPLETED")) {
                  if (!var2.equals("4") && !var2.equalsIgnoreCase("ON_HOLD")) {
                     if (!var2.equals("5") && !var2.equalsIgnoreCase("CANCELLED")) {
                        return !var2.equals("6") && !var2.equalsIgnoreCase("REOPENED") ? null : TaskStatus.REOPENED;
                     } else {
                        return TaskStatus.CANCELLED;
                     }
                  } else {
                     return TaskStatus.ON_HOLD;
                  }
               } else {
                  return TaskStatus.COMPLETED;
               }
            } else {
               return TaskStatus.IN_PROGRESS;
            }
         } else {
            return TaskStatus.OPEN;
         }
      }
   }

   String validateRemarks(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         return var1.trim().length() > 500 ? "Remarks cannot exceed 500 characters" : null;
      } else {
         return "Remarks cannot be empty";
      }
   }

   void updateStatus(Task var1, Task.TaskStatus var2, String var3, Employee var4) {
      if (var1 != null && var2 != null && var4 != null) {
         if (var2 == var1.getStatus()) {
            this.taskStatusUpdateView.onUpdateFailed("New status must differ from current status.");
         } else {
            Task.TaskStatus var5 = var1.getStatus();
            TaskStatusHistory var6 = new TaskStatusHistory();
            var6.setTaskId(var1.getId());
            var6.setChangedBy(var4.getId());
            var6.setOldStatus(var5);
            var6.setNewStatus(var2);
            var6.setRemarks(var3 == null ? "" : var3.trim());
            TaskFlowDB.getInstance().addStatusHistory(var6);
            var1.setStatus(var2);
            if (var2 == TaskStatus.COMPLETED) {
               var1.setCompletedTime(System.currentTimeMillis());
            } else if (var2 == TaskStatus.REOPENED) {
               var1.setCompletedTime((Long)null);
            }

            Task var7 = TaskFlowDB.getInstance().updateTask(var1);
            if (var7 == null) {
               this.taskStatusUpdateView.onUpdateFailed("Could not update status. Please try again.");
            } else {
               this.notifyCreator(var7, var5, var2, var4);
               this.taskStatusUpdateView.onUpdateSuccessful(var7, var5);
            }
         }
      } else {
         this.taskStatusUpdateView.onUpdateFailed("Could not update status. Please try again.");
      }
   }

   private void notifyCreator(Task var1, Task.TaskStatus var2, Task.TaskStatus var3, Employee var4) {
      Long var5 = var1.getAssignedBy();
      if (var5 != null) {
         if (var4 == null || !var5.equals(var4.getId())) {
            Notification var6 = new Notification();
            var6.setEmployeeId(var5);
            var6.setTaskId(var1.getId());
            var6.setType(NotificationType.STATUS_UPDATED);
            String var7 = var4 != null && var4.getName() != null ? var4.getName() : "an employee";
            String var10001 = var1.getTitle();
            var6.setMessage("Task '" + var10001 + "' status changed from " + String.valueOf(var2) + " to " + String.valueOf(var3) + " by " + var7);
            TaskFlowDB.getInstance().addNotification(var6);
         }
      }
   }
}
