/**
 * @author Adam Long
 */


public class Main
{
   public static void main(String[] args)
   {
      System.out.println("Hello, World!");

      final double SERVICE_TIME = 1/0.04;

      Simulator sim = new Simulator(20.0, SERVICE_TIME);

      sim.runSimulation();
      System.out.println(sim.plotMetrics());
   }
}