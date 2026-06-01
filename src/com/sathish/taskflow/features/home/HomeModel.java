package com.sathish.taskflow.features.home;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Employee.Role;

class HomeModel {
   private final HomeView homeView;

   HomeModel(HomeView var1) {
      this.homeView = var1;
   }

   void init(Employee var1) {
      if (var1 != null && var1.getRole() != null) {
         if (var1.getRole() == Role.MANAGER) {
            this.homeView.showManagerMenu();
         } else {
            this.homeView.showEmployeeMenu();
         }

      } else {
         this.homeView.showUnauthorized();
      }
   }
}
