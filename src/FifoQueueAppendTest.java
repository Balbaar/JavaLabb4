import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class FifoQueueAppendTest {
    @Test
    public void testAppendTwoEmptyQueues() {
        FifoQueue<String> queue1 = new FifoQueue<>();
        FifoQueue<String> queue2 = new FifoQueue<>();
        queue1.append(queue2);
        assertEquals(0, queue1.size());
        assertEquals(0, queue2.size());
    }

    @Test
    public void testAppendEmptyQueueToNonEmptyQueue() {
        FifoQueue<String> queue1 = new FifoQueue<>();
        FifoQueue<String> queue2 = new FifoQueue<>();
        queue1.offer("A");
        queue1.append(queue2);
        assertEquals(1, queue1.size());
        assertEquals("A", queue1.peek());
        assertEquals(0, queue2.size());
    }

    @Test
    public void testAppendNonEmptyQueueToEmptyQueue() {
        FifoQueue<String> queue1 = new FifoQueue<>();
        FifoQueue<String> queue2 = new FifoQueue<>();
        queue2.offer("A");
        queue1.append(queue2);
        assertEquals(1, queue1.size());
        assertEquals("A", queue1.peek());
        assertEquals(0, queue2.size());
    }

    @Test
    public void testAppendTwoNonEmptyQueues() {
        FifoQueue<String> queue1 = new FifoQueue<>();
        FifoQueue<String> queue2 = new FifoQueue<>();
        queue1.offer("A");
        queue2.offer("B");
        queue1.append(queue2);
        assertEquals(2, queue1.size());
        assertEquals("A", queue1.poll());
        assertEquals("B", queue1.poll());
        assertEquals(0, queue2.size());
    }

    @Test
    public void testAppendQueueToItself() {
        FifoQueue<String> queue = new FifoQueue<>();
        queue.offer("A");
        assertThrows(IllegalArgumentException.class, () -> queue.append(queue));
    }
}
