import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Round Robin Scheduler
 * 
 * @version 2017
 */
public class RRScheduler extends AbstractScheduler {

  // TODO
    private Queue<Process> readyQueue = new LinkedList<Process>();

    private boolean usedFullTimeQuantum;
    Properties parameters;
    private int timeQuantum ;

    
    public void initialize(Properties parameters) {
        this.parameters = parameters;
        timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }
    
    
    public int getTimeQuantum(){
        return timeQuantum;
    }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    readyQueue.offer(process);
    System.out.println(process.getNextBurst());
    this.usedFullTimeQuantum = usedFullTimeQuantum;
  }

  /**
   * Removes the next process to be run from the ready queue 
   * and returns it. 
   * Returns null if there is no process to run.
   */
  public Process schedule() {

    System.out.println("Scheduler selects process "+readyQueue.peek());
    return readyQueue.poll();
  }
}
