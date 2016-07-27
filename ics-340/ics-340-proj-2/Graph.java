import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

/**
 * A somewhat messy attempt at creating my own Graph object with Breadth-First and Depth-First searches
 *
 * @author Kyle Heneman
 * @since 04/23/2015
 */
public class Graph
{
  // How many Vertices this Graph will contain
  private final int V;

  // Master adjacency list for Graph
  private static ArrayList<Vertex> adj = new ArrayList<Vertex>();

  // A list of visited vertices in the Graph, using ArrayList for convenience methods
  private static ArrayList<Integer> visited = new ArrayList<Integer>();

  // A temporary list of visited vertices for current group of vertices in Graph
  private static ArrayList<Integer> tempVisited = new ArrayList<Integer>();

  // A List of Object[] to hold groups of visited vertices
  private static ArrayList<Object[]> traversedSets = new ArrayList<Object[]>();


  /**
   * Instantiates new Graph object and its vertices
   *
   * @param n  Number of vertices Graph object will contain
   */
  public Graph( int n )
  {
    this.V = n;

    // Fill adj so NullPointerExceptions aren't being thrown everywhere
    int i = 0;
    while( i < V )
    {
      adj.add( new Vertex( i ) );
      i++;
    }
  }


  /**
   * Accepts an index and ensures it stays within bounds
   *
   * Ensures Graph contains vertices to add to or traverse
   *
   * @param i  Index to validate
   */
  private void validateVertex( int i )
  {
    /*
    * Number of vertices cannot be negative and
    * cannot exceed the declared limit
    */
    if( i < 0 || i >= getV() )
    {
      throw new IndexOutOfBoundsException( "Vertex " + i + " must be between 0 and " + (getV() - 1) );
    }

     /*
     * Attempts to access `adj`'s element at index i
     * and prints error message to console if one arises
     */
    try
    {
      adj.get( i );
    }
    catch( Exception e )
    {
      System.out.print( "Vertex " + i + " doesn't exist" );
    }
  }


  /**
   * Adds two vertices to each others adjacency lists
   *
   * @param v  First vertex to be added to neighbor's adjacency list
   * @param w  Second vertex to be added to neighbor's adjacency list
   */
  public void addEdge( int v, int w )
  {
    // Validate both vertics
    validateVertex( v );
    validateVertex( w );

    // Add both to each others adjacency lists
    adj.get( v ).setAdjacency( w );
    adj.get( w ).setAdjacency( v );
  }


  /**
   * Delegate for a Breadth-First Search starting with
   * vertex at position 0 in `adj` and a boolean
   * signaling that all vertices in `adj` should
   * be visited
   */
  public static void bfs()
  {
    bfs( 0, true );
  }


  /**
   * Delegate for a Breadth-First Search starting with
   * vertex at given index in `adj` and a boolean
   * indicating that only vertices reachable from
   * `start` vertex should be visited
   *
   * @param start  Index of vertex to start BFS on
   */
  public static void bfs( int start )
  {
    bfs( start, false );
  }


  /**
   * Performs a Breadth-First Search starting at given
   * index and inspects all vertices in adjacency list
   * or only those reachable from vertex at `start`
   *
   * @param start  Index of vertex to start BFS on
   * @param allSets  Indicate whether all vertices in graph should be inspected
   */
  private static void bfs( int start, boolean allSets )
  {
    // Current Vertex
    Vertex v;
    // Queue for vertices to be inspected
    ArrayDeque<Vertex> q = new ArrayDeque<Vertex>();

    // Add vertex from index `start` in `adj` to queue
    q.add( adj.get( start ) );

    // Add index to visited list
    visited.add( start );

    // Add index to list of vertices in this cycle that have been visited
    tempVisited.add( start );


    // While there are still verices in the queue to be inspected
    while( !q.isEmpty() )
    {
      // Get the next vertex in the queue
      v = q.remove();

      // Get the list of adjacencies from the current vertex
      ArrayList< Integer > ads = v.getAdjacencies();

      // For each neighbor of current vertex
      for( Integer neighbor : ads )
      {
        // Get the first neighbor of current vertex
        Vertex temp = adj.get( neighbor );

        /*
         * If that neighbor has not been visited yet,
         * i.e. the index of the neighbor is not in `visited`,
         * add that vertex to the queue, the visited list and the
         * visited list for this cycle
         */
        if( !visited.contains( temp.getData() ) )
        {
          q.add( temp );
          visited.add( temp.getData() );
          tempVisited.add( temp.getData() );
        }
      }
    }

    /*
     * Add the temporary list of visited vertices
     * to the collection of inspected vertices
     * as a separate array of Objects
     */
    traversedSets.add( tempVisited.toArray() );

    /*
     * At this point a cycle has been completed.
     *
     * If `allSets` is true then the whole Graph
     * needs to be inspected, and the BFS should
     * continue at the next vertex in `adj`
     *
     * The traversal can't continue if the list of
     * visited vertices is larger than the list of
     * vertices that exists, so `visited` must be
     * a smaller size than `adj`
     *
     * If BFS should continue, the temporary list of
     * visited vertices needs to be cleared for reuse
     * and have `bfs()` called with the index of the
     * next vertex in the adjacency list and the value
     * of `allSets`
     *
     * Otherwise the traversal of the Graph can be
     * considered complete.  The list of visited
     * vertices within their cycle sets is printed
     * and the lists of visited vertices and traversed
     * sets is cleared for reuse.  Clearing the lists
     * was mostly for debugging
     */
    if( allSets && (adj.size() > visited.size()) )
    {
      tempVisited.clear();
      bfs( visited.size(), allSets );
    }
    else
    {
      printSets( traversedSets, allSets );
      visited.clear();
      tempVisited.clear();
      traversedSets.clear();
    }
  }


  /**
   * Delegate for a Depth-First Search starting with
   * vertex at position 0 in `adj` and a boolean
   * signaling that all vertices in `adj` should
   * be visited
   */
  public static void dfs()
  {
    dfs( 0, true );
  }


  /**
   * Delegate for a Breadth-First Search starting with
   * vertex at given index in `adj` and a boolean
   * indicating that only vertices reachable from
   * `start` vertex should be visited
   *
   * @param start  Index of vertex to start BFS on
   */
  public static void dfs( int start )
  {
    dfs( start, false );
  }


  /**
   * Performs a Depth-First Search starting at given
   * index and inspects all vertices in adjacency list
   * or only those reachable from vertex at `start`
   *
   * A Stack is used in this search to emulate a recursive
   * implementation rather than a queue like with breadth-first
   *
   * @param start  Index of vertex to start BFS on
   * @param allSets  Indicate whether all vertices in graph should be inspected
   */
  private static void dfs( int start, boolean allSets )
  {
    // Current Vertex
    Vertex v;
    // Stack for vertices to be inspected
    Stack<Vertex> s = new Stack<Vertex>();

    // Add vertex from index `start` in `adj` to stack
    s.push( adj.get( start ) );

    // While there are vertices in the stack yet to inspect
    while( !s.isEmpty() )
    {
      // Get the next vertex from the stack
      v = s.pop();

      // Get the list of adjacencies from the current vertex
      ArrayList<Integer> ads = v.getAdjacencies();

      /*
       * If the current vertex has not been visited,
       * add it to the list of visited verices and to
       * the list of vertices that have been visited
       * in this cycle
       */
      if( !visited.contains( v.getData() ) )
      {
        visited.add( v.getData() );
        tempVisited.add( v.getData() );

        /*
         * Add each of the current vertex's neighbors
         * to the stack to be inspected
         */
        for( Integer neighbor : ads )
        {
          s.push( adj.get( neighbor ) );
        }
      }
    }


    /*
     * Add the temporary list of visited vertices
     * to the collection of inspected vertices
     * as a separate array of Objects
     */
    traversedSets.add( tempVisited.toArray() );


    /*
     * At this point a cycle has been completed.
     *
     * If `allSets` is true then the whole Graph
     * needs to be inspected, and the DFS should
     * continue at the next vertex in `adj`
     *
     * The traversal can't continue if the list of
     * visited vertices is larger than the list of
     * vertices that exists, so `visited` must be
     * a smaller size than `adj`
     *
     * If DFS should continue, the temporary list of
     * visited vertices needs to be cleared for reuse
     * and have `dfs()` called with the index of the
     * next vertex in the adjacency list and the value
     * of `allSets`
     *
     * Otherwise the traversal of the Graph can be
     * considered complete.  The list of visited
     * vertices within their cycle sets is printed
     * and the lists of visited vertices and traversed
     * sets is cleared for reuse.  Clearing the lists
     * was mostly for debugging
     */
    if( adj.size() > visited.size() && allSets )
    {
      tempVisited.clear();
      dfs( visited.size(), allSets );
    }
    else
    {
      printSets( traversedSets, allSets );
      visited.clear();
      tempVisited.clear();
      traversedSets.clear();
    }
  }


  /**
   * Prints the Object arrays of traversed sets to the console
   * in a visually pleasing way
   *
   * @param sets  A collection of the traversed vertices with each
   *              cycle having its own array in the collection
   *
   * @param allSets  Used to determine if outer brackets should be printed
   *                 if distinct cycles are to be printed
   */
  private static void printSets( ArrayList<Object[]> sets, boolean allSets )
  {
    /*
     * If multiple cycles are present, an outer bracket needs to be printed
     * to indicate the output as a 2D array, and each inner array as a distinct
     * cycle
     */
    System.out.print( allSets ? "[ " : "" );

    // Delimiter for elements within each set
    String delim1 = "";

    // Delimiter for each set of vertices
    String delim2 = allSets ? ", " : "";

    // For each set of vertices in `sets`
    for( Object[] set : sets )
    {
      // Print an opening bracket for start of array
      System.out.print( "[" );

      // For each element in that array
      for( Object o : set )
      {
        // Print the inner-delimiter and the element
        System.out.print( delim1 + o.toString() );

        // Set the inner-delimiter to a comma
        delim1 = ",";
      }

      // Close out the inner arrays
      System.out.print( "]" + delim2 );

      // Reset the Delimiters
      delim1 = "";
      delim2 = "";
    }

    // Print the final closing bracket if
    // more than one set was present
    System.out.println( allSets ? " ]" : "" );
  }


  /**
   * Builds a String representation of the Graph object in dot format
   *
   * @return  A String with the dot format representation of the Graph object
   */
  public String toDoT()
  {
    // StringBuilder for to build Strings with
    StringBuilder sb = new StringBuilder();

    // Delimiter for list
    String delim = "";

    // Define the beginning of a Graph
    sb.append( "graph {\n" );

    // For each vertex in the adjacency list
    for( Vertex vertex : adj )
    {
      // For each neighbor of that vertex
      for( int neighbor : vertex.getAdjacencies() )
      {
        /*
         * Since the `data` of each vertex is simply its
         * index within `adj`, that value is used in this
         * comparison.
         *
         * Since we're iterating through a list, we know that
         * any vertices with an index value lower than the current
         * index value has already been listed, and since this is an
         * undirected digraph, each edge only needs to be listed once
         */
        if( !(neighbor < vertex.getData()) )
        {
          sb.append( "    " ).append( vertex.getData() );
          delim = " -- ";
          sb.append( delim ).append( neighbor ).append( ";\n" );
        }
      }
    }

    // Finish Graph definition
    sb.append( "}" );

    return sb.toString();
  }


  // Returns value of V
  public int getV()
  {
    return V;
  }


  // Copies the output of toDot() to the system clipboard
  public static void copyToClipboard( String s )
  {
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents( new StringSelection( s ), null );
  }


  public static void main( String[] args )
  {
    /*
     * Just to have some sort of semblence to work towards,
     * I used the 11-vertex Graph in the last examples on the
     * assignment page.
     */


//    Graph g = new Graph( 11 );
//
//    g.addEdge( 10, 9 );
//    g.addEdge( 10, 7 );
//    g.addEdge( 9, 8 );
//    g.addEdge( 8, 7 );
//    g.addEdge( 6, 4 );
//    g.addEdge( 6, 3 );
//    g.addEdge( 6, 0 );
//    g.addEdge( 5, 2 );
//    g.addEdge( 5, 1 );
//    g.addEdge( 4, 3 );
//    g.addEdge( 4, 1 );
//    g.addEdge( 4, 0 );
//    g.addEdge( 3, 0 );
//    g.addEdge( 2, 1 );
//    g.addEdge( 1, 0 );
//
//    dfs();
//    dfs( 2 );


    Graph g = new Graph( 11 );

    g.addEdge( 0, 1 );
    g.addEdge( 0, 3 );
    g.addEdge( 0, 4 );
    g.addEdge( 0, 6 );
    g.addEdge( 1, 2 );
    g.addEdge( 1, 4 );
    g.addEdge( 1, 5 );
    g.addEdge( 2, 5 );
    g.addEdge( 3, 4 );
    g.addEdge( 3, 6 );
    g.addEdge( 4, 6 );
    g.addEdge( 7, 8 );
    g.addEdge( 7, 10 );
    g.addEdge( 8, 9 );
    g.addEdge( 9, 10 );

    System.out.println("BFS:");
    bfs();
    bfs( 2 );

    String dot = g.toDoT();
    System.out.println( dot );
    copyToClipboard( dot );
  }
}
