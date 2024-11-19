import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FifoQueueTest {

    @Test
    public void testSize() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertEquals(0, queue.size());
    }

    @Test
    public void testOffer() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertEquals(true, queue.offer("hejsan"));
    }

    @Test
    public void testOfferIncreaseSize() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("hejsan");
        assertEquals(1, queue.size());
    }

    @Test
    public void testPeekNotIncreaseSize() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.peek();
        assertEquals(1, queue.size());
    }

    @Test
    public void testPeekReturnNullIfEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertEquals(null, queue.peek());
    }

    @Test
    public void testPeekReturnElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        assertEquals("abc", queue.peek());
    }

    @Test
    public void testPollReturnNullIfEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        assertEquals(null, queue.poll());
    }

    @Test
    public void testPollReturnElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        assertEquals("abc", queue.poll());
    }

    @Test
    public void testPollRemovesFirstElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        queue.poll();
        assertEquals("def", queue.peek());
    }

    @Test
    public void testCanEmptyList() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        queue.poll();
        queue.poll();
        assertEquals(0, queue.size());
    }

    @Test
    public void testRetrieveCorrectElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        assertEquals(queue.poll() + queue.poll(), "abcdef");
    }
}
