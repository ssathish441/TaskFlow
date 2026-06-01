package com.sathish.taskflow.features.notification;

import com.sathish.taskflow.data.dto.Notification;
import com.sathish.taskflow.data.repository.TaskFlowDB;
import java.util.List;

class NotificationModel {
   private final NotificationView notificationView;

   NotificationModel(NotificationView var1) {
      this.notificationView = var1;
   }

   List<Notification> getNotifications(Long var1) {
      return TaskFlowDB.getInstance().getNotificationsFor(var1);
   }

   int markAllRead(Long var1) {
      return TaskFlowDB.getInstance().markNotificationsRead(var1);
   }
}
