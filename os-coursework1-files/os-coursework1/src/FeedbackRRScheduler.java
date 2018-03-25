import java.util.LinkedList;
import java.util.Properties;


/**
 * Feedback Round Robin Scheduler
 * 
 * @version 2017
 */
public class FeedbackRRScheduler extends AbstractScheduler {

    private LinkedList<Process> readyQueue = new LinkedList<Process>();

    Properties parameters;
    private int timeQuantum ;

    
    public void initialize(Properties parameters) {
        this.parameters = parameters;
        timeQuantum = Integer.parseInt(parameters.getProperty("timeQuantum"));
    }
    
    public boolean isPreemptive(){
        return true;
    }
  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    //if the process has used full time quantum the priority is decreased
    // (lower value = higher priority)
    if (usedFullTimeQuantum){
        process.setPriority(process.getPriority()+1);
    }
    int p = process.getPriority();
    int i = 0;
    
    while(i < readyQueue.size() && p > readyQueue.get(i).getPriority()){
        i++;
    }
    // if the while loop reaches the end of the queue without finding a lower priority than "process"
    // the new process is offered to the end of the list. otherwise "process" is added in the place where it is
    // lower than the next value
    if (i == readyQueue.size()){
        readyQueue.offer(process);
    } else{
        readyQueue.add(i,process);
    }
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
