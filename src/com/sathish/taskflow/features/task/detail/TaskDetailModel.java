
package com.sathish.taskflow.features.task.detail;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Task;
import com.sathish.taskflow.data.dto.TaskStatusHistory;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class TaskDetailModel {
   private final TaskDetailView taskDetailView;

   TaskDetailModel(TaskDetailView var1) {
      this.taskDetailView = var1;
   }

   List<Task> getVisibleTasks(Employee var1) {
      ArrayList var2 = new ArrayList();
      if (var1 != null && var1.getId() != null) {
         Long var3 = var1.getId();
         HashSet var4 = new HashSet();

         for(Task var6 : TaskFlowDB.getInstance().getTasksAssignedTo(var3)) {
            if (var6.getId() != null && var4.add(var6.getId())) {
               var2.add(var6);
            }
         }

         for(Task var8 : TaskFlowDB.getInstance().getTasksCreatedBy(var3)) {
            if (var8.getId() != null && var4.add(var8.getId())) {
               var2.add(var8);
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   List<TaskStatusHistory> getHistoryFor(Long var1) {
      return TaskFlowDB.getInstance().getStatusHistoryForTask(var1);
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
