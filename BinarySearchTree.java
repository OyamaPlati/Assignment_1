// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x ) --> Insert x
// Comparable find( x ) --> Return item that matches x
// boolean isEmpty( ) --> Return true if empty; else false
// void postOrder( x ) --> print all items in post order format
// ******************ERRORS********************************
// Exceptions are thrown by insert if warranted

public class BinarySearchTree <AnyType extends Comparable<? super AnyType>> {
   
   protected BinaryNode<AnyType> root;
   public BinarySearchTree () {
      root = null;
   }
   
   public void insert (AnyType x) {
      try {
         root = insert (x, root);
      } 
      catch (DuplicateItemException e) {
         System.out.println(e.getMessage());
      }
   }
   
   public boolean isEmpty () { 
      return root == null;
   }
   
   /**
    * Internal method to insert into a subtree.
    * @param x the item to insert.
    * @param t the node that roots the tree.
    * @return the new root.
    * @throws DuplicateItemException if x is already present.
    */
   protected BinaryNode<AnyType> insert
      (AnyType x, BinaryNode<AnyType> t) throws DuplicateItemException { 
      
      if (t == null) {
         t = new BinaryNode<AnyType> (x);
      }
      else if (x.compareTo (t.element) < 0) {
         t.left = insert (x, t.left);
      }
      else if (x.compareTo (t.element) > 0) {
         t.right = insert (x, t.right);
      }
      else { 
         throw new DuplicateItemException (x.toString()); // Duplicate
      }
      return t;   
   }
   
   
   public void postOrder () {
      postOrder(root);
   }
   
   protected void postOrder (BinaryNode<AnyType> t) {
      if (t != null)
      {
          postOrder (t.left);             
          postOrder (t.right);
          System.out.println (t.element);
      }
   }  
}