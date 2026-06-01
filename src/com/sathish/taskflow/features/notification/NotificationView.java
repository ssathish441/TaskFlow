package com.sathish.taskflow.features.notification;

import com.sathish.taskflow.data.dto.Employee;
import com.sathish.taskflow.data.dto.Notification;
import com.sathish.taskflow.util.ConsoleInput;
import com.sathish.taskflow.util.ParseHelper;
import java.util.List;
import java.util.Scanner;

public class NotificationView {
   private final NotificationModel notificationModel = new NotificationModel(this);
   private final Scanner scanner = ConsoleInput.getScanner();
   private final Employee currentUser;

   public NotificationView(Employee var1) {
      this.currentUser = var1;
   }

   public void init() {
      System.out.println();
      System.out.println("Notifications");
      Long var1 = this.currentUser == null ? null : this.currentUser.getId();
      List var2 = this.notificationModel.getNotifications(var1);
      if (var2.isEmpty()) {
         System.out.println("You have no notifications.");
         System.out.print("Press Enter to return: ");
         this.scanner.nextLine();
      } else {
         int var3 = 0;

         for(int var4 = 0; var4 < var2.size(); ++var4) {
            Notification var5 = (Notification)var2.get(var4);
            boolean var6 = !Boolean.TRUE.equals(var5.getIsRead());
            if (var6) {
               ++var3;
            }

            String var7 = var6 ? "*" : " ";
            System.out.println(var4 + 1 + ". [" + var7 + "] " + ParseHelper.formatDateTime(var5.getCreatedTime()) + " | " + this.nameOr(var5.getType()) + " | " + this.safe(var5.getMessage()));
         }

         if (var3 > 0) {
            System.out.print("Mark all as read? (Y/N): ");
            if (ParseHelper.isYes(this.scanner.nextLine())) {
               int var8 = this.notificationModel.markAllRead(var1);
               System.out.println(var8 + " notification(s) marked as read.");
            }
         } else {
            System.out.print("Press Enter to return: ");
            this.scanner.nextLine();
         }

      }
   }

   private String safe(String var1) {
      return var1 == null ? "-" : var1;
   }

   private String nameOr(Enum<?> var1) {
      return var1 == null ? "-" : var1.name();
   }
}
