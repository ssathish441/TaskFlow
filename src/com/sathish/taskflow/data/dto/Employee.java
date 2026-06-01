package com.sathish.taskflow.data.dto;

public class Employee {
   private Long id;
   private String employeeId;
   private String name;
   private String email;
   private String password;
   private String mobileNo;
   private Long dob;
   private Role role;
   private Long reportingTo;
   private EmployeeStatus status;
   private Long createdAt;

   public Employee() {
   }

   public Long getId() {
      return this.id;
   }

   public void setId(Long var1) {
      this.id = var1;
   }

   public String getEmployeeId() {
      return this.employeeId;
   }

   public void setEmployeeId(String var1) {
      this.employeeId = var1;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String var1) {
      this.name = var1;
   }

   public String getEmail() {
      return this.email;
   }

   public void setEmail(String var1) {
      this.email = var1;
   }

   public String getPassword() {
      return this.password;
   }

   public void setPassword(String var1) {
      this.password = var1;
   }

   public String getMobileNo() {
      return this.mobileNo;
   }

   public void setMobileNo(String var1) {
      this.mobileNo = var1;
   }

   public Long getDob() {
      return this.dob;
   }

   public void setDob(Long var1) {
      this.dob = var1;
   }

   public Role getRole() {
      return this.role;
   }

   public void setRole(Role var1) {
      this.role = var1;
   }

   public Long getReportingTo() {
      return this.reportingTo;
   }

   public void setReportingTo(Long var1) {
      this.reportingTo = var1;
   }

   public EmployeeStatus getStatus() {
      return this.status;
   }

   public void setStatus(EmployeeStatus var1) {
      this.status = var1;
   }

   public Long getCreatedAt() {
      return this.createdAt;
   }

   public void setCreatedAt(Long var1) {
      this.createdAt = var1;
   }

   public static enum Role {
      MANAGER,
      EMPLOYEE;

      private Role() {
      }
   }

   public static enum EmployeeStatus {
      ACTIVE,
      INACTIVE;

      private EmployeeStatus() {
      }
   }
}
