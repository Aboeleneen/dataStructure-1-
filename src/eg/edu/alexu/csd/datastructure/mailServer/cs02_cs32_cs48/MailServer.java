package eg.edu.alexu.csd.datastructure.mailServer.cs02_cs32_cs48;


import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

import eg.edu.alexu.csd.datastructure.linkedList.cs21_cs48.SingleLinkedList;

import eg.edu.alexu.csd.datastructure.mailServer.IApp;
import eg.edu.alexu.csd.datastructure.mailServer.IContact;
import eg.edu.alexu.csd.datastructure.mailServer.IFilter;
import eg.edu.alexu.csd.datastructure.mailServer.IFolder;
import eg.edu.alexu.csd.datastructure.mailServer.IMail;
import eg.edu.alexu.csd.datastructure.mailServer.ISort;

public class MailServer implements IApp {
    //set mails.
    public SingleLinkedList emails = new SingleLinkedList();
    public boolean isPriority;
      /**
     *
     *sort the list.
     * @throws java.io.IOException
     */
    public void folders() throws IOException {
        File server = new File("C:\\server");
        if (!server.exists()) {
            server.mkdir();
        }
        File accounts = new File("C:\\server\\accounts");
        if (!accounts.exists()) {
            accounts.mkdir();
        }
        File users = new File("C:\\server\\users");
        if (!users.exists()) {
            users.mkdir();
        }
        File names = new File("C:\\server/accounts/name.txt");
        if (!names.exists()) {
            names.createNewFile();
        }
        File passwords = new File("C://server/accounts/pass.txt");
        if (!passwords.exists()) {
            passwords.createNewFile();
        }
    }
    public boolean isFound(File file, String user) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = input.readLine()) != null) {
            if (user.equals(line)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean signin(String email, String password) throws IOException {
        folders();
        BufferedReader input = new BufferedReader(new FileReader("C:\\server/"
                + "accounts/name.txt"));
        String line = null;
        String name = email;
        int i = 0;
        List<String> passwords = Files
                .readAllLines(Paths.get("C:\\server/accounts/pass.txt"),
                        Charset.forName("ISO-8859-1"));
        while ((line = input.readLine()) != null) {
            if (name.equals(line)) {
                String pass = password;
                return pass.equals(passwords.get(i));
            }
            i++;
        }
        return false;
    }
        /**
     *
     *sort the list.
     * @return 
     */
   
    @SuppressWarnings("unchecked")
    @Override
    public boolean signup(IContact contact) throws IOException {
        // TODO Auto-generated method stub
        folders();
        String name = contact.email;
        try (BufferedReader input = new BufferedReader(new FileReader("C:\\server/"
                + "accounts/name.txt"))) {
            String line = null;
            while ((line = input.readLine()) != null) {
                if (name.equals(line)) {
                    input.close();
                    return false;
                }
            }
        }
        String pass = contact.password;
        PrintWriter output = new PrintWriter(new FileWriter("C:\\server/"
                + "accounts/name.txt", true));
        output.printf("%s\r\n", name);
        output.close();
        PrintWriter output2 = new PrintWriter(new FileWriter("C:\\server/"
                + "accounts/pass.txt", true));
        output2.printf("%s\r\n", pass);
        output2.close();
        File f1 = new File("C:\\server/users/" + name);
        f1.mkdir();
        File f2 = new File("C:\\server/users/" + name + "/inbox");
        f2.mkdir();
        File indexfile1 = new File("C:\\server/users/" + name + "/inbox/" +
                "indexfile.txt");
        indexfile1.createNewFile();
        /* new update */
        JSONObject obj = new JSONObject();
        JSONArray email = new JSONArray();
        email.add("email number one");
        email.add("suject one");
        email.add(1);
        /*kemo1*/
        email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
        /*kemo1*/
        obj.put("email number one", email);
        try (FileWriter file = new FileWriter("C:\\server/users/" + name +
                "/inbox/" + "indexfile.txt")) {
            file.write(obj.toJSONString());
        }
        /* end of update one */
        File f3 = new File("C:\\server/users/" + name + "/sent");
        f3.mkdir();
        File indexfile2 = new File("C:\\server/users/" + name + "/sent/" +
                "indexfile.txt");
        indexfile2.createNewFile();
        /*update two */
        obj = new JSONObject();
        email = new JSONArray();
        email.add("email number one");
        email.add("suject one");
        email.add(1);
        /*kemo2*/
        email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
        /*kemo2*/
        obj.put("email number one", email);
        try (FileWriter file = new FileWriter("C:\\server/users/" + name +
                "/sent/" + "indexfile.txt")) {
            file.write(obj.toJSONString());
        }
        /* end of update two */
        File f4 = new File("C:\\server/users/" + name + "/trash");
        f4.mkdir();
        File indexfile3 = new File("C:\\server/users/" + name + "/trash/" +
                "indexfile.txt");
        indexfile3.createNewFile();
        /* update three */
        obj = new JSONObject();
        email = new JSONArray();
        email.add("email number one");
        email.add("suject one");
        email.add(1);
        /*kemo3*/
        email.add("sender");
        email.add("receviers");
        email.add("attach");
        email.add("attachments");
        /*kemo3*/
        obj.put("email number one", email);
        try (FileWriter file = new FileWriter("C:\\server/users/" + name +
                "/trash/" + "indexfile.txt")) {
            file.write(obj.toJSONString());
        }
        /* end of update three */
        return true;
    }

    @Override
    public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) 
            throws IOException, ParseException {
        // TODO Auto-generated method stub
       
    }

    @Override
    public IMail[] listEmails(int page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteEmails(ILinkedList mails) {
        // TODO Auto-generated method stub
        try {
            int num = mails.size();
            File names = new File("C:\\server/accounts/name.txt");
            Object[] fileArray = Files.readAllLines(names.toPath(),
                    Charset.forName("ISO-8859-1")).toArray();
            int num_user = fileArray.length;
            for (int i = 0; i < num; i++) {
                for (int j = 1; j < num_user; j++) {
                    File user_inbox = new File("C:\\server/users/" +
                            fileArray[j] + "/inbox/" + "indexfile.txt");
                    JSONParser parser_inbox = new JSONParser();
                  Object obj1 = parser_inbox.parse(new FileReader(user_inbox));
                    /*update eight */
                    JSONObject jsonObject1 = (JSONObject) obj1;
                    if (jsonObject1.containsKey(mails.get(i))) {
                        /* end of update eight */
                        IFolder dest = new IFolder();
                      dest.Path = "C:\\server/users/" + fileArray[j] + "/trash";
                        SingleLinkedList mail = new SingleLinkedList();
                        mail.add(mails.get(i));
                        moveEmails(mail, dest);
                        break;
                    }
                 File user_sent = new File("C:\\server/users/" + fileArray[j] +
                         "/sent/" + "indexfile.txt");
                    JSONParser parser_sent = new JSONParser();
                    Object obj2 = parser_sent.parse(new FileReader(user_sent));
                    /* update nine */
                    JSONObject jsonObject2 = (JSONObject) obj2;
                    if (jsonObject2.containsKey(mails.get(i))) {
                        /*end of update nine */
                        IFolder dest = new IFolder();
                    dest.Path = "C:\\server/users/" + fileArray[j] + "/trash";
                    SingleLinkedList mail = new SingleLinkedList();
                        mail.add(mails.get(i));
                        moveEmails(mail, dest);
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
        } catch (IOException | ParseException e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void moveEmails(ILinkedList mails, IFolder des) {
        // TODO Auto-generated method stub

        try {
            int num = mails.size();
            File names = new File("C:\\server/accounts/name.txt");
            Object[] fileArray = Files.readAllLines(names.toPath(),
                    Charset.forName("ISO-8859-1")).toArray();
            int num_user = fileArray.length;
            for (int i = 0; i < num; i++) {
                for (int j = 1; j < num_user; j++) {
                    File user_inbox = new File("C:\\server/users/" +
                            fileArray[j] + "/inbox/" + "indexfile.txt");
                    JSONParser parser_inbox = new JSONParser();
                   Object obj1 = parser_inbox.parse(new FileReader(user_inbox));
                    /* update five */
                    JSONObject jsonObject1 = (JSONObject) obj1;
                    if (jsonObject1.containsKey(mails.get(i))) {
                        File source = new File("C:\\server/users/" +
                                fileArray[j] + "/inbox/" + mails.get(i));
                        File dest = new File(des.Path + "/" + mails.get(i));
                        copyDirectory(source, dest);
                        // add new email to index file of dest folder
                        parser_inbox = new JSONParser();
         obj1 = parser_inbox.parse(new FileReader(des.Path + "/indexfile.txt"));
                        JSONObject jsonObject = (JSONObject) obj1;
                  JSONArray mails2 = (JSONArray) jsonObject1.get(mails.get(i));
                        jsonObject.put(mails.get(i), mails2);
         try (FileWriter file = new FileWriter(des.Path + "/indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        // delete email name from source folder
                        parser_inbox = new JSONParser();
                        obj1 = parser_inbox.parse(new FileReader("C:\\server/"
                     + "users/" + fileArray[j] + "/inbox/" + "indexfile.txt"));
                        jsonObject = (JSONObject) obj1;
                        jsonObject.remove(mails.get(i));
                        mails2.remove(mails.get(i));
                     try (FileWriter file = new FileWriter("C:\\server/users/" +
                             fileArray[j] + "/inbox/" + "indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        /*end of update five */
                        deleteDir(source);
                        break;
                    }
                    File user_sent = new File("C:\\server/users/" +
                            fileArray[j] + "/sent/" + "indexfile.txt");
                    JSONParser parser_sent = new JSONParser();
                    Object obj2 = parser_sent.parse(new FileReader(user_sent));
                    /* update six */
                    JSONObject jsonObject2 = (JSONObject) obj2;
                    if (jsonObject2.containsKey(mails.get(i))) {
                        File source = new File("C:\\server/users/" +
                                fileArray[j] + "/sent/" + mails.get(i));
                        File dest = new File(des.Path + "/" + mails.get(i));
                        copyDirectory(source, dest);
                        // add new email to index file of dest folder
                        parser_sent = new JSONParser();
                        obj1 = parser_sent.parse(new FileReader(des.Path +
                                "/indexfile.txt"));
                        JSONObject jsonObject = (JSONObject) obj1;
                        JSONArray mails2 = (JSONArray) jsonObject2.get
        (mails.get(i));
                        jsonObject.put(mails.get(i), mails2);
                        try (FileWriter file = new FileWriter(des.Path +
                                "/indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        // delete email name from source folder
                        parser_sent = new JSONParser();
                        obj1 = parser_sent.parse(new FileReader("C:\\server"
                     + "/users/" + fileArray[j] + "/sent/" + "indexfile.txt"));
                        jsonObject = (JSONObject) obj1;
                        jsonObject.remove(mails.get(i));
                  try (FileWriter file = new FileWriter("C:\\server/users/" +
                          fileArray[j] + "/sent/" + "indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        /* end of update six */
                        deleteDir(source);
                        break;
                    }
             File user_trash = new File("C:\\server/users/" + fileArray[j] +
                     "/trash/" + "indexfile.txt");
                    JSONParser parser_trash = new JSONParser();
                   Object obj3 = parser_trash.parse(new FileReader(user_trash));
                    /*update seven */
                    JSONObject jsonObject3 = (JSONObject) obj3;
                    if (jsonObject3.containsKey(mails.get(i))) {
                      File source = new File("C:\\server/users/" + fileArray[j]
                              + "/trash/" + mails.get(i));
                        File dest = new File(des.Path + "/" + mails.get(i));
                        copyDirectory(source, dest);
                        // add new email to index file of dest folder
                        parser_trash = new JSONParser();
        obj1 = parser_trash.parse(new FileReader(des.Path + "/indexfile.txt"));
                        JSONObject jsonObject = (JSONObject) obj1;
                  JSONArray mails2 = (JSONArray) jsonObject3.get(mails.get(i));
                        jsonObject.put(mails.get(i), mails2);
          try (FileWriter file = new FileWriter(des.Path + "/indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        // delete email name from source folder 
                        parser_trash = new JSONParser();
                        obj1 = parser_trash.parse(new FileReader("C:\\server/"
                     + "users/" + fileArray[j] + "/trash/" + "indexfile.txt"));
                        jsonObject = (JSONObject) obj1;
                        jsonObject.remove(mails.get(i));
                   try (FileWriter file = new FileWriter("C:\\server/users/" +
                           fileArray[j] + "/trash/" + "indexfile.txt")) {
                            file.write(jsonObject.toJSONString());
                        }
                        /* end of update seven */
                        deleteDir(source);
                        break;
                    }
                }
            }
        } catch (IOException | ParseException e) {
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean compose(IMail email) throws IOException, ParseException {
        // TODO Auto-generated method stub
        String message = email.message;
        String name = email.sender;
        boolean userFound = false;
        File sent = new File("C:\\server/users/" + name + "/sent");
        /* update four */
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("C:\\server/users/" + name +
                "/sent/" + "indexfile.txt"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray mails2 = new JSONArray();
        String[] messages = sent.list();
        int numMessage = messages.length + 1;
        File folder2 = new File("C:\\server/users/" + name + "/sent/" +
                "message" + numMessage + "_" + name);
        folder2.mkdirs();
        /*kemo*/
        File attachment = new File("C:\\server/users/" + name + "/sent/" +
                "message" + numMessage + "_" + name+"/attachment");
        attachment.mkdirs();
       
      
      for(int i = 0;i<email.attachments.size();i++ ){
         
          File SrcPath = new File((String)email.attachments.get(i));
          File DesPath = new File(
        		  "C:\\server/users/" + name + "/sent/" +
        	                "message" + numMessage + "_" + name+"/attachment/" + SrcPath.getName() );
          DesPath.createNewFile();
          Files.copy(SrcPath.toPath(),DesPath.toPath(),
                  StandardCopyOption.REPLACE_EXISTING);
      }
        /*kemo*/
        File mess = new File("C:\\server/users/" + name + "/sent/" + "message"
                + numMessage + "_" + name + "/" + "message" + numMessage + "_" +
                name + ".txt");
        mess.createNewFile();
        mails2.add("message" + numMessage + "_" + name);
        mails2.add(email.subject);
        mails2.add(email.priority);
        /*kemo4*/
        mails2.add(email.sender);
       
        
        for(int n=0;n<email.receivers.size();n++){
        	mails2.add(email.receivers.get(n));
        }
        mails2.add("attach");
        for(int n=0;n<email.attachments.size();n++){
        	mails2.add(email.attachments.get(n));
        }
        /*kemo4*/
        jsonObject.put("message" + numMessage + "_" + name, mails2);
        try (FileWriter file = new FileWriter("C:\\server/users/" + name +
                "/sent/" + "indexfile.txt")) {
            file.write(jsonObject.toJSONString());
        }
        try (BufferedWriter sentMess = new BufferedWriter(new FileWriter(mess))) {
            sentMess.write(message);
        }
        int numOfReceviers = email.receivers.size();
        String[] users = new String[numOfReceviers];
        for (int counter = 0; counter < numOfReceviers; counter++) {
            users[counter] = (String) email.receivers.get(counter);
        }
        File file = new File("C:\\server/accounts/name.txt");
        BufferedReader input = new BufferedReader(new FileReader(file));
        for (String user : users) {
            if (isFound(file, user)) {
                File f2 = new File("C:\\server/users/" + user + "/inbox");
                String[] inbox = f2.list();
                int numMessage2 = inbox.length + 1;
                File folder = new File("C:\\server/users/" + user + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + user);
                folder.mkdir();
                
                /*kemo*/
                File attachment2 = new File("C:\\server/users/" + user + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + user+"/attachment");
                attachment2.mkdirs();
                
                for(int i = 0;i<email.attachments.size();i++ ){
                    
                    File SrcPath = new File((String)email.attachments.get(i));
                    File DesPath = new File(
                  		  "C:\\server/users/" + user + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + user+"/attachment/" + SrcPath.getName() );
                    DesPath.createNewFile();
                    Files.copy(SrcPath.toPath(),DesPath.toPath(),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                
                /*kemo*/
                File mess2 = new File("C:\\server/users/" + user + "/inbox/" + "message" + numMessage2 + "_" + name + "_" + user + "/" + "message" + numMessage2 + "_" + name + "_" + user + ".txt");
                mess2.createNewFile();
                
                obj = parser.parse(new FileReader("C:\\server/users/" + user + "/inbox/" + "indexfile.txt"));
                jsonObject = (JSONObject) obj;
                mails2 = new JSONArray();
                mails2.add("message" + numMessage2 + "_" + name + "_" + user);
                mails2.add(email.subject);
                mails2.add(email.priority);
                /*kemo5*/
                mails2.add(email.sender);
               
                
                for(int n=0;n<email.receivers.size();n++){
                	mails2.add(email.receivers.get(n));
                }
                mails2.add("attach");
                for(int n=0;n<email.attachments.size();n++){
                	mails2.add(email.attachments.get(n));
                }
                /*kemo5*/
                jsonObject.put("message" + numMessage2 + "_" + name + "_" + user, mails2);
                try (final FileWriter file2 = new FileWriter("C:\\server/users/" + user + "/inbox/" + "indexfile.txt")) {
                    file2.write(jsonObject.toJSONString());
                }
                try (BufferedWriter sentMess2 = new BufferedWriter(new FileWriter(mess2))) {
                    sentMess2.write(message);
                }
                userFound = true;
            }
        }
        if (!userFound) {
            return false;
        } else {
            return true;
        }
    }
     /**
     *
     *sort the list.
     * @param dir
     * @return 
     */
    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String children1 : children) {
                boolean success = deleteDir(new File(dir, children1));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
        /**
     *
     *sort the list.
     * @param sourceLocation
     * @param targetLocation
     * @throws java.io.IOException
     */
    public void copyDirectory(File sourceLocation, File targetLocation)
            throws IOException {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }
            String[] children = sourceLocation.list();
            for (String children1 : children) {
                copyDirectory(new File(sourceLocation, children1), new File(targetLocation, children1));
            }
        } else {
            if (sourceLocation.exists()) {
                OutputStream out;
                try (InputStream in = new FileInputStream(sourceLocation)) {
                    out = new FileOutputStream(targetLocation);
                    // Copy the bits from instream to outstream
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                }
                out.close();
            }
        }
    }
        /**
     *
     *sort the list.
     */
    public boolean deactivate(String email) throws IOException {
        //Delete From Users Part
        File f = new File("C:\\server\\users\\" + email);
        deleteDir(f);
        //Delete From Accounts Part
        File users = new File("C:\\server\\accounts\\name.txt");
        String line;
        int myIndex;
        int i;
        try (BufferedReader reader = new BufferedReader(new FileReader(users))) {
        	SingleLinkedList s = new SingleLinkedList();
            line = reader.readLine();
            myIndex = 0;
            int y = 0;
            while (line != null) {
                if (!(line.equals(email))) {
                    s.add(line);
                    line = reader.readLine();
                    y++;
                    continue;
                }
                line = reader.readLine();
                myIndex = y;
            }   PrintWriter output = new PrintWriter(new FileWriter(users), true);
            i = 0;
            while (i < s.size()) {
                line = (String) s.get(i);
                i++;
                output.printf("%s\r\n", line);
        }
        }
        //Delete The Password
        File users1 = new File("C:\\server\\accounts\\pass.txt");
        BufferedReader reader1 = new BufferedReader(new FileReader(users1));
        SingleLinkedList s1 = new SingleLinkedList();
        String line1 = reader1.readLine();
        while (line1 != null) {
            s1.add(line1);
            line1 = reader1.readLine();
        }
        s1.remove(myIndex);
        @SuppressWarnings("resource")
        PrintWriter output1 = new PrintWriter(new FileWriter(users1), true);
        i = 0;
        while (i < s1.size()) {
            line = (String) s1.get(i);
            i++;
            output1.printf("%s\r\n", line);
        }
        return true;
    }
    
    public IMail getmail(String name) throws FileNotFoundException, IOException, ParseException{
    	 File names = new File("C:\\server/accounts/name.txt");
         Object[] fileArray = Files.readAllLines(names.toPath(),
                 Charset.forName("ISO-8859-1")).toArray();
         int num_user = fileArray.length;
         for (int j = 1; j < num_user; j++) {
        	 
        	 // find mail in inbox folder
             File user_inbox = new File("C:\\server/users/" +
                     fileArray[j] + "/inbox/" + "indexfile.txt");
             JSONParser parser_inbox = new JSONParser();
             Object obj1 = parser_inbox.parse(new FileReader(user_inbox));
             /*update eight */
             JSONObject jsonObject1 = (JSONObject) obj1;
             if (jsonObject1.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject1.get(name);
            	SingleLinkedList attachments = new SingleLinkedList();
            	SingleLinkedList receviers = new SingleLinkedList();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\inbox\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
            	 
             
             // find mail in sent folder 
             File user_sent = new File("C:\\server/users/" +
                     fileArray[j] + "/sent/" + "indexfile.txt");
             JSONParser parser_sent = new JSONParser();
             Object obj2 = parser_sent.parse(new FileReader(user_sent));
             /*update eight */
             JSONObject jsonObject2 = (JSONObject) obj2;
             if (jsonObject2.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject2.get(name);
            	SingleLinkedList attachments = new SingleLinkedList();
            	SingleLinkedList receviers = new SingleLinkedList();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\sent\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
             
             
             // find mail in trash folder 
             File user_trash = new File("C:\\server/users/" +
                     fileArray[j] + "/trash/" + "indexfile.txt");
             JSONParser parser_trash = new JSONParser();
             Object obj3 = parser_sent.parse(new FileReader(user_trash));
             /*update eight */
             JSONObject jsonObject3 = (JSONObject) obj3;
             if (jsonObject3.containsKey(name)) {
            	 IMail mail = new IMail();
            	JSONArray contain= (JSONArray) jsonObject3.get(name);
            	SingleLinkedList attachments = new SingleLinkedList();
            	SingleLinkedList receviers = new SingleLinkedList();
            	mail.subject=(String) contain.get(1);
            	mail.priority=(String) contain.get(2);
            	mail.sender=(String) contain.get(3);
            	int i=4;
            	
            	while(! contain.get(i).equals("attach")){
            		receviers.add(contain.get(i));
            		i++;
            	}
            	mail.receivers=receviers;
            	for(int n=i+1;n<contain.size();n++ ){
            		attachments.add(contain.get(n));
            	}
            	mail.attachments=attachments;
            	File message = new File("C:\\server\\users\\"+fileArray[j]+"\\trash\\"+name+"\\"+name+".txt");
            	String mess = Files.readAllLines(message.toPath(),
                        Charset.forName("ISO-8859-1")).toString();
            	mail.message=mess.toString();
            	return mail; 
             }
             
             }
         
         
		return null;
    }
    
   
}
