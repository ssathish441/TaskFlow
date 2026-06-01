package com.sathish.taskflow.features.leave;

import com.sathish.taskflow.data.dto.Leave;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class LeaveViewModel {
   private LeaveView view;

   LeaveViewModel(LeaveView var1) {
      this.view = var1;
   }

   void applyForLeave(Long var1, String var2, String var3, String var4) {
      Leave var5 = new Leave();
      var5.setEmployeeId(var1);
      var5.setStartDate(var2);
      var5.setEndDate(var3);
      var5.setReason(var4);
      TaskFlowDB.getInstance().addLeave(var5);
      this.view.showSuccess("Leave applied successfully!");
   }

   void getEmployeeLeaves(Long var1) {
      List var2 = TaskFlowDB.getInstance().getLeavesByEmployeeId(var1);
      this.view.showLeaves(var2);
   }

   void getPendingLeaves() {
      List var1 = TaskFlowDB.getInstance().getPendingLeaves();
      this.view.showPendingLeaves(var1);
   }

   void approveRejectLeave(Long var1, Leave.LeaveStatus var2) {
      Leave var3 = TaskFlowDB.getInstance().getLeaveById(var1);
      if (var3 == null) {
         this.view.showError("Leave request not found.");
      } else {
         TaskFlowDB.getInstance().updateLeaveStatus(var1, var2);
         this.view.showSuccess("Leave status updated to " + String.valueOf(var2));
      }
   }
}
