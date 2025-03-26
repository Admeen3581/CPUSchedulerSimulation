import java.util.Comparator;

/**
 * @author Adam Long
 */

public class Process
{
   private int ID;
   private double arrivalTime; //seconds
   private double serviceTime; //seconds
   private double remainingServiceTime; //seconds


   public Process(int ID, double arrivalTime, double serviceTime)
   {
      this.ID = ID;
      this.arrivalTime = arrivalTime;
      this.serviceTime = serviceTime;
      this.remainingServiceTime = 0;
   }

   public double getRemainingServiceTime()
   {
      return remainingServiceTime;
   }

   public void setRemainingServiceTime(double remainingServiceTime)
   {
      this.remainingServiceTime = remainingServiceTime;
   }

   public int getID()
   {
      return ID;
   }

   public double getArrivalTime()
   {
      return arrivalTime;
   }

   public double getServiceTime()
   {
      return serviceTime;
   }

   @Override
   public String toString()
   {
      return "Process #"+ this.ID +" {" +
            "arrivalTime= " + this.arrivalTime +
            ", serviceTime= " + this.serviceTime +
            '}';
   }
}

class SortProcess implements Comparator<Process>
{
   public int compare(Process p1, Process p2)
   {
      if(p1.getArrivalTime() == p2.getArrivalTime())
      {
         return Double.compare(p1.getServiceTime(), p2.getServiceTime());
      }
      else
      {
         return Double.compare(p1.getArrivalTime(), p2.getArrivalTime());
      }
   }
}