package com.sathish.taskflow.data.dto;

public class Task {
   private Long id;
   private String title;
   private String description;
   private Long assignedBy;
   private Long assignedTo;
   private Priority priority;
   private Long createdTime;
   private Long dueDate;
   private Long updatedTime;
   private Long completedTime;
   private String remarks;
   private TaskStatus status;

   public Task() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long var1) {
      this.id = var1;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String var1) {
      this.title = var1;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String var1) {
      this.description = var1;
   }

   public Long getAssignedBy() {
      return this.assignedBy;
   }

   public void setAssignedBy(Long var1) {
      this.assignedBy = var1;
   }

   public Long getAssignedTo() {
      return this.assignedTo;
   }

   public void setAssignedTo(Long var1) {
      this.assignedTo = var1;
   }

   public Priority getPriority() {
      return this.priority;
   }

   public void setPriority(Priority var1) {
      this.priority = var1;
   }

   public Long getCreatedTime() {
      return this.createdTime;
   }

   public void setCreatedTime(Long var1) {
      this.createdTime = var1;
   }

   public Long getDueDate() {
      return this.dueDate;
   }

   public void setDueDate(Long var1) {
      this.dueDate = var1;
   }

   public Long getUpdatedTime() {
      return this.updatedTime;
   }

   public void setUpdatedTime(Long var1) {
      this.updatedTime = var1;
   }

   public Long getCompletedTime() {
      return this.completedTime;
   }

   public void setCompletedTime(Long var1) {
      this.completedTime = var1;
   }

   public String getRemarks() {
      return this.remarks;
   }

   public void setRemarks(String var1) {
      this.remarks = var1;
   }

   public TaskStatus getStatus() {
      return this.status;
   }

   public void setStatus(TaskStatus var1) {
      this.status = var1;
   }

   public static enum Priority {
      P1,
      P2,
      P3;

      private Priority() {
      }
   }

   public static enum TaskStatus {
      OPEN,
      IN_PROGRESS,
      COMPLETED,
      ON_HOLD,
      CANCELLED,
      REOPENED;

      private TaskStatus() {
      }
   }
}
