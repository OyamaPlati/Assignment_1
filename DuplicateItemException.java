public class DuplicateItemException extends Exception {
   public static final long serialVersionUID = 42L;
   private String message;
   public DuplicateItemException (String message) {
      super (message);
   }
   public String getMessage() {
      return message;
   }
}