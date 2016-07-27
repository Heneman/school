/**
 * Wrapper Class for data about 'planes'
 *
 * @author Kyle Heneman
 * @since 10/11/2014
 */

class Plane
{
  /**
   * Invariants for Plane
   * ***********************
   *
   * - `timeStamp` must be >= 0
   *
   * - `fuelAmount` must be > 0
   *
   * - `fuelAmount` must decrement in each
   *   iteration of the simulation
   *
   * - `crashed` must be false until
   *   `fuelAmount` = 0, then `crashed`
   *   must be true.
   */

  // 'Time' the Plane was put into Queue
  private final int timeStamp;

  // 'Time' Plane can wait in Arrival Queue
  private int fuelAmount;

  // If Plane ran out of 'fuel'
  private boolean crashed;


  /**
   * Creates a new Plane
   *
   * - Precondition: `fuels` and `time` are ints > 0,
   *                 `crashed` is false
   *
   * - Postcondition: `timeStamp` equals `time`, `fuelAmount` equals
   *                  `fuels`, `crashed` equals false and a new
   *                  Plane is returned
   *
   * @param fuels Amount of 'time' Plane can stay in Arrival Queue
   *
   * @param time 'Time' that Plane was entered into Queue
   */

  public Plane(int fuels, int time)
  {
    timeStamp = time;
    fuelAmount = fuels;
    crashed = false;
  }


  /**
   * Gets 'time' Plane was entered into Queue
   *
   * - Precondition: `timeStamp` has been initialized
   *
   * - Postcondition: `timeStamp` is unchanged
   *
   * @return timeStamp 'Time' Plane was entered into Queue
   */
  public int getTimeStamp()
  {
    return timeStamp;
  }


  /**
   * Decrements 'time' Plane has 'fuel' for
   *
   * - Precondition: `fuelAmount` has been initialized
   *
   * - Postcondition: `crashed` is true if `fuelAmount` <= 0,
   *                  `fuelAmount` is decremented if > 0
   */
  public void burnFuel()
  {
    if( fuelAmount > 0 )
    {
      fuelAmount--;
    }
    else
    {
      crashed = true;
    }
  }


  /**
   * Plane ran out of 'fuel'
   *
   * - Precondition: `crashed` has been initialized,
   *                 `fuelAmount` <= 0
   *
   * - Postcondition: `crashed` equals true
   */
  public void crash()
  {
    crashed = true;
  }


  /**
   * Checks if Plane 'crashed'
   *
   * - Precondition: `crashed` has been initialized
   *
   * - Postcondition: `crashed` is unchanged
   *
   * @return true If Plane crashed
   */
  public boolean isCrashed()
  {
    return crashed;
  }
}
