import java.util.HashMap;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class SJFScheduler extends AbstractScheduler {

  // TODO
    private LinkedList<Process> readyQueue = new LinkedList();
    HashMap<Process, Integer> burstRecord = new HashMap();
    Properties parameters;
    private int initialBurst;
    private double alphaBurst;
    @Override
    public void initialize(Properties parameters) {
        this.parameters = parameters;
        initialBurst = Integer.parseInt(parameters.getProperty("initialBurstEstimate"));
        alphaBurst = Double.parseDouble(parameters.getProperty("alphaBurstEstimate"));
    }
    
    private int calculate(Process process){
      double c = (alphaBurst * process.getRecentBurst()) + ((1 - alphaBurst) * burstRecord.get(process));
       return (int)Math.floor(c);
    }
    
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
    
    
    @Override
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    int time = initialBurst;
    if (burstRecord.containsKey(process)){
    time = calculate(process);
    }
    int i = 0;
    while(i < readyQueue.size() && time > calculate(readyQueue.get(i))){
        i++;
    }
    readyQueue.add(i,process);
    burstRecord.put(process, time);

  }
  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
     * @return 
   */
  @Override
  public Process schedule() {
   
    System.out.println("Scheduler selects process "+readyQueue.peek());
    return readyQueue.poll();
  }
}
