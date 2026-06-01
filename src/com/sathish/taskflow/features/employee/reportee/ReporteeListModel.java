package com.sathish.taskflow.features.employee.reportee;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class ReporteeListModel {
   private final ReporteeListView reporteeListView;

   ReporteeListModel(ReporteeListView var1) {
      this.reporteeListView = var1;
   }

   List<Employee> getReportees(Long var1) {
      return TaskFlowDB.getInstance().getDirectReports(var1);
   }
}