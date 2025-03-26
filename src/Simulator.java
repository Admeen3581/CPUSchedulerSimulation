import java.util.ArrayList;

/**
 * @author Adam Long
 */

public class Simulator
{
   private static final ArrayList<Event> eventQueue = new ArrayList<>();
   private static int totalProcesses = 0;
   private static double currentClock = 0;
   private static int readyQueueCount = 0;

   private static int lambda;
   private static double mew;
   private static boolean CPUIdle = true;


   public static void runSimulator(int lambdaRate, double serviceRate)
   {
      lambda = lambdaRate;
      mew = serviceRate;

      simulatorInit();

      Event currEvent;
      while(totalProcesses < 10000)
      {
         currEvent = eventQueue.remove(0);

         currentClock += currEvent.getServeTime();

         if(currEvent.getType() == eventType.ARRIVAL)
         {
            arrivalHandler(currEvent);
            System.out.println("arrivial");
         }
         else
         {
            departureHandler(currEvent);
            System.out.println("depaert");
         }
      }
   }


   private static void arrivalHandler(Event currEvent)
   {
      Process currProcess = currEvent.getProcess();

      Process nextProcess = new Process(currProcess.getID()+1,
            DistributionCalculations.exponentialVariable(lambda),
            DistributionCalculations.exponentialVariable(mew));

      Event nextEvent = new Event(eventType.ARRIVAL, nextProcess);

      eventQueue.add(nextEvent);

      if (!CPUIdle)
      {
         readyQueueCount++;
      }
      else
      {
         CPUIdle = false;
         Event departure = new Event(eventType.DEPARTURE, currProcess);
         eventQueue.add(departure);
      }
   }

   private static void departureHandler(Event currEvent)
   {
      Process currProcess = currEvent.getProcess();

      totalProcesses++;

      if(readyQueueCount == 0)
      {
         CPUIdle = true;
      }
      else
      {
         readyQueueCount--;
         Event departure = new Event(eventType.DEPARTURE, currProcess);
         eventQueue.add(departure);
      }
   }

   private static void simulatorInit()
   {
      Process initProcess = new Process(1,
            DistributionCalculations.exponentialVariable(lambda),
            DistributionCalculations.exponentialVariable(mew));

      Event initEvent = new Event(eventType.ARRIVAL, initProcess);
      eventQueue.add(initEvent);

      CPUIdle = true;
   }
}

//Stops after 10,000 simulations before switching to a new state.