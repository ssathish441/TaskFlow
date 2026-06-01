package com.sathish.taskflow.data.dto;

public class TaskStatusHistory {
   private Long id;
   private Long taskId;
   private Long changedBy;
   private Task.TaskStatus oldStatus;
   private Task.TaskStatus newStatus;
   private String remarks;
   private Long changedTime;

   public TaskStatusHistory() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long var1) {
      this.id = var1;
   }

   public Long getTaskId() {
      return this.taskId;
   }

   public void setTaskId(Long var1) {
      this.taskId = var1;
   }

   public Long getChangedBy() {
      return this.changedBy;
   }

   public void setChangedBy(Long var1) {
      this.changedBy = var1;
   }

   public Task.TaskStatus getOldStatus() {
      return this.oldStatus;
   }

   public void setOldStatus(Task.TaskStatus var1) {
      this.oldStatus = var1;
   }

   public Task.TaskStatus getNewStatus() {
      return this.newStatus;
   }

   public void setNewStatus(Task.TaskStatus var1) {
      this.newStatus = var1;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String var1) {
      this.remarks = var1;
   }

   public Long getChangedTime() {
      return this.changedTime;
   }

   public void setChangedTime(Long var1) {
      this.changedTime = var1;
   }
}
