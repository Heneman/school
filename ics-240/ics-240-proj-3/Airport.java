import java.util.LinkedList;
import java.util.Queue;

/**
 * Models an Airport with an Arrival Queue, Departure Queue and
 * a variable amount of Runways
 *
 * @author Kyle Heneman
 * @since 10/11/2014
 *
 * <a href="http://www.kyleheneman.com">kyleheneman.com</a>
 */

class Airport
{
  /**
   * Invariants of Airport:
   * **********************
   *
   * - ARRIVAL_DUR must be assigned the value
   *   given for "Duration of Arrivals" field.
   *
   * - ARRIVAL_DUR must be an int > 0
   *
   * - DEPARTURE_DUR must be assigned the value
   *   given for "Duration of Departures" field.
   *
   * - DEPARTURE_DUR must be an int > 0
   *
   * - currentMinute must be an int >= 0 and < `simTime`
   *
   * - currentMinute must increment in each iteration
   *   of the simulation
   *
   * - currentMinute must be used as value for `timeStamp`
   *   in each Plane
   *
   * - `averager` must log every Plane getting added to
   *   `arrivals` and `departures` Queues
   *
   * - `averager` must keep count of all Arrivals,
   *    Departures, and Crashes
   *
   * - `averager` must give an average wait time
   *   spent in each Queue
   *
   * - `arrivals` must hold Planes in a LinkedList
   *   in the order in which they arrive
   *
   * - `departures` must hold Planes in a LinkedList
   *   in the order in which they are ready to depart
   *
   * - `arrivals` and `departures` must grow and shrink
   *   to accommodate new Planes in each Queue
   *
   * - `runways` must be as big as the value given in
   *   "Number of Runways" field
   *
   * - `runways` must hold at least one Runway object
   */


  // Durations for each Arrival and Departure
  private final int ARRIVAL_DUR;
  private final int DEPARTURE_DUR;

  // Current 'timestamp'
  private static int currentMinute;

  // Airport Averager
  private static final Averager averager = new Averager();

  // Arrival and Departure Queues
  private final Queue<Plane> arrivals = new LinkedList<Plane>();
  private final Queue<Plane> departures = new LinkedList<Plane>();

  // Array of Runways
  private static Runway[] runways;


  /**
   * Creates new Airport with specified
   * Arrival and Departure durations
   *
   * - Precondition: `arriveDur` and `departDur` must be
   *               ints >= 0
   *
   * - Postcondition: `arriveDur` value is assigned to ARRIVAL_DUR,
   *                  `departDur` value is assigned to DEPARTURE_DUR,
   *                  a new Airport is returned
   *
   * @param arriveDur Amount of 'minutes' one Arrival takes
   * @param departDur Amount of 'minutes' one Departure takes
   */

  private Airport( int arriveDur, int departDur )
  {
    ARRIVAL_DUR = arriveDur;
    DEPARTURE_DUR = departDur;
  }


  /**
   * Reduces the remaining processing
   * 'time' of each Runway in `runways`
   *
   * - Precondition: `runways` contains at least one Runway
   *
   * - Postcondition: `currentMinute` has been decremented for
   *                  each Runway in `runways`
   */

  void reduceRemainingTime()
  {
    for( Runway runway : runways )
    {
      if ( runway.isBusy() )
      {
        runway.reduceTime();
      }
    }
  }


  /**
   * Tests if Arrivals Queue is empty
   *
   * - Precondition: `arrivals` has been initialized
   *
   * - Postcondition: `arrivals` is unchanged
   *
   * @return true If the number of Planes in `arrivals` = 0
   */

  boolean arrivalsEmpty()
  {
    return arrivals.isEmpty();
  }


  /**
   * Add new Plane to `arrivals`
   *
   * - Precondition: `arrivals` has been initialized, `newArrival` is a Plane
   *
   * - Postcondition: `newArrival` has been added to `arrivals`, `arrivals`
   *                  contains `newArrival`
   *
   * @param newArrival New Plane to add to `arrivals`
   */

  void addToArrivals( Plane newArrival )
  {
    arrivals.add( newArrival );
  }


  /**
   * Dequeues next Plane in `arrivals`
   * and tests if it is 'crashed'
   *
   * Logs amount of 'time' plane spent in Queue
   *
   * Makes `runway` busy for specified Arrival time
   *
   * - Precondition: `arrivals` has been initialized and not empty,
   *                 `averager` has been initialized, `runway` is a
   *                 non-busy Runway
   *
   * - Postcondition: `arrivals` no longer contains Plane `next`, `averager`
   *                  increments `crashCount` if `next.isCrashed()` is true,
   *                  `averager` logged wait time of `next` in Arrival Queue,
   *                  and `runway` becomes busy for ARRIVAL_DUR amount if
   *                  `next.isCrashed()` is false
   *
   * @param runway Runway with which to process `next`
   */

  void processArrival(Runway runway)
  {
    if( !(runway.isBusy()) )
    {
      Plane next = arrivals.remove();

      if( next.isCrashed() )
      {
        averager.addCrash();
      }
      else
      {
        averager.addArrivalWaitTime( currentMinute - next.getTimeStamp() );
        runway.startDuration( ARRIVAL_DUR );
      }
    }
  }


  /**
   * Tests if Departures Queue is empty
   *
   * - Precondition: `departures` has been initialized
   *
   * - Postcondition: `departures` is unchanged
   *
   * @return true If number of Planes in `departures` = 0
   */

  boolean departuresEmpty()
  {
    return departures.isEmpty();
  }


  /**
   * Add new Plane to `departures`
   *
   * - Precondition: `departures` has been initialized, `newDeparture` is a Plane
   *
   * - Postcondition: `newDeparture` is added to `departures`, `departures`
   *                  contains `newDeparture`
   *
   * @param newDeparture New Plane to add to `departures`
   */

  void addToDepartures( Plane newDeparture )
  {
    departures.add( newDeparture );
  }


  /**
   * Dequeues next Plane in `departures`
   * and tests if it is 'crashed'
   *
   * Logs amount of 'time' plane spent in Queue
   *
   * Makes `runway` busy for specified Departure time
   *
   * - Precondition: `departures` has been initialized and not empty,
   *                 `averager` is initialized, `runway` is a
   *                 non-busy Runway
   *
   * - Postcondition: `departures` no longer contains Plane `next`,
   *                  `averager` logged wait time of `next` in
   *                  Departure Queue, `runway` is busy for
   *                  DEPARTURE_DUR amount
   *
   * @param runway Runway with which to process `next`
   */

  void processDeparture(Runway runway)
  {
    if( !(runway.isBusy()) )
    {
      Plane next = departures.remove();
      averager.addDepartureWaitTime( currentMinute - next.getTimeStamp() );
      runway.startDuration( DEPARTURE_DUR );
    }
  }


  /**
   * Tells each Plane in `arrivals` to burn its fuel
   *
   * - Precondition: `arrivals` is initialized and not empty
   *
   * - Postcondition: Each Plane in `arrivals` has one less
   *                  fuel unit
   */

  void burnFuel()
  {
    for( Plane plane : arrivals )
    {
      plane.burnFuel();
    }
  }


  /**
   * Simulates Planes arriving, landing, departing and taking-off
   *
   * Prints report of given inputs,
   * Arrival, Departure, and Crash counts,
   * and Average Time spent in Arrival and
   * Departure Queues
   *
   * - Precondition: `arriveDur`, `departDur`, `fuelTime`, `runwayCount`,
   *                 and `simTime` are ints > 0,
   *                 `arriveProb` and `departProb` are doubles > 0 and < 1
   *
   * - Postcondition: `airport.currentMinute` == `simTime`, `averager` values
   *                  are flushed, `reportFigures` has been built and returned
   *
   * @param arriveDur Amount of 'time' a Runway is busy with a new arrival
   * @param arriveProb Probability a new Plane is entered into Arrival Queue
   * @param departDur Amount of 'time' a Runway is busy with a new departure
   * @param departProb Probability a new Plane is entered into Departure Queue
   * @param fuelTime Amount of 'time' a Plane can stay in Queue before it 'crashes'
   * @param runwayCount Amount of Runways able to process Planes
   * @param simTime Total amount of 'time' to run `simulation`
   *
   * @return reportFigures Report of inputs, counts, and averages
   */

  public static String simulate(int arriveDur, double arriveProb,
                                int departDur, double departProb,
                                int fuelTime, int runwayCount, int simTime)
  {
    // Triggers new Arrival or Departure
    final BooleanSource arrive = new BooleanSource( arriveProb );
    final BooleanSource depart = new BooleanSource( departProb );

    // Airport to hold Planes and Runways
    final Airport airport = new Airport( arriveDur, departDur );

    // Array to hold specified amount of Runways
    runways = new Runway[ runwayCount ];

    // Seed `runways` with new Runways
    for( int i = 0; i < runwayCount; i++)
    {
      runways[i] = new Runway();
    }

    // Start simulating
    for( currentMinute = 0; currentMinute < simTime; currentMinute++)
    {
      // New Plane arrives
      if(arrive.query())
      {
        airport.addToArrivals( new Plane( fuelTime, currentMinute ) );
      }

      // New Plane ready to depart
      if(depart.query())
      {
        airport.addToDepartures( new Plane( fuelTime, currentMinute ) );
      }

      // Arrivals have priority
      for(Runway runway : runways)
      {
        if( !(runway.isBusy()) )
        {
          if( !(airport.arrivalsEmpty()) )
          {
            airport.processArrival( runway );
          }
          else if( !(airport.departuresEmpty()) )
          {
            airport.processDeparture( runway );
          }
        }
      }

      // Burn Plane fuel
      airport.burnFuel();

      // Reduces remaining busy time in Runways
      airport.reduceRemainingTime();
    }

    // Build report
    String reportFigures = "\n**** Inputs ****" +
                           "\nArrival Duration: " + String.valueOf( arriveDur ) + " minutes" +
                           "\nArrival Probability: " + String.valueOf( arriveProb * 100 ) + "%" +
                           "\nDeparture Duration: " + String.valueOf( departDur ) + " minutes" +
                           "\nDeparture Probability: " + String.valueOf( departProb * 100 ) + "%" +
                           "\nMinutes of Fuel: " + String.valueOf( fuelTime ) + " minutes" +
                           "\nNumber of Runways: " + String.valueOf( runwayCount ) +
                           "\nSimulation Time: " + String.valueOf( simTime ) + " minutes\n" +

                           "\n**** Report ****" +
                           "\nNumber of Arrivals: " + String.valueOf( averager.getArrivalCount() ) +
                           "\nCrash Count: " + String.valueOf( averager.getCrashCount() ) +
                           "\nNumber of Departures: " + String.valueOf( averager.getDepartureCount() ) +
                           "\nAverage Arrival Wait Time: " + String.valueOf( averager.getArrivalAverage() ) + " minutes" +
                           "\nAverage Departure Wait Time: " + String.valueOf( averager.getDepartureAverage() ) + " minutes" +
                           "\n\n***************************************************************\n\n";

    // Clears values in `averager`
    averager.flush();

    return reportFigures;
  }
}
