package com.sathish.taskflow.features.signin;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.LoginRequest;
import com.sathish.taskflow.data.dto.Employee.EmployeeStatus;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.regex.Pattern;

class SignInModel {
   private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
   private final SignInView signInView;

   SignInModel(SignInView var1) {
      this.signInView = var1;
   }

   String validateEmail(String var1) {
      if (var1 != null && !var1.trim().isEmpty()) {
         return !EMAIL_PATTERN.matcher(var1.trim()).matches() ? "Enter a valid email address" : null;
      } else {
         return "Email cannot be empty";
      }
   }

   String validatePassword(String var1) {
      return var1 != null && !var1.isEmpty() ? null : "Password cannot be empty";
   }

   void authenticate(LoginRequest var1) {
      if (var1 == null) {
         this.signInView.onSignInFailed("Invalid email or password");
      } else {
         String var2 = this.validateEmail(var1.getEmail());
         if (var2 != null) {
            this.signInView.onSignInFailed(var2);
         } else {
            String var3 = this.validatePassword(var1.getPassword());
            if (var3 != null) {
               this.signInView.onSignInFailed(var3);
            } else {
               Employee var4 = TaskFlowDB.getInstance().authenticateEmployee(var1.getEmail(), var1.getPassword());
               if (var4 == null) {
                  this.signInView.onSignInFailed("Invalid email or password");
               } else if (var4.getStatus() == EmployeeStatus.INACTIVE) {
                  this.signInView.onSignInFailed("Your account is not active. Contact your administrator.");
               } else {
                  this.signInView.onSignInSuccessful(var4);
               }
            }
         }
      }
   }
}
