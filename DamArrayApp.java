import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class DamArrayApp {
   private final static int SIZE = 1000;
   private static DamData[] dataItem = new DamData[SIZE];
   private static Scanner inputStream = null;
   private static int opCount = 0;
   
   public static void main(String[] args) {
      try {
            inputStream = new Scanner(
                  new FileInputStream(
                  (new File("Dam_Levels_Individual_Nov2015-Mar2016.csv")).getAbsolutePath()));
      }
      catch (FileNotFoundException e) {
         e.printStackTrace();
         System.out.println("FAIL!! : File does not exist");
         System.out.println(" or could not be open.");
      }
         
      String line = null;
      
      inputStream.nextLine();
      
      int i = 0;
      while (inputStream.hasNextLine()) {
         dataItem[i] = new DamData();
         line = inputStream.nextLine();
         
         String[] rawdata = line.split(",");
         
         if (rawdata.length >= 26) {
            dataItem[i].setDamName(rawdata[2]);
            dataItem[i].setFSC(rawdata[10]);
            dataItem[i].setDamLevel(rawdata[25]);
         }
         else if ((rawdata.length >= 11) 
            && (rawdata.length < 26)) {
            dataItem[i].setDamName(rawdata[2]);
            dataItem[i].setFSC(rawdata[10]);
         }
         else if (rawdata.length < 11){
            dataItem[i].setDamName(rawdata[2]);
         }      
         i++;
      }
      
      if (args.length != 0) {
         StringBuilder builder = new StringBuilder();
         for (String s: args) {
            if (builder.length() > 0) {
               builder.append(" ");
            }
            builder.append(s);
         }
         
         printDam(builder.toString().trim());
      }
      else {
         printAllDams();  
      }
      
      inputStream.close();
   }
   
   private static int find (String target, DamData[] haystack) {
      for (int i = 0; i < haystack.length; i++) {
         if (haystack[i] != null) {
            opCount++;
            if ((haystack[i].getDamName().trim()).equals(target))
               return i; 
         } 
      }
      return -1;   
   }
   
   private static void printOpCount (String cases, int count) {
      PrintWriter outputStream = null;
      
      try {
         outputStream = new PrintWriter (
                              new FileWriter("OpReport.dat", true));
      }
      catch (FileNotFoundException e0) {
         e0.printStackTrace();
         System.out.println("File not found \n or file does not exist.");
         System.exit(0);
      }
      catch (IOException e1) {
         e1.printStackTrace();
         System.exit(0);
      }
      
      outputStream.println(cases + ", " + count);
      
      outputStream.close();
   }
   
   public static void printDam (String damName) {
      int index = find(damName, dataItem);
      if  (index == -1) {
         printOpCount("Unknown dam name", opCount);
         System.out.println("Dam not found");
      }
      else {
         printOpCount("Known dam name", opCount);
         System.out.println(dataItem[index]);
      }     
   }
   
   public static void printAllDams () {
       for (int i = 0; i < dataItem.length; i++) {
         if (dataItem[i] != null)     
              System.out.println(dataItem[i]);
      }
      printOpCount("Without any parameters ",opCount);
   }
}