import java.util.*;

public class CPUScheduler 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        Stack<Job> jobStack = new Stack<>();

        while (input.hasNextLine()) 
        {
            String line = input.nextLine();
            String[] tokens = line.split(" ");
            int priority = Integer.parseInt(tokens[0]);
            int startTime = Integer.parseInt(tokens[1]);
            int duration = Integer.parseInt(tokens[2]);
            Job job = new Job(priority, startTime, duration);
            jobStack.push(job);
        }

        int currentTime = 0;
        while (!jobStack.isEmpty()) 
        {
            Job job = jobStack.pop();
            int startTime = Math.max(currentTime, job.getStartTime());
            int endTime = startTime + job.getDuration();
            System.out.printf("\nJob %d (priority %d) started at time %d and ended at time %d\n",
                job.getId(), job.getPriority(), startTime, endTime);
            currentTime = endTime;
        }
    }
}

class Job 
{
    private static int nextId = 1;
    private int id;
    private int priority;
    private int startTime;
    private int duration;

    public Job(int priority, int startTime, int duration) 
    {
        this.id = nextId++;
        this.priority = priority;
        this.startTime = startTime;
        this.duration = duration;
    }

    public int getId() 
    {
        return id;
    }

    public int getPriority() 
    {
        return priority;
    }

    public int getStartTime() 
    {
        return startTime;
    }

    public int getDuration() 
    {
        return duration;
    }
}