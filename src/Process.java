/**
 * @author Adam Long
 */

public class Process
{
   private int ID;
   private int arrivalTime; //seconds
   private double serviceTime; //seconds

   public Process(int ID, int arrivalTime, double serviceTime)
   {
      this.ID = ID;
      this.arrivalTime = arrivalTime;
      this.serviceTime = serviceTime;
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