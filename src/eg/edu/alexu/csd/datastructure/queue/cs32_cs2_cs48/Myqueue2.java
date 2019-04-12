package eg.edu.alexu.csd.datastructure.queue.cs32_cs2_cs48;

import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Geek
 */
public class Myqueue2 implements ILinkedBased, IQueue {
    /**
* head of the list.
*/
    public DoupleNode head = null;
     /**
* tail of the list.
*/
    public DoupleNode tail = null;
     /**
* size of the list.
*/
    int size;
    @Override
    public void enqueue(final Object item) {
        if (head == null) {
            DoupleNode x = new DoupleNode(item);
            x.next = head;
            head = x;
            tail = x;
        } else {
            DoupleNode c = tail;
            DoupleNode x = new DoupleNode(item);
            c.next = x;
            x.previous = c;
            tail = x;
        }
        size++;
    }
    @Override
    public Object dequeue() {
        if (size == 0) {
            throw new RuntimeException("empty queue");
        }
        DoupleNode i = head;
        Object c = head.value;
        head = i.next;
        size--;
        return c;
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }
    @Override
    public int size() {
        return size;
    }
}
