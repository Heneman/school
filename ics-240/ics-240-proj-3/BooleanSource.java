/**
 * Source for new Planes given a Probability
 *
 * Taken from reading
 *
 * @since 10/11/2014
 */

class BooleanSource
{
  /**
   * Invariants of BooleanSource:
   * ****************************
   *
   * - `probability` must be > 0 and < 1
   */

  // Probability of true event
  private final double probability;


  /**
   * Creates new BooleanSource
   *
   * - Precondition: A new BooleanSource is being created, `p`
   *                 is a double > 0 and < 1
   *
   * - Postcondition: `probability` equals `p` and a new BooleanSource
   *                  was returned
   *
   * @param p Probability of new Plane for a Queue
   */
  public BooleanSource(double p)
  {
    if ((p < 0) || (p > 1))
    {
      throw new IllegalArgumentException( "Illegal probability rate: " + p );
    }
    else
    {
      probability = p;
    }
  }


  /**
   * Query for new Plane event
   *
   * - Precondition: `probability` has been initialized
   *
   * - Postcondition: `probability` is unchanged
   *
   * @return If new Arrival happened or not
   */
  public boolean query()
  {
    return (Math.random() < probability);
  }
}
