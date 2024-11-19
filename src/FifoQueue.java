import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class FifoQueue<E> extends AbstractQueue<E> {
    private LinkedList<E> list = new LinkedList<E>();

    private Node<E> last;

    public FifoQueue() {
        last = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new QueueIterator<E>();
    }

    @Override
    public int size() {
        if (last == null) {
            return 0;
        }
        int size = 1;
        Node<E> current = last.next;
        while (current != last) {
            size++;
            current = current.next;
        }
        return size;
    }

    @Override
    public boolean offer(E e) {
        Node<E> newNode = new Node<E>(e);
        if (last == null) {
            newNode.next = newNode;
            last = newNode;
        } else {
            newNode.next = last.next;
            last.next = newNode;
            last = newNode;
        }
        list.offer(e);
        return true;
    }

    @Override
    public E poll() {
        if (last == null) {
            return null;
        }
        Node<E> first = last.next;
        if (first == last) {
            last = null;
        } else {
            last.next = first.next;
        }
        list.poll();
        return first.element;
    }

    @Override
    public E peek() {
        return last == null ? null : last.next.element;
    }

    public void append(FifoQueue<E> q) {
        if(this == q) {
            throw new IllegalArgumentException();
        }
        if(q.last == null) {
            return;
        }
        if(last == null) {
            last = q.last;
        }
        else {
            Node<E> first = q.last.next;
            q.last.next = last.next;
            last.next = first;
            last = q.last;
        }
        q.last = null;
    }


    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    private class QueueIterator<E> implements Iterator<E> {
        private Node<E> current;

        private QueueIterator() {
            if(last != null) {
                this.current = (Node<E>) last.next;
            }
        }

        @Override
        public boolean hasNext() {
            if(last == null) {
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;

            return element;
        }
    }


}

