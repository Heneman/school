public class Heap
{
  private Comparable[] heap;
  private int size;


  public Heap(int n)
  {
    if (n < 0)
      throw new IllegalArgumentException();

    size              = 0;
    heap = new Comparable[n + 1];
  }

  public int size()
  {
    return size;
  }


  public void insert(Comparable value)
  {
    if (value == null)
      throw new IllegalArgumentException();

    if (size == heap.length - 1)
    {
      Comparable[] temp = new Comparable[(heap.length * 2)+ 1 ];
      System.arraycopy(heap, 1, temp, 1, size );
      heap = temp;
    }

    heap[++size] = value;     // add at end of array
    siftUp(size); // restore heap property
  }

  private void siftUp(int pos)
  {
    if (pos < 1 || pos > size || size > heap.length)
      throw new IllegalArgumentException();

    int child  = pos;
    int parent = child / 2;
    Comparable value = heap[child];

    while (parent > 0)
    {
      if (heap[parent].compareTo(value) < 0)
      {
        heap[child] = heap[parent];
        child = parent;
        parent = parent / 2;
      }
      else
        break;
    }

    heap[child] = value;
  }


  public Comparable remove() throws Exception
  {
    switch (size)
    {
      case 0:
        throw new Exception();

      case 1:
        return heap[size--]; // special case for size = 1

      default:
        Comparable ret = heap[1]; // will return top element
        heap[1] = heap[size--];   // move last element at top and adjust size
        siftDown(1);  // restore heap property
        return ret;
    }
  }

  private void siftDown(int pos)
  {
    if (pos < 1 || pos > size || size > heap.length)
      throw new IllegalArgumentException();

    int parent = pos;
    int child  = 2 * parent;
    Comparable value = heap[parent];

    while (child <= size)
    {
      if (child < size && heap[child].compareTo(heap[child + 1]) < 0)
        child++;

      if (heap[child].compareTo(value) > 0)
      {
        heap[parent] = heap[child];
        parent    = child;
        child     = 2 * child;
      }
      else
        break;
    }

    heap[parent] = value;
  }

  /*
   * Transform an array into a heap
   * 
   * @param A   array that we want to transform into a heap
   * @param size   number of elements
   * @exception IllegalArgumentException   size > A.length
   * @since   1.0
   * */
  private void heapify(Comparable[] A, int size)
  {
    if (size > A.length)
      throw new IllegalArgumentException();

    for (int i = size / 2; i > 0; i--)
      siftDown(i);
  }

  public void printHeap()
  {
    System.out.print( "Heap = " );
    for( int i = 0; i < size; i++ )
    {
      System.out.print( "[" + heap[ i ] + "]" );
    }
    System.out.println();
  }

  public boolean isEmpty()
  {
    return size == 0;
  }

  public static void main( String[] args ) throws Exception
  {
    Heap heap = new Heap( 10 );

    heap.insert( "foo" );
    heap.printHeap();
    heap.insert( "bar" );
    heap.printHeap();
    heap.insert( "zoidberg" );
    heap.printHeap();
    heap.insert( "reynolds" );
    heap.printHeap();
    heap.insert( "apple" );
    heap.printHeap();
    heap.insert( "giuseppe" );
    heap.printHeap();
    heap.insert( "salman" );
    heap.printHeap();
    heap.insert( "chutzpah" );
    heap.printHeap();
    heap.insert( "pickle" );
    heap.printHeap();
    heap.insert( "venus" );
    heap.printHeap();
    heap.insert( "jupiter" );
    heap.printHeap();
    heap.insert( "sword" );
    heap.printHeap();
    heap.insert( "book" );
    heap.printHeap();
    heap.insert( "spatula" );
    heap.printHeap();

    System.out.println( "\n*************\n" );

    while ( !heap.isEmpty() )
    {
      System.out.print( "[" + heap.remove() + "]" );
    }
  }
}
