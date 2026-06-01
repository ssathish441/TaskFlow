package com.sathish.taskflow.features.task.team;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class TeamStatusModel {
   private final TeamStatusView teamStatusView;

   TeamStatusModel(TeamStatusView var1) {
      this.teamStatusView = var1;
   }

   List<Employee> getDirectReports(Long var1) {
      return TaskFlowDB.getInstance().getDirectReports(var1);
   }

   List<Task> getTasksFor(Long var1) {
      return TaskFlowDB.getInstance().getTasksAssignedTo(var1);
   }
}