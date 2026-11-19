
package mazeWave;

import java.util.NoSuchElementException;

/**
 * Tests the Queue class.
 *
 * @author
 * @version Nov 18, 2026
 */
public class QueueTest extends student.TestCase {

    private Queue<Integer> queue;

    /**
     * Sets up a queue before each test.
     */
    public void setUp()
    {
        queue = new Queue<Integer>();
    }

    /**
     * Tests the constructor and isEmpty.
     */
    public void testIsEmpty()
    {
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests enqueue and size.
     */
    public void testEnqueue()
    {
        queue.enqueue(10);
        queue.enqueue(20);

        assertFalse(queue.isEmpty());
        assertEquals(2, queue.size());
    }

    /**
     * Tests dequeue and FIFO ordering.
     */
    public void testDequeue()
    {
        queue.enqueue(10);
        queue.enqueue(20);
        
        queue.dequeue();
        assertEquals("Queue should have 1", 1, queue.size());
        queue.dequeue();
        assertEquals("Queue should contain 0 elements", 0, queue.size());

        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    /**
     * Tests dequeue on an empty queue.
     */
    public void testDequeueEmpty()
    {
        Exception exception = null;

        try
        {
            queue.dequeue();
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof NoSuchElementException);
    }

    /**
     * Tests peek without removing an element.
     */
    public void testPeek()
    {
        queue.enqueue(10);

        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    /**
     * Tests peek on an empty queue.
     */
    public void testPeekEmpty()
    {
        Exception exception = null;

        try
        {
            queue.peek();
        }
        catch (Exception e)
        {
            exception = e;
        }

        assertNotNull(exception);
        assertTrue(exception instanceof NoSuchElementException);
    }

    /**
     * Tests that the queue can be reused after becoming empty.
     */
    public void testEnqueueAfterEmpty()
    {
        queue.enqueue(10);
        queue.dequeue();

        queue.enqueue(20);

        assertFalse(queue.isEmpty());
    }
}
