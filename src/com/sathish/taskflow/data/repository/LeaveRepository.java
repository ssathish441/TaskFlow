package com.sathish.taskflow.data.repository;

import com.sathish.taskflow.data.dto.Leave;
import com.sathish.taskflow.data.dto.Leave.LeaveStatus;
import java.util.ArrayList;
import java.util.List;

public class LeaveRepository {
   private static LeaveRepository instance;
   private List<Leave> leaveList = new ArrayList<>();
   private long leaveIdCounter = 1L;

   private LeaveRepository() {
   }

   public static LeaveRepository getInstance() {
      if (instance == null) {
         instance = new LeaveRepository();
      }

      return instance;
   }

   public void addLeave(Leave var1) {
      var1.setId(Long.valueOf((long)(this.leaveIdCounter++)));
      var1.setAppliedOn(System.currentTimeMillis());
      var1.setStatus(LeaveStatus.PENDING);
      this.leaveList.add(var1);
   }

   public List<Leave> getLeavesByEmployeeId(Long var1) {
      ArrayList var2 = new ArrayList();

      for(Leave var4 : this.leaveList) {
         if (var4.getEmployeeId().equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public List<Leave> getPendingLeaves() {
      ArrayList var1 = new ArrayList();

      for(Leave var3 : this.leaveList) {
         if (var3.getStatus() == LeaveStatus.PENDING) {
            var1.add(var3);
         }
      }

      return var1;
   }

   public Leave getLeaveById(Long var1) {
      for(Leave var3 : this.leaveList) {
         if (var3.getId().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public void updateLeaveStatus(Long var1, Leave.LeaveStatus var2) {
      Leave var3 = this.getLeaveById(var1);
      if (var3 != null) {
         var3.setStatus(var2);
      }

   }
}
