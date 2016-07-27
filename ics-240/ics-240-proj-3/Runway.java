/**
 * Simulates a Runway that can be occupied
 * during Arrivals and Departures
 *
 * @author Kyle Heneman
 * @since 10/11/2014
 *
 * <a href="http://www.kyleheneman.com">kyleheneman.com</a>
 */

public class Runway
{
  /**
   * Invariants of Runway
   * *********************
   *
   * - `timeLeft` must be >= 0
   *
   * - `timeLeft` must be decrement with each
   *   iteration of the simulation
   */


  // Time left until Runway is free
  private int timeLeft;

  /**
   * Initializes Runway in a free state
   *
   * - Precondition: A new Runway is being created
   *
   * - Postcondition: `timeLeft` equals 0, a new Runway was returned
   */

  public Runway()
  {
    timeLeft = 0;
  }


  /**
   * Start processing of Arrival or Departure
   *
   * - Precondition: `timeLeft` has been initialized
   *
   * - Postcondition: `timeLeft` equals `duration`
   *
   * @param duration Duration of Arrival or Departure
   */

  void startDuration(int duration)
  {
    timeLeft = duration;
  }


  /**
   * Tests if Runway is busy
   *
   * - Precondition: `timeLeft` has been initialized
   *
   * - Postcondition: `timeLeft` is unchanged
   *
   * @return true If `timeLeft > 0`
   */

  boolean isBusy()
  {
    return (timeLeft > 0);
  }


  /**
   * Decrement time left in busy state
   *
   * - Precondition: `timeLeft` has been initialized and is > 0
   *
   * - Postcondition: `timeLeft` has been decremented
   */

  void reduceTime()
  {
    if(isBusy())
    {
      timeLeft--;
    }
  }
}
