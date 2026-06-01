// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.sathish.taskflow.data.repository;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Leave;
import com.sathish.taskflow.data.dto.Notification;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.dto.TaskStatusHistory;
import com.sathish.taskflow.data.dto.Employee.EmployeeStatus;
import com.sathish.taskflow.data.dto.Employee.Role;
import com.sathish.taskflow.data.dto.Leave.LeaveStatus;
import com.sathish.taskflow.data.dto.Task.TaskStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskFlowDB {
   private static TaskFlowDB TaskFlowDB = null;
   private final List<Employee> employees = new ArrayList<>();
   private final List<Task> tasks = new ArrayList<>();
   private final List<TaskStatusHistory> taskStatusHistories = new ArrayList<>();
   private final List<Notification> notifications = new ArrayList<>();
   private long employeePk = 0L;
   private long taskPk = 0L;
   private long taskStatusHistoryPk = 0L;
   private long notificationPk = 0L;
   private final List<Leave> leaves = new ArrayList<>();
   private long leavePk = 0L;

   private TaskFlowDB() {
   }

   public static final TaskFlowDB getInstance() {
      if (TaskFlowDB == null) {
         TaskFlowDB = new TaskFlowDB();
      }

      return TaskFlowDB;
   }

   public Employee addEmployee(Employee var1) {
      if (var1 == null) {
         return null;
      } else if (var1.getEmail() != null && !var1.getEmail().trim().isEmpty()) {
         ++this.employeePk;
         var1.setId(this.employeePk);
         var1.setEmployeeId(String.format(Locale.ROOT, "EMP%05d", this.employeePk));
         if (var1.getCreatedAt() == null) {
            var1.setCreatedAt(System.currentTimeMillis());
         }

         if (var1.getStatus() == null) {
            var1.setStatus(EmployeeStatus.ACTIVE);
         }

         if (var1.getRole() == null) {
            var1.setRole(Role.EMPLOYEE);
         }

         this.employees.add(var1);
         return var1;
      } else {
         return null;
      }
   }

   public Employee getEmployeeByEmail(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);
         if (var2.isEmpty()) {
            return null;
         } else {
            for(Employee var4 : this.employees) {
               if (var4.getEmail() != null && var4.getEmail().trim().toLowerCase(Locale.ROOT).equals(var2)) {
                  return var4;
               }
            }

            return null;
         }
      }
   }

   public Employee authenticateEmployee(String var1, String var2) {
      Employee var3 = this.getEmployeeByEmail(var1);
      if (var3 == null) {
         return null;
      } else {
         return var2 != null && var2.equals(var3.getPassword()) ? var3 : null;
      }
   }

   public Employee getEmployeeById(Long var1) {
      if (var1 == null) {
         return null;
      } else {
         for(Employee var3 : this.employees) {
            if (var1.equals(var3.getId())) {
               return var3;
            }
         }

         return null;
      }
   }

   public List<Employee> getEmployees() {
      return new ArrayList(this.employees);
   }

   public List<Employee> getEmployeesExcept(Long var1) {
      ArrayList var2 = new ArrayList<>();

      for(Employee var4 : this.employees) {
         if (var4.getStatus() == EmployeeStatus.ACTIVE && (var1 == null || !var1.equals(var4.getId()))) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public List<Employee> getActiveManagers() {
      ArrayList var1 = new ArrayList<>();

      for(Employee var3 : this.employees) {
         if (var3.getRole() == Role.MANAGER && var3.getStatus() == EmployeeStatus.ACTIVE) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public Task addTask(Task var1) {
      if (var1 == null) {
         return null;
      } else {
         ++this.taskPk;
         var1.setId(this.taskPk);
         long var2 = System.currentTimeMillis();
         if (var1.getCreatedTime() == null) {
            var1.setCreatedTime(var2);
         }

         var1.setUpdatedTime(var2);
         if (var1.getStatus() == null) {
            var1.setStatus(TaskStatus.OPEN);
         }

         this.tasks.add(var1);
         return var1;
      }
   }

   public Task updateTask(Task var1) {
      if (var1 != null && var1.getId() != null) {
         for(int var2 = 0; var2 < this.tasks.size(); ++var2) {
            if (var1.getId().equals(((Task)this.tasks.get(var2)).getId())) {
               var1.setUpdatedTime(System.currentTimeMillis());
               this.tasks.set(var2, var1);
               return var1;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public List<Task> getTasksAssignedTo(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Task var4 : this.tasks) {
            if (var1.equals(var4.getAssignedTo())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public List<Task> getTasksCreatedBy(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Task var4 : this.tasks) {
            if (var1.equals(var4.getAssignedBy())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public List<Task> getUnassignedTasksCreatedBy(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Task var4 : this.tasks) {
            if (var4.getAssignedTo() == null && var1.equals(var4.getAssignedBy())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public List<Employee> getDirectReports(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Employee var4 : this.employees) {
            if (var1.equals(var4.getReportingTo())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public TaskStatusHistory addStatusHistory(TaskStatusHistory var1) {
      if (var1 != null && var1.getTaskId() != null) {
         ++this.taskStatusHistoryPk;
         var1.setId(this.taskStatusHistoryPk);
         if (var1.getChangedTime() == null) {
            var1.setChangedTime(System.currentTimeMillis());
         }

         this.taskStatusHistories.add(var1);
         return var1;
      } else {
         return null;
      }
   }

   public List<TaskStatusHistory> getStatusHistoryForTask(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(TaskStatusHistory var4 : this.taskStatusHistories) {
            if (var1.equals(var4.getTaskId())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public Notification addNotification(Notification var1) {
      if (var1 != null && var1.getEmployeeId() != null) {
         ++this.notificationPk;
         var1.setId(this.notificationPk);
         if (var1.getCreatedTime() == null) {
            var1.setCreatedTime(System.currentTimeMillis());
         }

         if (var1.getIsRead() == null) {
            var1.setIsRead(Boolean.FALSE);
         }

         this.notifications.add(var1);
         return var1;
      } else {
         return null;
      }
   }

   public List<Notification> getNotificationsFor(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Notification var4 : this.notifications) {
            if (var1.equals(var4.getEmployeeId())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public int markNotificationsRead(Long var1) {
      if (var1 == null) {
         return 0;
      } else {
         int var2 = 0;

         for(Notification var4 : this.notifications) {
            if (var1.equals(var4.getEmployeeId()) && !Boolean.TRUE.equals(var4.getIsRead())) {
               var4.setIsRead(Boolean.TRUE);
               ++var2;
            }
         }

         return var2;
      }
   }

   public Leave addLeave(Leave var1) {
      if (var1 == null) {
         return null;
      } else {
         ++this.leavePk;
         var1.setId(this.leavePk);
         if (var1.getAppliedOn() == null) {
            var1.setAppliedOn(System.currentTimeMillis());
         }

         if (var1.getStatus() == null) {
            var1.setStatus(LeaveStatus.PENDING);
         }

         this.leaves.add(var1);
         return var1;
      }
   }

   public List<Leave> getLeavesByEmployeeId(Long var1) {
      ArrayList var2 = new ArrayList<>();
      if (var1 == null) {
         return var2;
      } else {
         for(Leave var4 : this.leaves) {
            if (var1.equals(var4.getEmployeeId())) {
               var2.add(var4);
            }
         }

         return var2;
      }
   }

   public List<Leave> getPendingLeaves() {
      ArrayList var1 = new ArrayList<>();

      for(Leave var3 : this.leaves) {
         if (var3.getStatus() == LeaveStatus.PENDING) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public Leave getLeaveById(Long var1) {
      if (var1 == null) {
         return null;
      } else {
         for(Leave var3 : this.leaves) {
            if (var1.equals(var3.getId())) {
               return var3;
            }
         }

         return null;
      }
   }

   public void updateLeaveStatus(Long var1, Leave.LeaveStatus var2) {
      Leave var3 = this.getLeaveById(var1);
      if (var3 != null) {
         var3.setStatus(var2);
      }

   }
}
