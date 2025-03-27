/**
 * @author Adam Long
 */

enum eventType
{
   ARRIVAL,
   DEPARTURE;
}

public class Event implements Comparable<Event>
{
   private eventType type;
   private Process process;
   private double serveTime;

   public Event(eventType type, Process process, double serveTime)
   {
      this.type = type;
      this.process = process;
      this.serveTime = serveTime;
   }

   public eventType getType()
   {
      return type;
   }

   public Process getProcess()
   {
      return process;
   }

   public double getServeTime()
   {
      return serveTime;
   }

   @Override
   public int compareTo(Event event)
   {
      return Double.compare(this.serveTime, event.serveTime);
   }
}
