package eg.edu.alexu.csd.datastructure.queue;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public interface IQueue {
/**
* Inserts an item at the queue front.
     * @param item
*/
public void enqueue(Object item);
/**
* Removes the object at the queue rear and returns it.
     * @return 
*/
public Object dequeue();
/**
* Tests if this queue is empty.
     * @return 
*/
public boolean isEmpty();
/**
* Returns the number of elements in the queue
     * @return 
*/
public int size();
}