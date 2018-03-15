class BinaryNode<AnyType> {
   // Data accessible by other package routines
   public AnyType element;              // The data in the node
   public BinaryNode<AnyType> left;     // Left child
   public BinaryNode<AnyType> right;    // Right child
   
   // Constructor
   BinaryNode (AnyType theElement) {
      element = theElement;
      left = right = null;
   }
}