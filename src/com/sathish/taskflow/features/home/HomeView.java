package com.sathish.taskflow.features.home;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.features.employee.EmployeeListView;
import com.sathish.taskflow.features.employee.add.EmployeeAddView;
import com.sathish.taskflow.features.employee.reportee.ReporteeListView;
import com.sathish.taskflow.features.leave.LeaveView;
import com.sathish.taskflow.features.notification.NotificationView;
import com.sathish.taskflow.features.report.ReportView;
import com.sathish.taskflow.features.task.assign.AssignMode;
import com.sathish.taskflow.features.task.assign.TaskAssignView;
import com.sathish.taskflow.features.task.create.TaskCreateView;
import com.sathish.taskflow.features.task.detail.TaskDetailView;
import com.sathish.taskflow.features.task.list.TaskListView;
import com.sathish.taskflow.features.task.status.TaskStatusUpdateView;
import com.sathish.taskflow.features.task.team.TeamStatusView;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.Scanner;

public class HomeView {
   private final HomeModel homeModel = new HomeModel(this);
   private final Employee employee;
   private final Scanner scanner;

   public HomeView(Employee var1) {
      this.employee = var1;
      this.scanner = ConsoleInput.getScanner();
   }

   public void init() {
      this.homeModel.init(this.employee);
   }

   void showUnauthorized() {
      System.out.println("Your account role is not set. Contact your administrator.");
   }

   void showManagerMenu() {
      while(true) {
         System.out.println();
         System.out.println("Manager Home");
         System.out.println("1.  View all employees");
         System.out.println("2.  View reportees");
         System.out.println("3.  Add employee");
         System.out.println("4.  Add new task");
         System.out.println("5.  Assign a task");
         System.out.println("6.  View team status");
         System.out.println("7.  Update my task status");
         System.out.println("8.  View task details");
         System.out.println("9.  Notifications");
         System.out.println("10. View reports");
         System.out.println("11. Leave Approvals");
         System.out.println("12. Sign out");
         System.out.print("Choose an option: ");
         switch (this.scanner.nextLine().trim()) {
            case "1":
               (new EmployeeListView()).init();
               break;
            case "2":
               (new ReporteeListView(this.employee)).init();
               break;
            case "3":
               (new EmployeeAddView(this.employee)).init();
               break;
            case "4":
               (new TaskCreateView(this.employee)).init();
               break;
            case "5":
               (new TaskAssignView(this.employee, AssignMode.MANAGER_ASSIGN)).init();
               break;
            case "6":
               (new TeamStatusView(this.employee)).init();
               break;
            case "7":
               (new TaskStatusUpdateView(this.employee)).init();
               break;
            case "8":
               (new TaskDetailView(this.employee)).init();
               break;
            case "9":
               (new NotificationView(this.employee)).init();
               break;
            case "10":
               (new ReportView()).init();
               break;
            case "11":
               (new LeaveView(this.employee)).initManagerLeaveMenu();
               break;
            case "12":
               System.out.println("You have been signed out.");
               return;
            default:
               System.out.println("Invalid option. Please try again.");
         }
      }
   }

   void showEmployeeMenu() {
      while(true) {
         System.out.println();
         System.out.println("Employee Home");
         System.out.println("1. My tasks");
         System.out.println("2. Update task status");
         System.out.println("3. Reassign a task");
         System.out.println("4. View task details");
         System.out.println("5. Notifications");
         System.out.println("6. Leave Management");
         System.out.println("7. Sign out");
         System.out.print("Choose an option: ");
         switch (this.scanner.nextLine().trim()) {
            case "1":
               (new TaskListView(this.employee)).init();
               break;
            case "2":
               (new TaskStatusUpdateView(this.employee)).init();
               break;
            case "3":
               (new TaskAssignView(this.employee, AssignMode.EMPLOYEE_REASSIGN)).init();
               break;
            case "4":
               (new TaskDetailView(this.employee)).init();
               break;
            case "5":
               (new NotificationView(this.employee)).init();
               break;
            case "6":
               (new LeaveView(this.employee)).initEmployeeLeaveMenu();
               break;
            case "7":
               System.out.println("You have been signed out.");
               return;
            default:
               System.out.println("Invalid option. Please try again.");
         }
      }
   }
}
