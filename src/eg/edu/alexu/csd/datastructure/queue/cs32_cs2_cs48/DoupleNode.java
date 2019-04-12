/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eg.edu.alexu.csd.datastructure.queue.cs32_cs2_cs48;
/**
 *
 * @author Geek
 */
public class DoupleNode {
/**
 *the value of the node.
 */
    public Object value;
/**
 * pointer to the next node.
 */
    public DoupleNode next = null;
/**
 * pointer to the previous node.
 */
    public DoupleNode previous = null;
/**
* put n in this node.
@param n object
*/
    DoupleNode(final Object n) {
        this.value = n;
    }
}
