import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class FifoQueueIteratorTest {

    @Test
    public void testIteratorReturnsIterator() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        assertEquals("abc", queue.iterator().next());
    }







    @Test
    public void testHasNextReturnsFalseIfEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        Iterator<String> iterator = queue.iterator();
        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testHasNextReturnsTrueIfNotEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        Iterator<String> iterator = queue.iterator();
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testNextThrowsExceptionIfEmpty() {
        FifoQueue<String> queue = new FifoQueue<String>();
        Iterator<String> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorReturnsNextElement() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        Iterator<String> iterator = queue.iterator();
        assertEquals("abc", iterator.next());
        assertEquals("def", iterator.next());
    }

    @Test
    public void testNextDoNotChangeList() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        Iterator<String> iterator = queue.iterator();
        iterator.next();
        assertEquals(2, queue.size());
    }

    @Test
    public void testTwoIteratorsDoNotInterfere() {
        FifoQueue<String> queue = new FifoQueue<String>();
        queue.offer("abc");
        queue.offer("def");
        Iterator<String> iterator1 = queue.iterator();
        Iterator<String> iterator2 = queue.iterator();
        iterator1.next();
        assertEquals("abc", iterator2.next());
    }
}
