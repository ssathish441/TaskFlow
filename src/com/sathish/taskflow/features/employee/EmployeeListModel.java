package com.sathish.taskflow.features.employee;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

import java.util.List;

class EmployeeListModel {

   private final EmployeeListView employeeListView;

   EmployeeListModel(EmployeeListView employeeListView) {
      this.employeeListView = employeeListView;
   }

   List<Employee> getAllEmployees() {
      return TaskFlowDB.getInstance().getEmployees();
   }
}