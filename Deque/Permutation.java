import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;

public class Permutation {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k > 0) 
        {
            RandomizedQueue<String> rq = new RandomizedQueue<String>();
            int i = 1;
            String item = "";
            while (!StdIn.isEmpty()) 
            {
                item = StdIn.readString();
                 if (i <= k) 
                {
                        rq.enqueue(item); // reading first k elements to the queue
                        
                }
                 else if (StdRandom.bernoulli(0.5))
                    {
                        rq.dequeue();
                        rq.enqueue(item); // switching a random queue element with a one after k items;
                    } 
                 i++;
            }
            for (Iterator<String> j = rq.iterator(); j.hasNext();) // printing out final list k
            {
                StdOut.println(j.next());
            }
            
        } else StdOut.println("empty");    
    }

}
