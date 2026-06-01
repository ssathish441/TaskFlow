package com.sathish.taskflow.features.employee.add;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Employee.EmployeeStatus;
import com.sathish.taskflow.data.dto.Employee.Role;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import com.sathish.taskflow.util.ParseHelper;
import java.util.regex.Pattern;

class EmployeeAddModel {
   private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
   private static final Pattern MOBILE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
   private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,}$");
   private static final int MIN_NAME_LENGTH = 3;
   private static final int MAX_NAME_LENGTH = 50;
   private static final int MIN_AGE_YEARS = 18;
   private final EmployeeAddView employeeAddView;

   EmployeeAddModel(EmployeeAddView var1) {
      this.employeeAddView = var1;
   }

   String validateName(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         String var2 = var1.trim();
         return var2.length() >= 3 && var2.length() <= 50 ? null : "Name must be between 3 and 50 characters";
      } else {
         return "Name cannot be empty";
      }
   }

   String validateEmail(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         String var2 = var1.trim();
         if (!EMAIL_PATTERN.matcher(var2).matches()) {
            return "Enter a valid email address";
         } else {
            return TaskFlowDB.getInstance().getEmployeeByEmail(var2) != null ? "This email is already registered" : null;
         }
      } else {
         return "Email cannot be empty";
      }
   }

   String validatePassword(String var1) {
      if (var1 != null && !var1.isEmpty()) {
         return !PASSWORD_PATTERN.matcher(var1).matches() ? "Password must be at least 8 characters and contain letters and numbers" : null;
      } else {
         return "Password cannot be empty";
      }
   }

   String validateConfirmPassword(String var1, String var2) {
      return var2 != null && var2.equals(var1) ? null : "Passwords do not match";
   }

   boolean isFirstEmployee() {
      return TaskFlowDB.getInstance().getEmployees().isEmpty();
   }

   boolean needsReportingManager(Employee.Role var1) {
      return var1 == Role.EMPLOYEE;
   }

   String validateMobile(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         return !MOBILE_PATTERN.matcher(var1.trim()).matches() ? "Enter a valid 10 digit mobile number" : null;
      } else {
         return "Mobile number cannot be empty";
      }
   }

   Long parseDateOfBirth(String var1) {
      Long var2 = ParseHelper.parseDate(var1);
      if (var2 == null) {
         return null;
      } else if (var2 >= System.currentTimeMillis()) {
         return null;
      } else {
         return ParseHelper.calculateAgeYears(var2) < 18 ? null : var2;
      }
   }

   Employee.Role parseRole(String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = var1.trim();
         if (!var2.equals("1") && !var2.equalsIgnoreCase("Manager")) {
            return !var2.equals("2") && !var2.equalsIgnoreCase("Employee") ? null : Role.EMPLOYEE;
         } else {
            return Role.MANAGER;
         }
      }
   }

   void addEmployee(String var1, String var2, String var3, String var4, Long var5, Employee.Role var6, Long var7) {
      Employee var8 = new Employee();
      var8.setName(var1.trim());
      var8.setEmail(var2.trim());
      var8.setPassword(var3);
      var8.setMobileNo(var4.trim());
      var8.setDob(var5);
      var8.setRole(var6);
      var8.setReportingTo(var7);
      var8.setStatus(EmployeeStatus.ACTIVE);
      Employee var9 = TaskFlowDB.getInstance().addEmployee(var8);
      if (var9 == null) {
         this.employeeAddView.onEmployeeAddFailed("Could not add employee. Please try again.");
      } else {
         this.employeeAddView.onEmployeeAdded(var9);
      }
   }
}
