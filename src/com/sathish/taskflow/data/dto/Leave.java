
package com.sathish.taskflow.data.dto;

public class Leave {
   private Long id;
   private Long employeeId;
   private String startDate;
   private String endDate;
   private String reason;
   private LeaveStatus status;
   private Long appliedOn;

   public Leave() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long var1) {
      this.id = var1;
   }

   public Long getEmployeeId() {
      return this.employeeId;
   }

   public void setEmployeeId(Long var1) {
      this.employeeId = var1;
   }

   public String getStartDate() {
      return this.startDate;
   }

   public void setStartDate(String var1) {
      this.startDate = var1;
   }

   public String getEndDate() {
      return this.endDate;
   }

   public void setEndDate(String var1) {
      this.endDate = var1;
   }

   public String getReason() {
      return this.reason;
   }

   public void setReason(String var1) {
      this.reason = var1;
   }

   public LeaveStatus getStatus() {
      return this.status;
   }

   public void setStatus(LeaveStatus var1) {
      this.status = var1;
   }

   public Long getAppliedOn() {
      return this.appliedOn;
   }

   public void setAppliedOn(Long var1) {
      this.appliedOn = var1;
   }

   public static enum LeaveStatus {
      PENDING,
      APPROVED,
      REJECTED;

      private LeaveStatus() {
      }
   }
}
