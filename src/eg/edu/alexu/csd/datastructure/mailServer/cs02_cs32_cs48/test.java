package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.simple.parser.ParseException;

import eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48.SingleLinkedList;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;

public class test {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
         MailServer X = new MailServer();
         IContact user = new IContact();
         user.email="kemo3";
         user.password="kemo3";
         System.out.println(X.signup(user));
         IMail mail = new IMail();
        mail.sender="kemo2";
         mail.message="CHECK GET OBJECT";
         mail.subject="Check";
         mail.priority="2";
         SingleLinkedList attach = new SingleLinkedList();
         
         attach.add("C:\\server\\users\\abozaid\\trash\\1.pdf");
       
         SingleLinkedList rece = new SingleLinkedList();
         rece.add("kemo3");
         mail.attachments=attach;
         mail.receivers=rece;
         System.out.println(X.compose(mail));
         rece = new SingleLinkedList();
        // rece.add("message8_kemo2_kemo3");
       //  X.deleteEmails(rece);
      /*  mail= X.getmail("message2_kemo2");
        
        int n=mail.attachments.size();
        
        for(int i=0;i<n;i++){
        	System.out.println(mail.attachments.get(i));
        }
        System.out.println(mail.message);*/
        
         
    }

}
