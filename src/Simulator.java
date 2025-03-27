import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Long
 */

public class Simulator
{
   //Sim Params
   private double lambda;
   private double mew;

   //Sim State
   private double currTime = 0;
   private boolean isCPUIdle = true;
   private int readyQueueCount = 0;
   private int totalProcesses = 0;
   private int currProcessID = 0;

   //Metrics
   private double totalCPUTime = 0;
   private double totalReadyQueueLength = 0;
   private double totalTurnaroundTime = 0;

   private ArrayList<Event> eventQueue = new ArrayList<>();

   public Simulator(double lambda, double mew)
   {
      this.lambda = lambda;
      this.mew = mew;
   }


   public void runSimulation()
   {
      initSimulation();

      while(totalProcesses < 10000)
      {
         Event currEvent = eventQueue.remove(0);

         double timeElapsed = currEvent.getServeTime() - currTime;
         if(!isCPUIdle)
         {
            totalCPUTime += timeElapsed;
         }
         totalReadyQueueLength += readyQueueCount;

         currTime = currEvent.getServeTime();

         if(currEvent.getType() == eventType.ARRIVAL)
         {
            handleArrival(currEvent);
         }
         else
         {
            handleDeparture(currEvent);
         }
      }
   }

   public Map<String, Double> retrieveMetrics()
   {
      double cpuUtil = totalCPUTime / currTime;
      double avgReadyQueueLength = totalReadyQueueLength / currTime;
      double avgTurnaroundTime = totalTurnaroundTime / 10000;
      double totalThroughput = totalProcesses / currTime;

      Map<String, Double> map = new HashMap<>();

      map.put("CPUUtil", cpuUtil);
      map.put("AvgReadyQueueLength", avgReadyQueueLength);
      map.put("AvgTurnaroundTime", avgTurnaroundTime);
      map.put("TotalThroughput", totalThroughput);

      return map;
   }

   private void handleArrival(Event event)
   {
      Process currProcess = event.getProcess();

      //Next Event (Arrival)
      double nextArrTime = currTime + DistributionCalculations.exponentialVariable(lambda);
      double nextServiceTime = DistributionCalculations.exponentialVariable(mew);

      Process nextProcess = new Process(++currProcessID, nextArrTime, nextServiceTime);

      Event nextEvent = new Event(eventType.ARRIVAL, nextProcess, nextArrTime);
      eventQueue.add(nextEvent);

      //Curr Event (Departure)
      if(!isCPUIdle)
      {
         readyQueueCount++;
      }
      else
      {
         isCPUIdle = false;
         currProcess.setStartTime(currTime);

         double departTime = currTime + currProcess.getServiceTime();
         Event departEvent = new Event(eventType.DEPARTURE, currProcess, departTime);
         eventQueue.add(departEvent);
      }
   }

   private void handleDeparture(Event event)
   {
      Process currProcess = event.getProcess();

      //Update Stats
      currProcess.setEndTime(currTime);
      totalTurnaroundTime += currProcess.getEndTime() - currProcess.getArrivalTime();
      totalProcesses++;

      //Depart Event
      if(readyQueueCount == 0)
      {
         isCPUIdle = true;
      }
      else
      {
         readyQueueCount--;

         double departTime = currTime + currProcess.getServiceTime();
         Event departEvent = new Event(eventType.DEPARTURE, currProcess, departTime);
         eventQueue.add(departEvent);
      }
   }

   private void initSimulation()
   {
      double firstArrTime = DistributionCalculations.exponentialVariable(lambda);
      double firstServiceTime = DistributionCalculations.exponentialVariable(mew);

      Process firstProcess = new Process(++currProcessID, firstArrTime,firstServiceTime);

      Event firstEvent = new Event(eventType.ARRIVAL, firstProcess, firstServiceTime);
      eventQueue.add(firstEvent);
      //moarh
   }
}

//Stops after 10,000 simulations before switching to a new state.