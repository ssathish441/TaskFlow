package com.sathish.taskflow.features.task.assign;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Notification;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.dto.Employee.Role;
import com.sathish.taskflow.data.dto.Notification.NotificationType;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.ArrayList;
import java.util.List;

class TaskAssignModel {

   private final TaskAssignView taskAssignView;

   TaskAssignModel(TaskAssignView taskAssignView) {
      this.taskAssignView = taskAssignView;
   }

   List<Task> listAssignableTasks(AssignMode mode, Employee currentUser) {
      if (mode == null || currentUser == null) return new ArrayList<>();
      if (mode == AssignMode.MANAGER_ASSIGN) {
         return TaskFlowDB.getInstance().getUnassignedTasksCreatedBy(currentUser.getId());
      }
      return TaskFlowDB.getInstance().getTasksAssignedTo(currentUser.getId());
   }

   List<Employee> listAssignees(Employee currentUser, AssignMode mode) {
      if (mode == AssignMode.MANAGER_ASSIGN) {
         return TaskFlowDB.getInstance().getEmployeesExcept(null);
      }
      Long excludeId = currentUser == null ? null : currentUser.getId();
      List<Employee> all = TaskFlowDB.getInstance().getEmployeesExcept(excludeId);
      if (mode == AssignMode.EMPLOYEE_REASSIGN) {
         List<Employee> filtered = new ArrayList<>();
         for (Employee candidate : all) {
            if (candidate.getRole() == Employee.Role.EMPLOYEE) {
               filtered.add(candidate);
            }
         }
         return filtered;
      }
      return all;
   }

   void assign(Task task, Long assigneeId) {
      if (task == null || assigneeId == null) {
         taskAssignView.onAssignFailed("Could not update task. Please try again.");
         return;
      }
      task.setAssignedTo(assigneeId);
      Task updated = TaskFlowDB.getInstance().updateTask(task);
      if (updated == null) {
         taskAssignView.onAssignFailed("Could not update task. Please try again.");
         return;
      }
      Notification notification = new Notification();
      notification.setEmployeeId(assigneeId);
      notification.setTaskId(updated.getId());
      notification.setType(Notification.NotificationType.TASK_ASSIGNED);
      notification.setMessage("You have been assigned task: " + updated.getTitle());
      TaskFlowDB.getInstance().addNotification(notification);

      taskAssignView.onAssignSuccessful(updated);
   }
}
