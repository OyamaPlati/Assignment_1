import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.File;

public class DamBSTApp {
   
   private static BinarySearchTree <DamData> bst = new BinarySearchTree <DamData> ();
   private static Scanner inputStream = null;
   private static int opCount = 0;
   
   public static void main (String[] args) {
      
      // ************************OPEN FILE******************* //
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
      
      // ************************OPENED FILE******************** //
      
      
      // ************************POPULATE TREE****************** //
      String line = null;
      
      inputStream.nextLine(); 
      
      while (inputStream.hasNextLine()) {
         line = inputStream.nextLine();
         
         String[] rawdata = line.split(",");
         
         if (rawdata.length >= 26) {
            bst.insert(
               new DamData(rawdata[2], rawdata[10], rawdata[25]));
         }
         else if ((rawdata.length >= 11) 
            && (rawdata.length < 26)) {
            bst.insert(
               new DamData(rawdata[2], rawdata[10], null));
         }
         else if (rawdata.length < 11){
            bst.insert(new DamData(rawdata[2], null, null));
         }    
      }
      
      // *********************TREE POPULATED********************* //
      
      // *********************EXTRACT ARGUMENT********************* //
      // *********************OUTPUT OPERATION********************* //
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
      
      // *********************EXTRACTED ARGUMENT********************* //
      // ******************FINISHED OUTPUT OPERATION***************** //
      
      inputStream.close();  
   }
   
   public static void printAllDams () {
      bst.postOrder();
      printOpCount("Without any parameters ",opCount);
   }
   
   public static void printDam (String damName) {
      BinaryNode <DamData> result = find (damName);
      if (result == null) {
         printOpCount("Unknown dam name", opCount);
         System.out.println("Dam not found");
      }
      else {
         printOpCount("Known dam name", opCount);
         System.out.println(result.element); 
      }  
   }
   
   private static BinaryNode<DamData> find (String name) {
      return find (bst.root, name);   
   }
   
   private static BinaryNode<DamData> find 
               (BinaryNode<DamData> t, String name) {
      BinaryNode<DamData> result = null;
      if (t != null) { 
         opCount++;
         if (name.equals(t.element.getDamName().trim())) { 
            return t;
         }
         else {
            result = find (t.left, name); 
            if (result == null) {
               result = find (t.right, name);
            }
            return result;
         }
      }
      else {
         return null;
      }
   }
   
   private static void printOpCount (String cases, int count) {
      PrintWriter outputStream = null;
      
      try {
         outputStream = new PrintWriter (
                              new FileWriter("OpBSTReport.dat", true));
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
}