import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int numberOfNodes;
    private Node first, last;
    
    private class Node
    {
        Item item;
        Node next;
    }
    // construct an empty deque
    public Deque() 
    {
        numberOfNodes = 0;
        first = null; 
        last = null;
        
    }
    // is the deque empty?
    public boolean isEmpty()
    { return first == null || last == null; } 
    
    // return the number of items on the deque
    public int size()
    { return numberOfNodes; }
    
    // add the item to the front
    public void addFirst(Item item)  // queue enqueue modified  
    {
        if (item == null)
            throw new IllegalArgumentException("Parameter cannot be null!");
         else
         { 
            Node oldfirst = first;
            first = new Node();
            first.item = item;
            first.next = oldfirst;
            numberOfNodes++;
            if (oldfirst == null) last = first;
         }
    }
    // add the item to the back
    public void addLast(Item item) // stack push
    {
        if (item == null)
            throw new IllegalArgumentException("Parameter cannot be null!");
         else
         { 
            if (!(first == null) && last == null) last = first;       
            Node oldlast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            
            if (isEmpty()) first = last;
            else 
              oldlast.next = last;
            numberOfNodes++;
         }
    }
    // remove and return the item from the front
    public Item removeFirst() // queue dequeue
    {  
        if (isEmpty()) throw new NoSuchElementException("empty list");
        
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        numberOfNodes--;
        if (numberOfNodes < 0) numberOfNodes = 0;
        return item;  
    }
    // remove and return the item from the back
    public Item removeLast() //  stack pop
    {
        if (isEmpty()) throw new NoSuchElementException("empty list");
        Node item = first;
        Item removed = last.item;
        if (!(item.next == null)) 
        {
            while (!(item.next.next == null))
                item = item.next;
            item.next = null;
            last = item;
        }
        else  // removing the last item in the list 
            {
             first = null;
             last = null;
            }
        numberOfNodes--;
        if (numberOfNodes < 0) numberOfNodes = 0;
        return removed;
    } 
    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() 
    { return new ListIterator(); }
    
        private class ListIterator implements Iterator<Item>
        {
            private Node current = first;
            public boolean hasNext() { return current != null; }
            public void remove() { throw new UnsupportedOperationException("Invalid operation."); } 
            public Item next()
            {
                if (current == null) throw new NoSuchElementException("No more items to return!");
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<Integer> dq = new Deque<Integer>();
        StdOut.println(dq);     
        dq.addFirst(2);
        dq.addLast(3);
        dq.addLast(4);
        dq.addFirst(1);
        StdOut.println(dq.removeLast());
        StdOut.println(dq.removeFirst());
        StdOut.println(dq.isEmpty());
        StdOut.println(dq.iterator());
        StdOut.print("iterator : ");
        for (Iterator<Integer> i = dq.iterator(); i.hasNext();) {
            StdOut.print(i.next() + " ");            
           }
        StdOut.println();
        StdOut.println("size: " + dq.size()); 
   }
}


