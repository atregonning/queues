import java.util.Iterator;

/******************************************************************************
 * <p>Compilation  : javac Deque.java</p>
 * <p>Execution    : java Deque &lt; input.txt</p>
 * <p>Dependencies : StdIn.java,
 *                   StdOut.java,
 *                    java.util.Iterator
 * </p>
 *
 *  <p>Linked list implementation of a generic double-ended queue.</p>
 *  
 *  @author     Adrian Tregonning
 *  @version    1.0  9/30/15
 *
 *****************************************************************************/
public class Deque<Item> implements Iterable<Item> {
    
    private int N;              // Size of the queue
    private Node first;         // First item in the deque
    private Node last;          // Last item in the deque
    
    // Nested class for Nodes
    private class Node {
        Item item;
        Node next;
        Node prev;
    }
    
    /**
     * Constructs an empty deque.
     */
    public Deque() {
        N = 0;
        first = null;
        last = null;
    }
    
    /**
     * Returns an iterator over items in order from front to end.
     * 
     * @return  An iterator for the deque
     * @throws  java.lang.UnsupportedOperationException if remove() is attempted
     * @throws  java.util.NoSuchElementException if next() is called 
     *          on empty deque
     */
    public Iterator<Item> iterator() {
        return new ListForwardIterator();
    }
    
    // Iterator implementation
    private class ListForwardIterator implements Iterator<Item> {
        private Node current = first;
        
        public boolean hasNext() {
            return (current != null);
        }
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException("No more items");
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Unused iterator method");
        }   
    }
    
    /**
     * Check if the deque is empty.
     * 
     * @return  <tt>true</tt> if deque is empty;
     *          <tt>false</tt> if not
     */
    public boolean isEmpty() {
        return (N == 0);
    }
    
    /**
     * Returns size of the deque.
     * 
     * @return  The number of items on the deck
     */
    public int size() {
        return N;
    }
    
    /**
     * Add an item to the front of the deque.
     * 
     * @param   item Item to add
     * @throws  NullPointerExpection if <tt>item == null</tt>
     */
    public void addFirst(Item item)  {
        if (item == null) {
            throw new java.lang.NullPointerException("Trying to add null item.");
        }
        
        Node newNode = new Node();
        newNode.item = item;
        
        if (N == 0) {
            first = newNode;
            last = first;
        } else {
            Node oldFirst = first;
            first = newNode;
            oldFirst.prev = first;
            first.next = oldFirst;
        }
        N++;
    }
    
    /**
     * Add an item to the end of the deque.
     * 
     * @param   item Item to add
     * @throws  NullPointerExpection if <tt>item == null</tt>
     * */
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.NullPointerException("Trying to add null item.");
        }
        Node newNode = new Node();
        newNode.item = item;
        
        if (N == 0) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
        }
        N++;
    }
    
    /**
     * Remove and return the first item in the deque.
     * 
     * @return  The first Item on the deque
     * @throws  NoSuchElementException if attempting to remove an item from 
     *          empty deque
     */
    public Item removeFirst() {
        if (N == 0) {
            throw new java.util.NoSuchElementException("Attempting to remove"
                    + " from empty deque");
        }
        Item item = first.item;
        first = first.next;
        if (first != null) {
            first.prev = null;
        } else {
            last = null;
        }
        N--;
        return item;
    }
    
    /**
     * Remove and return the last item in the deque.
     * 
     * @return  The last Item on the deque
     * @throws  NoSuchElementException if attempting to remove an item from 
     *          empty deque
     */
    public Item removeLast() {
        if (N == 0) {
            throw new java.util.NoSuchElementException("Attempting to remove" +
                    "from empty deque");
        }
        Item item = last.item;
        if (last.prev != null) {
            last = last.prev;
            last.next = null;
        } else {
            first = null;
            last = null;
        }
        N--;
        return item;
    }

    /**
     * Test client.
     * 
     * @param args
     */
    public static void main(String[] args) {
    }
    
}
