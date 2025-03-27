/**
 * @author Adam Long
 */

public class DistributionCalculations
{
   /**
    * Calculates factorial of number
    *
    * @param num Number to factorize
    * @return Factorial of 'num'
    */
   protected static int factorial(int num)
   {
      if (num == 0)
      {
         return 1;
      }
      return num * factorial(num - 1);
   }


   /**
    * Calculates the poisson distribution
    *
    * @param expectedValue  Lambda
    * @param requestedValue K
    * @return Expected probability of K given Poisson Distribution
    * @see <a href="https://en.wikipedia.org/wiki/Poisson_distribution">Poisson Distribution - Wiki</a>
    */
   public static double poissonEquation(int expectedValue, int requestedValue)
   {
      double result = Math.pow(expectedValue, requestedValue) * Math.exp(Math.negateExact(expectedValue)) / factorial(requestedValue);
      return Math.round(result * 1E5) / 1E5;
   }

   /**
    * Calculates a random variable based on a Poisson distribution
    *
    * @return random Poisson variable
    */
   public static int poissonVariable(int expectedValue)
   {
      int eventCounter = 0;
      double probability = poissonEquation(expectedValue, 0);
      double rand = Math.random();//uniformly distributed

      while (rand > probability)
      {
         eventCounter++;
         probability += poissonEquation(expectedValue, eventCounter);
      }

      return eventCounter;
   }

   /**
    * Calculates a random variable based on an Exponential distribution
    *
    * @param expectedValue Lambda or Mew
    * @return random Exponential variable
    * @see <a href="https://en.wikipedia.org/wiki/Exponential_distribution">Exponential Distribution - Wiki</a>
    */
   public static double exponentialVariable(double expectedValue)
   {
      return -Math.log(1 - Math.random()) / expectedValue;
   }
}
