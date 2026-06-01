package com.sathish.taskflow.data.dto;

public class Notification {
   private Long id;
   private Long employeeId;
   private Long taskId;
   private String message;
   private NotificationType type;
   private Boolean isRead;
   private Long createdTime;

   public Notification() {
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

   public Long getTaskId() {
      return this.taskId;
   }

   public void setTaskId(Long var1) {
      this.taskId = var1;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String var1) {
      this.message = var1;
   }

   public NotificationType getType() {
      return this.type;
   }

   public void setType(NotificationType var1) {
      this.type = var1;
   }

   public Boolean getIsRead() {
      return this.isRead;
   }

   public void setIsRead(Boolean var1) {
      this.isRead = var1;
   }

   public Long getCreatedTime() {
      return this.createdTime;
   }

   public void setCreatedTime(Long var1) {
      this.createdTime = var1;
   }

   public static enum NotificationType {
      TASK_ASSIGNED,
      STATUS_UPDATED,
      DUE_REMINDER;

      private NotificationType() {
      }
   }
}
