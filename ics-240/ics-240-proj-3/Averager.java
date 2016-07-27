/**
 * Created by Kyle Heneman
 *
 * @since 10/11/2014
 *
 * Adds and averages total wait times, tracks number of customers
 *
 * Taken from reading
 */

class Averager
{
  /**
   * Invariants of Averager:
   * ***********************
   *
   * - `arrivalCount` must be >= 0 and increment
   *   with each new Arrival
   *
   * - `departureCount` must be >= 0 and increment
   *   with each new Departure
   *
   * - `arrivalSum` must contain a summation of wait
   *   times in Arrival Queue and be >= 0
   *
   * - `departureSum` must contain a summation of wait
   *   times in Departure Queue and be >= 0
   *
   * - `crashCount` must increment each time a Plane's fuel
   *   reaches 0
   */

  // Number of Arrivals
  private int arrivalCount;

  // Number of Departures
  private int departureCount;

  // 'Time' spent in Arrival Queue
  private double arrivalSum;

  // 'Time' spent in Departure Queue
  private double departureSum;

  // Number of Crashes
  private int crashCount;


  /**
   * Creates new Averager
   *
   * - Precondition: A new Averager is being created
   *
   * - Postcondition: `arrivalCount`, `arrivalSum`, `departureCount`,
   *                  and `departureSum` all equal 0, a new Averager
   *                  is returned
   */

  public Averager(){
    arrivalCount = 0;
    arrivalSum = 0;

    departureCount = 0;
    departureSum = 0;
  }


  /**
   * Add wait time of new Arrival
   *
   * - Precondition: `arrivalCount` < Integer.MAX_VALUE,
   *                 `arrivalSum` has been initialized,
   *                 `num` is a double >= 0
   *
   * - Postcondition: `arrivalSum` equals the previous `arrivalSum` value
   *                  + `num` value, `arrivalCount` is incremented
   *
   * @param num Wait time of new Arrival
   */

  public void addArrivalWaitTime(double num){
    if( arrivalCount == Integer.MAX_VALUE){
      throw new IllegalStateException( "Too many numbers" );
    }
    arrivalSum += num;
    arrivalCount++;
  }

  /**
   * Add wait time of new Departure
   *
   * - Precondition: `departureCount` < Integer.MAX_VALUE,
   *                 `departureSum` has been initialized,
   *                 `num` is a double >= 0
   *
   * - Postcondition: `departureSum` equals the previous `departureSum` value
   *                  + `num` value, `departureCount` is incremented
   *
   * @param num Wait time of new Departure
   */

  public void addDepartureWaitTime(double num){
    if( arrivalCount == Integer.MAX_VALUE){
      throw new IllegalStateException( "Too many numbers" );
    }
    departureSum += num;
    departureCount++;
  }


  /**
   * Get average Arrival wait time
   *
   * - Precondition: `arrivalCount` has been initialized and is > 0,
   *                 `arrivalSum` is >= 0
   *
   * - Postcondition: `arrivalSum/arrivalCount` is returned,
   *                  `arrivalSum` and `arrivalCount` are unchanged
   *
   * @return Average of Arrival wait time
   */

  public double getArrivalAverage()
  {
    if( arrivalCount == 0)
    {
      return Double.NaN;
    }
    else
    {
      return arrivalSum/arrivalCount;
    }
  }


  /**
   * Get average Departure wait time
   *
   * - Precondition: `departureCount` has been initialized and > 0,
   *                 `departureSum` is >= 0
   *
   * - Postcondition: `departureSum/departureCount` is returned,
   *                  `departureSum` and `departureCount` are unchanged
   *
   * @return Average Departure wait time
   */
  public double getDepartureAverage()
  {
    if( departureCount == 0 )
    {
      return Double.NaN;
    }
    else
    {
      return departureSum/departureCount;
    }
  }


  /**
   * Get number of Arrivals
   *
   * - Precondition: `arrivalCount` has been initialized
   *
   * - Postcondition: `arrivalCount` is unchanged
   *
   * @return Number of Arrivals
   */

  public int getArrivalCount()
  {
    return arrivalCount;
  }


  /**
   * Get number of Departures
   *
   * - Precondition: `departureCount` has been initialized
   *
   * - Postcondition: `departureCount` is unchanged
   *
   * @return Number of Departures
   */

  public int getDepartureCount()
  {
    return departureCount;
  }


  /**
   * Add to Crash count
   *
   * - Precondition: `crashCount` has been initialized
   *
   * - Postcondition: `crashCount` has been incremented
   */

  public void addCrash()
  {
    crashCount++;
  }


  /**
   * Get number of Crashes
   *
   * - Precondition: `crashCount` has been initialized
   *
   * - Postcondition: `crashCount` is unchanged
   *
   * @return Number of Crashes
   */

  public int getCrashCount()
  {
    return crashCount;
  }


  /**
   * Clear all numbers
   *
   * - Preconditions: `arrivalCount`, `arrivalSum`, `departureCount`, `departureSum`
   *                  and `crashCount` are all initialized
   *
   * - Postconditions: `arrivalCount`, `arrivalSum`, `departureCount`, `departureSum`
   *                   and `crashCount` are all == 0
   */

  public void flush()
  {
    arrivalCount = 0;
    departureCount = 0;
    arrivalSum = 0;
    departureSum = 0;
    crashCount = 0;
  }
}
