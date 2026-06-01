package com.sathish.taskflow.util;

import java.util.Scanner;

public class ConsoleInput {
   private static final Scanner SCANNER;

   private ConsoleInput() {
   }

   public static Scanner getScanner() {
      return SCANNER;
   }

   static {
      SCANNER = new Scanner(System.in);
   }
}