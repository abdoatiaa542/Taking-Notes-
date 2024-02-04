
package takingnotes;
import java.io.*;
import java.util.*;
public class FileManager {
    private static File file;
    private static Map<String , String> users;

    public FileManager(String path) {
        file = new File(path+"AppData\\");
        users = new HashMap<>();
        getUsers();
        
    }
    
    private void getUsers() 
    {
        File tempFile  = new File(FileManager.file + "//users.txt");
        if(!FileManager.file.exists() || !tempFile.exists())
        {
             FileManager.file.mkdirs();
             createFile(tempFile.pathSeparator);
        }
        else{
            try {
                //Scanner read = new Scanner(new FileReader(file));
                FileReader reader = new FileReader(tempFile);
                Scanner read = new Scanner(reader);
                String userName = null , password = null;
                while(read.hasNext())
                {
                    String current = read.nextLine();
                    if(current.contains("Username:"))
                    userName = current.substring(9);
                    else if(current.contains("Password:"))
                    {
                      password = current.substring(9);   
                      FileManager.users.put(userName.toLowerCase(), password);
                    }
                }
                    
            } catch (Exception e) {
                System.out.println(e);
            }
            
        }
    }
    public static void createFile(String path)
    {
        File file = new File(path);
        try {
           file.createNewFile();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static boolean FindUser(String userName)
    {
        return FileManager.users.containsKey(userName);
    }
    
    public static String getPassword(String userName)
    {
        return FileManager.users.get(userName);
    }
    
    public static void addUser(User user)
    {
        File tempFile  = new File(file + "\\users.txt");
        File dirFile  = new File(file + "\\" + user.getUserName());
        try {
            FileWriter writer = new FileWriter(tempFile , true);
            PrintWriter pw = new PrintWriter(writer);
            pw.append("Username:" + user.getUserName() + "\n");
            pw.append("Password:" + user.getPassword()+ "\n");
            pw.close();
            writer.close();
            dirFile.mkdir();
            users.put(user.getUserName().toLowerCase(),user.getPassword());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Map<String , String> readNotesFile(String path)
    {
    	Map<String , String> Notes = new HashMap<>(); 
    	File file =new File(path);
        try {
            if(!file.exists())
            {
                file.createNewFile();
                return Notes;
            }
               
            FileReader fr = new FileReader(path);
            Scanner reader = new Scanner(fr);
            String noteName = "" , password = "";
            while(reader.hasNext())
            {
            	String current = reader.nextLine();
                if(current.contains("Notename:"))
                	noteName = current.substring(9);
                else if(current.contains("Password:"))
                {
                	password = current.substring(9);   
                	Notes.put(noteName, password);
                }
            }
            reader.close();
            fr.close();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
        return Notes;
       
    }
    
    public static String readFile(String filePath)
    {
        try {
                FileReader fr = new FileReader(filePath);
                Scanner reader = new Scanner(fr);
                String body = "";
                while(reader.hasNext())
                    body += reader.nextLine() + "\n";
                reader.close();
                fr.close();
                return body;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
    
    public static void addNoteToFile(String filePath , String body)
    {
        try {
            FileWriter fw = new FileWriter(new File(filePath));
            PrintWriter writer = new PrintWriter(fw);
            writer.flush();
            writer.write(body);
            writer.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void renameFile(String oldPath , String newPath) 
    {
        File oldFile = new File(oldPath);
        File newFile = new File(newPath);
        oldFile.renameTo(newFile);
    }

    public static void addNotesToDetailsFile(String filePath , Map<String , String> Notes , Note note)
    {
    	File tempFile  = new File(filePath);
        try {
            FileWriter writer = new FileWriter(tempFile , true);
            PrintWriter pw = new PrintWriter(writer);
            pw.append("Notename:" + note.getTitle() + "\n");
            pw.append("Password:" + note.getPassword()+ "\n");
            pw.close();
            writer.close();
            Notes.put(note.getTitle() , note.getPassword());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void editNotesFile(String filePath, String oldName , String newName)
    {
        File tempFile = new File(filePath);
        try {
            String data = readFile(filePath);
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
            String oldLine = "Notename:"+oldName;
            String newLine = "Notename:"+newName;
            String edited = data.replace(oldLine, newLine);
            pw.flush();
            pw.write(edited);
            pw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void deleteFromNotesFile(String filePath , String title)
    {
        File tempFile = new File(filePath);
        try {
            Scanner reader = new Scanner(new FileReader(tempFile));
                String body = "";
                while(reader.hasNext()){
                    String noteName = reader.nextLine().substring(9);
                    String password =  reader.nextLine().substring(9);
                    if(!noteName.equals(title))
                    {
                        String nameLine = "Notename:"+noteName;
                        String passLine = "Password:"+password;
                         body += nameLine + "\n" + passLine + "\n";
                    }
                       
                }
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));    
            pw.flush();
            pw.write(body);
            pw.close();
        } catch (IOException ex) {  
            System.out.println(ex.getMessage());
        }
    }
    
    public static boolean deleteFile(String filePath)
    {
        File tempFile = new File(filePath);
        return tempFile.delete();
    }
}
   
