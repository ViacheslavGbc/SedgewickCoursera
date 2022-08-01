import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] s;
    private int n = 0; 
    
       
    // construct an empty randomized queue
    
    public RandomizedQueue() 
    {
        s = (Item[]) new Object[1];
    }
        
    // is the randomized queue empty?
    public boolean isEmpty()   
    { return n == 0; }
    
    // return the number of items on the randomized queue
    public int size()    
    { return n; }
    
    // add the item
    public void enqueue(Item item) 
    {
        if (item == null)
            throw new IllegalArgumentException("Parameter cannot be null!");
         else
         { 
             if (n == s.length) resize(2 * s.length);
             s[n++] = item;
         }   
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        else 
        {
            // shuffleArray();
            // StdRandom.permutation(n);
            Item item = s[--n];
            s[n] = null;
            if (n > 0 && n == s.length/4) resize(s.length/2);
            if (item == null) throw new NoSuchElementException("empty list"); // <==
            return item;
        }    
    } 
    
    // private void ResizingArrayStackOfStrings()
    // { s = (Item[]) new Object[1]; }
    
    private void resize(int capacity)
    {
    Item[] copy = (Item[]) new Object[capacity];
    for (int i = 0; i < n; i++)
    copy[i] = s[i];
    s = copy;
    }
    
    // return a random item (but do not remove it)
    public Item sample()    
    { 
        if (isEmpty()) throw new NoSuchElementException("empty list");
        else {
            // shuffleArray();  // <=== !!
            if (n == 1 && !(s[0] == null)) return s[0];            // <==
            Item item = s[StdRandom.uniform(0, n-1)]; 
            if (item == null && n == 0)                         // <=!!!
                throw new NoSuchElementException("empty list"); // <== ! <===
            else return item;
        }
    }
    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator()
    {   // shuffleArray();
        return new ReverseArrayIterator(); 
    }
    private class ReverseArrayIterator implements Iterator<Item>
    {
        private int i = n;
        public boolean hasNext() { return i > 0; }
        public void remove() { throw new UnsupportedOperationException("Invalid operation."); }
        public Item next() 
        { 
            if (!(hasNext()) && s[0] == null) throw new NoSuchElementException("No more items to return!"); // <== !!!  
            else 
            {
                return s[--i]; 
            }
        }
    }
    private void shuffleArray() 
    { StdRandom.shuffle(s); }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        
        StdOut.println(rq);     
        rq.enqueue(4);
        rq.enqueue(3);
        rq.enqueue(2);
        rq.enqueue(1);
        StdOut.println(rq.dequeue());
        StdOut.println(rq.dequeue());
        StdOut.println(rq.isEmpty());
        StdOut.println(rq.iterator());
        StdOut.print("iterator : ");
        for (Iterator<Integer> i = rq.iterator(); i.hasNext();) {
            StdOut.print(i.next() + " ");
           }
        StdOut.println();
        StdOut.println("sample: " + rq.sample());
        StdOut.println("size: " + rq.size()); 
        
    }
}
