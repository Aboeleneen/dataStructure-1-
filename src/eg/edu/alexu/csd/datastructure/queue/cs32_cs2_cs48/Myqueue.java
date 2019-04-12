package eg.edu.alexu.csd.datastructure.queue.cs32_cs2_cs48;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
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
public class Myqueue implements IArrayBased, IQueue {
 /**
* size of the array.
*/
    int maxsize = 0;
     /**
* array for the queue.
*/
    Object[] arr = new Object[maxsize];
/**
* put the size of array.
@param n size of array
*/
    Myqueue(final int n) {
        maxsize = n;
        arr = new Object[maxsize];
    }
    /**
* pointer for the first element of the queue.
*/
    int f = 0;
    /**
* pointer for the last element of the queue.
*/
    int r = 0;
    /**
* size of the queue.
*/
    int size = 0;
    @Override
    public void enqueue(final Object item) {
        if (size == maxsize) {
            throw new RuntimeException("full queue");
        }
        arr[r] = item;
        r = (r + 1) % maxsize;
        size++;
    }
    @Override
    public Object dequeue() {
        if (size == 0) {
            throw new RuntimeException("empty queue");
        }
        Object x = arr[f];
        arr[f] = null;
        f = (f + 1) % maxsize;
        size--;
        return x;
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
