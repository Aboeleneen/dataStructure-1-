package eg.edu.alexu.csd.datastructure.mailServer;

import eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48.SingleLinkedList;

public class IMail {
	 public String sender;
	   public SingleLinkedList receivers= new SingleLinkedList() ;
	   public String message ;
	   public SingleLinkedList attachments= new SingleLinkedList();
	   public String subject;
	   public String priority;
}
