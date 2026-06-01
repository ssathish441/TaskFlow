package com.sathish.taskflow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ParseHelper {
   private static final String DATE_PATTERN = "dd-MM-yyyy";
   private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";
   private static final String EMPTY_PLACEHOLDER = "-";

   private ParseHelper() {
   }

   public static Integer parseNonNegativeInt(String var0) {
      if (var0 == null) {
         return null;
      } else {
         String var1 = var0.trim();
         if (var1.isEmpty()) {
            return null;
         } else {
            long var2 = 0L;

            for(int var4 = 0; var4 < var1.length(); ++var4) {
               char var5 = var1.charAt(var4);
               if (var5 < '0' || var5 > '9') {
                  return null;
               }

               var2 = var2 * 10L + (long)(var5 - 48);
               if (var2 > 2147483647L) {
                  return null;
               }
            }

            return (int)var2;
         }
      }
   }

   public static boolean isYes(String var0) {
      if (var0 == null) {
         return false;
      } else {
         String var1 = var0.trim();
         if (var1.isEmpty()) {
            return false;
         } else {
            char var2 = var1.charAt(0);
            return var2 == 'Y' || var2 == 'y';
         }
      }
   }

   public static Long parseDate(String var0) {
      if (var0 != null && !var0.trim().isEmpty()) {
         SimpleDateFormat var1 = new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT);
         var1.setLenient(false);
         var1.setTimeZone(TimeZone.getDefault());

         try {
            return var1.parse(var0.trim()).getTime();
         } catch (ParseException var3) {
            return null;
         }
      } else {
         return null;
      }
   }

   public static String formatDate(Long var0) {
      return var0 == null ? "-" : (new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT)).format(new Date(var0));
   }

   public static String formatDateTime(Long var0) {
      return var0 == null ? "-" : (new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.ROOT)).format(new Date(var0));
   }

   public static int calculateAgeYears(long var0) {
      Calendar var2 = Calendar.getInstance();
      var2.setTimeInMillis(var0);
      Calendar var3 = Calendar.getInstance();
      int var4 = var3.get(1) - var2.get(1);
      if (var3.get(6) < var2.get(6)) {
         --var4;
      }

      return var4;
   }

   public static boolean isTodayOrFuture(long var0) {
      Calendar var2 = Calendar.getInstance();
      var2.set(11, 0);
      var2.set(12, 0);
      var2.set(13, 0);
      var2.set(14, 0);
      return var0 >= var2.getTimeInMillis();
   }
}
