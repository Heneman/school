/**
 * 1-Index based Array implementation of Binary Max-Heap
 * <p/>
 * Heavily borrowed from Laurentiu Cristofor's implementation found at:
 * <p/>
 * http://www.cs.umb.edu/~dana/GAClust/src/Heap.java
 * <p/>
 * Other sources that I'd looked through weren't using pure arithmetical operations to manipulate the arrays being used
 * by the Heap objects; many used recursion on the sift-Up and -Down methods but their implementation utilized the
 * arithmetic described in the Binary Heap's 1-Index Array invariant.  I also enjoyed that the operations performed were
 * strictly on the index values and no for-loops were used.
 * <p/>
 * I had originally attempted to implement a simplified version of Cristofor's Heap class using a 0-Index based Array,
 * but was having trouble reconciling parent indices with 2+ elements added to the MaxHeap; each element can only have 2
 * "children", but when using the parent/child formulas "(i - 1)/2" and "(i * 2)+1 or +2" respectively, the calculated
 * "parent" indices of 0, 1, and 2 all indicated element 0 as their "parent", which violates the maximum number of
 * children each element can have.
 * <p/>
 * After spending considerable time trying to find an elegant way to fix this issue and continue to use a 0-Index based
 * Array, it appeared that the most elegant was to simply have a dummy node as the root and use a 1-Index based Array to
 * avoid the off-by-one "parent" index issues involved with the 0-Index based Array.
 *
 * @author Kyle Heneman
 * @since 03/20/2015
 */
public class MaxHeap
{
  // An array to represent the Binary Max-Heap
  private String[] heap;

  // Keeps count of non-null elements in `heap`
  private int heapSize;


  /**
   * Creates new MaxHeap object of length n
   *
   * @param n Length of `heap`
   */
  public MaxHeap( int n )
  {
    // Length cannot be a negative value
    if( n < 0 ) throw new IllegalArgumentException();

    /*
     * Actual number of elements being used in `heap` is n,
     * but since a dummy element is being used as the root,
     * one extra element is needed
     */
    heap = new String[ n + 1 ];

    // New MaxHeaps are empty
    heapSize = 0;
  }


  /**
   * Adds new String value to MaxHeap
   *
   * @param s String to be added to MaxHeap
   */
  public void push( String s )
  {
    // `s` cannot be a null value
    if( s == null ) throw new IllegalArgumentException();

    // Ensure capacity of `heap` is big enough
    if( isFull() ) ensureCapacity();

    // Add new value to end of `heap`
    heap[ ++heapSize ] = s;

    // Balance according to Binary Max-Heap invariant
    siftUp( heapSize );
  }


  /**
   * Balances `heap` (i.e. organizes array) upon addition of new value to ensure that the values stored in the parent
   * indices are greater than the values stored in the child indices
   *
   * @param i Index to "sift-up" from
   */
  public void siftUp( int i )
  {
    // Index value can't be out-of-bounds
    if( (i < 1) || (i > heapSize) ) throw new IllegalArgumentException( "i is an invalid index" );

    // Index of parent element to element at `i`
    int pi = getIndexOfParent( i );

    // Newest value added to `heap` for comparison
    String newestValue = heap[ i ];

    // Until "root" element is reached (i.e. index 0)
    while( pi > 0 )
    {
      /*
       * Compare `newestValue` to value stored in the parent of the current index.
       * If `newestValue` is greater than the value at the parent index, the values
       * at the parent and child indices need to be swapped
       */
      if( heap[ pi ].compareTo( newestValue ) < 0 )
      {
        // Move value of parent element to the element at the current index
        heap[ i ] = heap[ pi ];

        // Parent index becomes current index
        i = pi;

        // Get parent index of current index
        pi = getIndexOfParent( i );
      }

      /*
       * If `newestValue` is NOT greater than the value at the parent index,
       * `newestValue` is at it's proper position and `heap` has been sorted
       */
      else
        break;
    }

    /*
     *`i` is now the index at which `newestValue` will no longer be
     * greater than the value of the element at the parent index
     */
    heap[ i ] = newestValue;
  }


  /**
   * Removes and returns the next (i.e. greatest) value stored in MaxHeap
   *
   * @return Greatest value from MaxHeap
   *
   * @throws Exception MaxHeap is considered empty
   */
  public String pop() throws Exception
  {
    // Nothing to "pop off" if MaxHeap is empty
    if( isEmpty() )
      throw new ArrayIndexOutOfBoundsException( "Heap is empty, nothing to remove" );

    // If only one value is stored there is nothing to "sift-down"
    else if( heapSize == 1 )
      return heap[ heapSize-- ];

    // Two or more stored values requires re-balancing each time an element is "popped off"
    else
    {
      // Placeholder for value removed from `heap`
      String rv = heap[ 1 ];

      // Element containing greatest value in `heap` gets value from last element in `heap`
      heap[ 1 ] = heap[ heapSize-- ];

      // Balance according to Binary Max-Heap invariant
      siftDown( 1 );

      // Return the removed value
      return rv;
    }
  }


  /**
   * Balances `heap` (i.e. organizes array) upon removal of the greatest value to ensure that the values stored in the
   * parent indices are greater than the values stored in the child indices
   *
   * @param i Index to "sift-down" from
   */
  public void siftDown( int i )
  {
    // Index can't be out-of-bounds
    if( (i < 1) || (i > heapSize) ) throw new IndexOutOfBoundsException( "i is an invalid index" );

    // Index of left-child element to element at position `i`
    int lci = getIndexOfLeftChild( i );

    // Value currently stored at first element of `heap`
    String temp = heap[ i ];

    // Until the last element in `heap` is reached
    while( lci <= heapSize )
    {
      /*
       * If the next index is not out-of-bounds and the value at the current index
       * is less than the value at the next index, set the current index to the
       * index of the child with the largest value:
       *
       *   i.e. the next child, i.e. the right-child.  Formulas for 1-Index Binary Heap child-element indices:
       *
       *     - left-child = (i * 2)
       *     - right-child = (i * 2) + 1
       */
      if( (lci < heapSize) && (heap[ lci ].compareTo( heap[ lci + 1 ] ) < 0) )
        lci++;

      /*
       * If the value at the child index is larger than the value at the current index,
       * the values need to be swapped
       */
      if( heap[ lci ].compareTo( temp ) > 0 )
      {
        // Assign the larger value at the child index to the value at the parent index
        heap[ i ] = heap[ lci ];

        // Assign the current index to the child index with the greater value
        i = lci;

        // Get the index of the next child
        lci = getIndexOfLeftChild( lci );
      }

      /*
       * If the value at the child index is not larger than the value at the parent index,
       * `heap` is re-balanced (i.e. properly sorted)
       */
      else
        break;
    }

    /*
     * `i` is now the index at which the values at the child indices will no longer be larger
     * than the value at the parent index
     */
    heap[ i ] = temp;
  }


  /**
   * Accepts an array of Strings to sort by converting `input` into a MaxHeap, sifting the values (i.e. sorting the
   * values), and then returning an array containing said values ordered from largest to smallest
   *
   * @param input An array of Strings to sort
   *
   * @return The array referenced by `input` resorted from largest to smallest
   *
   * @throws Exception If index is out-of-bounds
   */
  public String[] heapsort( String[] input ) throws Exception
  {
    // MaxHeap with which the sorting of `input` will be performed
    MaxHeap temp = new MaxHeap( input.length );

    // The array to place sorted values into and return
    String[] output = new String[ input.length ];

    // Puts all values from `input` into `temp`
    for( String s : input )
      temp.push( s );

    // Puts sorted values from `temp` into `output`
    for( int i = 0; !temp.isEmpty(); i++ )
      output[ i ] = temp.pop();

    return output;
  }


  /**
   * Doubles the size of `heap`'s array if the current array is not large enough to accept a new value.
   */
  public void ensureCapacity()
  {
    /*
    * New String array twice the length of the current array,
    * plus one for the dummy element
    */
    String[] temp = new String[ (heap.length * 2) + 1 ];

    // Copy values from the current array into `temp`
    System.arraycopy( heap, 1, temp, 1, heapSize );

    // Replace the current value of `heap` with `temp`
    heap = temp;
  }


  /**
   * Returns the index of the parent element of the given index
   * <p/>
   * Parent-index formula for 1-Index Binary Heap: i / 2
   *
   * @param i Index to find parent index of
   *
   * @return Index of the parent element of element `i`
   */
  public int getIndexOfParent( int i ){ return i / 2; }


  /**
   * Returns the index of the left-child element of the given index
   * <p/>
   * Left-child formula for 1-Index Binary Heap: i * 2
   *
   * @param i Index to find left-child of
   *
   * @return Index of the left-child element of element `i`
   */
  public int getIndexOfLeftChild( int i ){ return i * 2; }


  /**
   * Tests if `heap` is empty
   * <p/>
   * `heap` is considered empty if counter is equal to 0
   *
   * @return True if counter is equal to zero, false otherwise
   */
  public boolean isEmpty(){ return heapSize == 0; }


  /**
   * Tests if counter is equal to the size of `heap` - 1
   * <p/>
   * `heap` is considered full if counter is equal to size of `heap` - 1
   *
   * @return True if counter is equal to size of `heap`
   */
  public boolean isFull(){ return heapSize == heap.length - 1; }


  /**
   * Returns count of values considered to be stored in `heap`
   * <p/>
   * `heap` may contain values within its elements, but are disregarded if their index is of greater value that
   * `heapSize`
   *
   * @return Count of values considered to be stored in `heap`
   */
  public int getHeapSize(){ return heapSize; }


  /**
   * Loops through each element in `heap` and prints the value
   */
  public void printHeap()
  {
    System.out.print( "Heap = " );

    for( int i = 0; i <= getHeapSize(); i++ )
      System.out.print( "[ " + heap[ i ] + " ]" );

    System.out.println();
  }


  public static void main( String[] args ) throws Exception
  {
    // Instantiates new MaxHeap object with 10 elements (11 including the dummy element)
    MaxHeap heap = new MaxHeap( 10 );

    /*
     * Instantiate an array of Strings containing values to be used to test `push()`, `pop()`, `ensureCapacity()` and
     * `heapsort()`
     *
     * Values are meant to be unordered and be of large enough number to trigger `ensureCapacity()`
     */
    String[] test = new String[]{
      "foo", "bar", "zoidberg", "reynolds", "apple", "giuseppe", "salman", "chutzpah",
      "pickle", "venus", "jupiter", "sword", "book", "spatula", "onomatopoeia"
    };


    /*
     * Adds values to the MaxHeap object to test `push()` method
     */
    heap.printHeap(); // Print initial state of `heap`

    for( String s : test )
    {
      heap.push( s );
      System.out.println( "\nAdded: [ " + s + " ]" );
      heap.printHeap();
    }

    System.out.println( "\n*************\n" );


    /*
     * Removes each value from the MaxHeap object to test `pop()` method
     *
     * "Popped" value is printed to the console as well as the state of `heap` after the value was "popped"
     */
    heap.printHeap(); // Print state of `heap` post-push-test

    while( !heap.isEmpty() )
    {
      System.out.println( "\nRemoved: [ " + heap.pop() + " ]" );
      heap.printHeap();
    }

    System.out.println( "\n*************\n" );


    /*
     * Prints all values contained in `heap` after all values have been "pushed" and "popped off" to show state of
     * MaxHeap after use, and to show that only values within the range of `heap[ 1 ]` to `heapSize` are considered.
     * This is seen from the array containing multiple of the same values, and not having all non-null and null
     * values in any sort of order
     */
    System.out.println( "State of `heap` after use while `heapSize` == 0:\n" );

    for( String s : heap.heap )
      System.out.print( "[ " + s + " ]" );

    System.out.println( "\n\n*************\n" );


    /*
     * Prints each element in the array returned from `heapsort( test )` to test that the elements have been correctly
     * ordered from largest to smallest
     */
    System.out.println( "Sorting array `test` with `heapsort()`.  Initial `test` state:\n" );

    for( String s : test )
      System.out.print( "[ " + s + " ]" );

    System.out.println( "\n\n\nArray has been sorted.  Current state:\n" );

    for( String s : heap.heapsort( test ) )
      System.out.print( "[ " + s + " ]" );

    System.out.println( "\n\n************" );
  }
}
