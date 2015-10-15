import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/******************************************************************************
 * <p>Compilation  : javac RandomizedQueue.java</p>
 * <p>Execution    : java RandomizedQueue &lt; input.txt</p>
 * <p>Dependencies : StdIn.java,
 *                   StdOut.java,
 *                   java.util.Iterator,
 *                   edu.princeton.cs.algs4.StdRandom;
 * </p>
 *
 *  <p>Resizing array implementation of a random queue.</p>
 *  
 *  @author     Adrian Tregonning
 *  @version    1.0  9/30/15
 *
 *****************************************************************************/
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] a;       // The queue
    private int N = 0;      // Size of the queue
    
    /** 
     * Construct an empty randomized queue.
     */
    public RandomizedQueue() {
        N = 0;
        a = (Item[]) new Object[1];
    }
    
    // Move stack to a new array of size max.
    private void resize(int max) {  
       Item[] temp = (Item[]) new Object[max];
       for (int i = 0; i < N; i++) {
          temp[i] = a[i];
       }
       a = temp;
    }
       
    private class RandomIterator implements Iterator<Item> { 
        private int count = 0;
        private int[] indices = new int[N]; // Array of random indices
               
        public RandomIterator() {
            for (int i = 0; i < N; i++) {
                indices[i] = i;
            }
            StdRandom.shuffle(indices);
        }           
        
        public boolean hasNext() {
            return (count != N);
        }

        public Item next() {
            if (count == N) {
                throw new java.util.NoSuchElementException("No items in queue");
            }
            return a[indices[count++]];
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException("Unused iterator method");           
        }
        
    }
    /**
     * Return an independent iterator over items in random order.
     * 
     * @return  A random iterator
     */
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }
    

    
    /**
     * Check if the queue is empty.
     * 
     * @return  <tt>true</tt> if queue is empty;
     *          <tt>false</tt> if not
     */
    public boolean isEmpty()  {
        return (N == 0);
    }
    
    /**
     * Returns the size of the queue.
     * 
     * @return  The number of items on the deck
     */
    public int size()  {
        return N;
    }
    
    /**
     * Add an item to the queue
     * 
     * @param   item Item to add.
     * @throws  NullPointerExpection if <tt>item == null</tt>
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Attempting to add null item");
        }
        if (N == a.length) {
            resize(2 * a.length);
        }
        a[N++] = item;
    }
    
    /**
     * Remove and return a random item.
     * 
     * @return  A random item from the queue
     * @throws  NoSuchElementException if attempting to remove an item from 
     *          empty queue
     */
    public Item dequeue() {
        if (N == 0) {
            throw new java.util.NoSuchElementException("Attempting to remove " +
                      "from empty queue");
        }
        int n = StdRandom.uniform(N);
        Item item = a[n];
        a[n] = a[N - 1];
        a[N - 1] = null;
        N--;
        if (N > 0 && N == a.length/4) {
            resize(a.length/2);
        }
        return item;
    }
    
    /** 
     * Return (but do not remove) a random item
     *  
     * @return An item from the queue.
     * @throws  NoSuchElementException if attempting to sample from 
     *          empty queue
     */
    public Item sample() {
        if (N == 0) {
            throw new java.util.NoSuchElementException("Attempting to sample " +
                      "empty queue");
        }
        return a[StdRandom.uniform(N)];
    }
       
}
