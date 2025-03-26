enum eventType
{
   ARRIVAL,
   DEPARTURE;
}

public class Event
{
   private eventType type;
   private double serveTime; //seconds
   private Event nextEvent;
   private Process process;

   public eventType getType()
   {
      return type;
   }

   public void setType(eventType type)
   {
      this.type = type;
   }

   public double getServeTime()
   {
      return serveTime;
   }

   public void setServeTime(double serveTime)
   {
      this.serveTime = serveTime;
   }

   public Event getNextEvent()
   {
      return nextEvent;
   }

   public void setNextEvent(Event nextEvent)
   {
      this.nextEvent = nextEvent;
   }

   public Process getProcess()
   {
      return process;
   }

   public void setProcess(Process process)
   {
      this.process = process;
   }
}
