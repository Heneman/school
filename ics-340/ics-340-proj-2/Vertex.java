import java.util.ArrayList;

/**
 * Created by kyle on 4/23/15.
 */
public class Vertex
{
  private int data;
  private ArrayList<Integer> adjacencies;

  public Vertex( int n )
  {
    if( n < 0 )
      throw new IllegalArgumentException( "Number of vertices must be non-negative" );

    setData( n );
    adjacencies = new ArrayList<Integer>();
  }

  private void setData( int n )
  {
    this.data = n;
  }

  public int getData()
  {
    return this.data;
  }

  public void setAdjacency( int i )
  {
    adjacencies.add( i );
  }

  public ArrayList<Integer> getAdjacencies()
  {
    return adjacencies;
  }
}
