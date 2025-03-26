import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Adam Long
 */

public class Simulator
{
   private static final ArrayList<Process> processes = new ArrayList<Process>();

   enum eventType
   {
      ARRIVAL,
      DEPARTURE;
   }

   public static void runSimulator(int lambdaRate, double serviceRate)
   {
      int currTime;
      double requestedTime;

      for(int k=0; k<10000; k++)//10,000 process simulations
      {
         currTime = DistributionCalculations.poissonVariable(lambdaRate);
         requestedTime = DistributionCalculations.exponentialVariable(serviceRate);
         processes.add(new Process(k, currTime, requestedTime));
      }

      Collections.sort(processes, new SortProcess());

      System.out.println(processes);



//      while ( ! end_cond ) {
//         set clock to next event;
//         switch (event_type) {
//            case type_1:
//               type_1_handler(); break;
//            case type_2:
//               type_2_handler(); break;
//......
//         }
//         delete this event that has been handled;
//      }
//      time_elapsed = clock – old_clock;
//      old_clock = clock;
//      Get time elapsed
//      since last event
//      What to do when this
//      kind of event happens

   }
}

//Stops after 10,000 simulations before switching to a new state.