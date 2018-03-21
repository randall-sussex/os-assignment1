import java.util.LinkedList;
import java.util.Properties;
import java.util.Queue;

/**
 * Ideal Shortest Job First Scheduler
 * 
 * @version 2017
 */
public class IdealSJFScheduler extends AbstractScheduler {

  private LinkedList<Process> readyQueue = new LinkedList<Process>();

  /**
   * Adds a process to the ready queue.
   * usedFullTimeQuantum is true if process is being moved to ready
   * after having fully used its time quantum.
   */
  public void ready(Process process, boolean usedFullTimeQuantum) {

    // TODO
    int burstSize = process.getNextBurst();
    
    // if getnextburst does not return -1 will search through list until a point is found where "process" has the smallest burst time
    if (burstSize != -1){
        int i = 0;
        while(i < readyQueue.size() && burstSize > readyQueue.get(i).getNextBurst()){
            i++;
        }
        readyQueue.add(i,process);
    }
    for(int i = 0; i < readyQueue.size(); i++){
        System.out.println(readyQueue.get(i).getId() + " || " + readyQueue.get(i).getNextBurst());
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
