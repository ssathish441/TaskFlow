package com.sathish.taskflow.features.leave;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Leave;
import com.sathish.taskflow.data.dto.Leave.LeaveStatus;
import com.sathish.taskflow.util.ConsoleInput;
import java.util.List;
import java.util.Scanner;

public class LeaveView {
   private LeaveViewModel viewModel;
   private Employee currentUser;

   public LeaveView(Employee var1) {
      this.currentUser = var1;
      this.viewModel = new LeaveViewModel(this);
   }

   public void initEmployeeLeaveMenu() {
      Scanner var1 = ConsoleInput.getScanner();

      while(true) {
         System.out.println("\n--- Leave Management ---");
         System.out.println("1. Apply for Leave");
         System.out.println("2. View My Leave History");
         System.out.println("3. Back");
         System.out.print("Choose an option: ");
         switch (var1.nextLine().trim()) {
            case "1":
               this.applyLeave(var1);
               break;
            case "2":
               this.viewModel.getEmployeeLeaves(this.currentUser.getId());
               break;
            case "3":
               return;
            default:
               System.out.println("Invalid option.");
         }
      }
   }

   public void initManagerLeaveMenu() {
      Scanner var1 = ConsoleInput.getScanner();

      while(true) {
         System.out.println("\n--- Leave Approvals ---");
         System.out.println("1. View Pending Leave Requests");
         System.out.println("2. Approve/Reject Leave Request");
         System.out.println("3. Back");
         System.out.print("Choose an option: ");
         switch (var1.nextLine().trim()) {
            case "1":
               this.viewModel.getPendingLeaves();
               break;
            case "2":
               this.approveRejectLeave(var1);
               break;
            case "3":
               return;
            default:
               System.out.println("Invalid option.");
         }
      }
   }

   private void applyLeave(Scanner var1) {
      System.out.print("Enter Start Date (YYYY-MM-DD): ");
      String var2 = var1.nextLine();
      System.out.print("Enter End Date (YYYY-MM-DD): ");
      String var3 = var1.nextLine();
      System.out.print("Enter Reason: ");
      String var4 = var1.nextLine();
      this.viewModel.applyForLeave(this.currentUser.getId(), var2, var3, var4);
   }

   private void approveRejectLeave(Scanner var1) {
      this.viewModel.getPendingLeaves();
      System.out.print("Enter Leave ID to process: ");

      try {
         Long var2 = Long.parseLong(var1.nextLine().trim());
         System.out.print("Approve (A) or Reject (R)? ");
         String var3 = var1.nextLine().trim().toUpperCase();
         if (var3.equals("A")) {
            this.viewModel.approveRejectLeave(var2, LeaveStatus.APPROVED);
         } else if (var3.equals("R")) {
            this.viewModel.approveRejectLeave(var2, LeaveStatus.REJECTED);
         } else {
            System.out.println("Invalid action. Must be A or R.");
         }
      } catch (NumberFormatException var4) {
         System.out.println("Invalid Leave ID.");
      }

   }

   public void showLeaves(List<Leave> var1) {
      if (var1.isEmpty()) {
         System.out.println("No leave history found.");
      } else {
         System.out.println("\n--- Your Leave History ---");

         for(Leave var3 : var1) {
            System.out.printf("ID: %d | Start: %s | End: %s | Status: %s | Reason: %s\n", var3.getId(), var3.getStartDate(), var3.getEndDate(), var3.getStatus(), var3.getReason());
         }

      }
   }

   public void showPendingLeaves(List<Leave> var1) {
      if (var1.isEmpty()) {
         System.out.println("No pending leave requests.");
      } else {
         System.out.println("\n--- Pending Leave Requests ---");

         for(Leave var3 : var1) {
            System.out.printf("Leave ID: %d | Employee ID: %d | Start: %s | End: %s | Reason: %s\n", var3.getId(), var3.getEmployeeId(), var3.getStartDate(), var3.getEndDate(), var3.getReason());
         }

      }
   }

   public void showSuccess(String var1) {
      System.out.println("Success: " + var1);
   }

   public void showError(String var1) {
      System.out.println("Error: " + var1);
   }
}
