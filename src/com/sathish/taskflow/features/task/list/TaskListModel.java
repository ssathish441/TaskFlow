package com.sathish.taskflow.features.task.list;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class TaskListModel {
   private final TaskListView taskListView;

   TaskListModel(TaskListView var1) {
      this.taskListView = var1;
   }

   List<Task> getMyTasks(Long var1) {
      return TaskFlowDB.getInstance().getTasksAssignedTo(var1);
   }

   String getEmployeeName(Long var1) {
      if (var1 == null) {
         return "-";
      } else {
         Employee var2 = TaskFlowDB.getInstance().getEmployeeById(var1);
         return var2 != null && var2.getName() != null ? var2.getName() : "-";
      }
   }
}
