import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

 
public class Terminal {
	public List<String> commands = new ArrayList<String>();
	public static String current = System.getProperty("user.dir");
	public String appendCurrent(String d)
	{
		for(int i = 0 ; i < d.length() ; i++)
		{
			if(d.charAt(i) == ':')
				return d;
		}
		d = current + "/" + d;
		return d;
	}
	
	public void cd(String newDir)
	{
		current = newDir;	
	}
	
	public void cd()
	{
		current = System.getProperty("user.dir");
	}
	
	
	public void cp(String sourcePath, String destinationPath) throws IOException
	{
		sourcePath = appendCurrent(sourcePath);
		destinationPath = appendCurrent(destinationPath);
		boolean check = true;
		String fileName="";
		for(int i = destinationPath.length()-1 ; destinationPath.charAt(i)!='/' ; i--)
		{
			if(destinationPath.charAt(i) == '.')
			{
				check = false;
				break;
			}
		}
		if(check == true)
		{
			for(int i = sourcePath.length()-1 ; sourcePath.charAt(i)!='/' ; i--)
			{
				fileName = sourcePath.charAt(i) + fileName;
			}
			destinationPath = destinationPath+"/"+fileName;
		}
		if(true)
		{
			File file = new File(sourcePath);
			Scanner scan = new Scanner(file);
			String content = "";
			while(scan.hasNextLine())
			{
				content = content.concat(scan.nextLine()+'\n');
			}
			scan.close();
			FileWriter out = new FileWriter(destinationPath);
			out.write(content);
			out.close();
		}
	}
	
	public void cp(List<String> sourcePath, String destinationPath) throws IOException
	{
		for(int i = 0 ; i<sourcePath.size()-1 ; i++)
		{
			cp(sourcePath.get(i),destinationPath);
		}
	}
	
	public void mv(String sourcePath, String destinationPath) throws IOException
	{
		cp(sourcePath,destinationPath);
		File f = new File(sourcePath);
		f.delete();
	}
	
	public void mv(List<String> sourcePath, String destinationPath) throws IOException
	{
		for(int i = 0 ; i<sourcePath.size()-1 ; i++)
		{
			mv(sourcePath.get(i),destinationPath);
		}
	}
	
	public void rm(List<String> sourcePath)
	{
		String sd="";

		for(int i = 0 ; i<sourcePath.size() ; i++)
		{
			sd = appendCurrent(sourcePath.get(i));
			File f = new File(sd);
			f.delete();		
		}
	}
	
	public void pwd()
	{
		System.out.println(current);
	}
	
	public void mkdir(String destinationPath) throws IOException
	{
			destinationPath = appendCurrent(destinationPath);
			File files = new File(destinationPath);
			files.mkdir();
	}
	
	public void cat(List<String> files_paths) throws FileNotFoundException, IOException
    {
           List<String>allData=new ArrayList<String>();
           for (int i = 0; i < files_paths.size(); i++)
           { 
               files_paths.set(i, appendCurrent(files_paths.get(i)));
           }
           
           for (int i = 0; i < files_paths.size(); i++) 
           {
        	   File file = new File(files_paths.get(i)); 
        	   BufferedReader br = new BufferedReader(new FileReader(file)); 
        	   String st;
        	   while ((st = br.readLine()) != null) 
                  allData.add(st);
           }
           System.out.println("Content of files : ");
           for (int i = 0; i < allData.size(); i++)       
               System.out.println(allData.get(i));
       }
	
	public void date()
	{
		DateFormat d = new SimpleDateFormat("dd-MM-YYYY 'at' HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		System.out.println(d.format(date));
	}
	
	public void clear()
	{
		for(int i = 0 ; i<15 ; i++)
			System.out.println();
	}
	
	public void args(String STR)
    {
        if(STR.equals("cp"))
            System.out.println("cp >> arg1: SourcePath, arg2: DestinationPath");
        else if(STR.equals("rm"))
            System.out.println("rm >> arg1: SourcePath");
        else if(STR.equals("mv"))
            System.out.println("mv >> arg1: SourcePath, arg2: DestinationPath");
        else if(STR.equals("rmdir"))
            System.out.println("rmdir >> arg1: SourcePath");
        else if(STR.equals("mkdir"))
            System.out.println("mkdir >> arg1: SourcePath, arg2: DestinationPath");
        else if(STR.equals("cat"))
            System.out.println("cat >> arg1: SourcePath, arg2: DestinationPath");
        else if(STR.equals("more"))
            System.out.println("more >> arg1: SourcePath");
        else if(STR.equals("cd"))
            System.out.println("cd >> No arguments");
        else if(STR.equals("clear"))
            System.out.println("clear >> No arguments");
        else if(STR.equals("ls"))
            System.out.println("ls >> No arguments");
        else if(STR.equals("pwd"))
            System.out.println("pwd >> No argumnets");
        else if(STR.equals("date"))
            System.out.println("date >> No arguments");
        else
        	System.out.println("wrong command");
    }
	
	
	public void help()
	{
		System.out.println("Commands : \ncp >> copy files and adding them to another directory >> arg1: SourcePath, arg2: DestinationPath");
		System.out.println("rm >> remove file >>arg1: SourcePath");
		System.out.println("mv>> cut files and adding them to another directory >> arg1: SourcePath, arg2: DestinationPath");
		System.out.println("rmdir>> removing directory >> arg1: SourcePath");
		System.out.println("mkdir>> creating directory >> arg1: SourcePath, arg2: DestinationPath");
		System.out.println("cat >> concatinate more than 1 file >> arg1: SourcePath, arg2: DestinationPath\nmore>> view more lines >> arg1: SourcePath");
		System.out.println("cd >> change the current working directory >>No arguments\nclear >> remove the appeared commands >>No arguments");
		System.out.println("ls >> list each given name and directory >> No arguments\npwd >> get the current working directory >> No argumnets");
		System.out.println("date >> get the current date and time >> No arguments");
	}
	
	public void ls()
	{
		File file = new File(current);
        File []files=file.listFiles();
        List<String> f = new ArrayList<String>();
        System.out.println("Files are:");
        if(files == null)
        	System.out.println("Folder is Empty");
        else 
        {
            for (int i = 0; i < files.length; i++) 
            { 
                 f.add((files[i].getName()));
            }
            Collections.sort(f);
           for (int i = 0; i < f.size(); i++) 
           { 
                System.out.println(f.get(i));
           }
        }
	}
	
	public void operatorOne (String command,String path) throws IOException
	{
		if(command.equals("pwd"))
		{
			FileWriter out = new FileWriter(path);
			out.write(current);
			out.close();
		}
		
		else if(command.equals("ls"))
		{
			FileWriter out = new FileWriter(path);
			File file = new File(current);
	        File []files=file.listFiles();
	        List<String> f = new ArrayList<String>();
	        if(files == null)
	        	return;
	        else 
	        {
	        	for (int i = 0; i < files.length; i++) 
	            { 
	                 f.add((files[i].getName()));
	            }
	            Collections.sort(f);
	           for (int i = 0; i < f.size(); i++) 
	           { 
	                out.write(f.get(i)+"\n");
	           }
	        }
			out.close();
		}
		
		else if(command.equals("date"))
		{
			FileWriter out = new FileWriter(path);
			DateFormat d = new SimpleDateFormat("dd-MM-YYYY 'at' HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			out.write(d.format(date));
			out.close();
		}
	}
	
	public void operatorOne (String command,List<String> files_paths,String path) throws IOException
	{
		 FileWriter out = new FileWriter(path);
		 List<String>allData=new ArrayList<String>();
         for (int i = 0; i < files_paths.size(); i++)
         { 
             files_paths.set(i, appendCurrent(files_paths.get(i)));
         }
         
         for (int i = 0; i < files_paths.size(); i++) 
         {
      	   File file = new File(files_paths.get(i)); 
      	   BufferedReader br = new BufferedReader(new FileReader(file)); 
      	   String st;
      	   while ((st = br.readLine()) != null) 
                allData.add(st);
         }
         
         for (int i = 0; i < allData.size(); i++)       
             out.write(allData.get(i)+"\n");
         out.close();
	}
	
	public void operatorTwo (String command,String path) throws IOException
	{
		if(command.equals("pwd"))
		{
			File file = new File(path);
			String content = "";
			if(file.isFile())
			{
				Scanner scan = new Scanner(file);
				
				while(scan.hasNextLine())
				{
					content = content.concat(scan.nextLine()+'\n');
				}
				content = content + current;
				scan.close();
			}
			else
			{
				System.out.print("the file doesnot exist\n");
				return;
			}
			FileWriter out = new FileWriter(path);
			out.write(content);
			out.close();
		}
		
		
		
		else if(command.equals("ls"))
		{
			File file = new File(path);
			File fil = new File(current);
			String content = "";
			if(file.isFile())
			{
				Scanner scan = new Scanner(file);
				while(scan.hasNextLine())
				{
					content = content.concat(scan.nextLine()+'\n');
				}
				scan.close();
			}
			else
			{
				System.out.print("the file doesnot exit\n");
				return;
			}
			FileWriter out = new FileWriter(path);
			out.write(content);
			File []files=fil.listFiles();
			List<String> f = new ArrayList<String>();
			if(files == null)
				return;
			else 
			{	
				for (int i = 0; i < files.length; i++) 
	            { 
	                 f.add((files[i].getName()));
	            }
	            Collections.sort(f);
	           for (int i = 0; i < f.size(); i++) 
	           { 
	                out.write(f.get(i)+"\n");
	           }
			}
			out.close();
		}
		
		else if(command.equals("date"))
		{
			File file = new File(path);
			String content = "";
			if(file.isFile())
			{
				Scanner scan = new Scanner(file);	
				while(scan.hasNextLine())
				{
					content = content.concat(scan.nextLine()+'\n');
				}
				scan.close();
			}
			else
			{
				System.out.print("the file doesnot exit\n");
				return;
			}
			FileWriter out = new FileWriter(path);
			DateFormat d = new SimpleDateFormat("dd-MM-YYYY 'at' HH:mm:ss");
			Date date = new Date(System.currentTimeMillis());
			out.write(content + d.format(date));
			out.close();
		}
		
		
	}
	
	
	public void operatorTwo(String command,List<String> files_paths,String path) throws IOException
	{
		File file = new File(path);
		List<String> content=new ArrayList<String>();
		if(file.isFile())
		{
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String st;
			while ((st = br.readLine()) != null) 
				content.add(st);
		}
		else
		{
			System.out.print("the file doesnot exit\n");
			return;
		}
		FileWriter out = new FileWriter(path);
		for(int i = 0 ; i<content.size() ; i++)
		{
			out.write(content.get(i)+"\n");
		}
		List<String>allData=new ArrayList<String>();
        for (int i = 0; i < files_paths.size(); i++)
        { 
            files_paths.set(i, appendCurrent(files_paths.get(i)));
        }
        for (int i = 0; i < files_paths.size(); i++) 
        {
     	   File fil = new File(files_paths.get(i)); 
     	   BufferedReader br = new BufferedReader(new FileReader(fil)); 
     	   String st;
     	   while ((st = br.readLine()) != null) 
               allData.add(st);
        }
        for (int i = 0; i < allData.size(); i++)       
            out.write(allData.get(i)+"\n");
        out.close();
	}
        public void more(String sourcePath)
        {
            
                Scanner in = new Scanner(System.in);
                sourcePath = appendCurrent(sourcePath);
                BufferedReader reader;
		try {
                        String continuity;
                        int i = 1;
			reader = new BufferedReader(new FileReader(sourcePath));
			String line = reader.readLine();
			System.out.println(line);
                        while (line != null) 
                        {
                                if(i%10==0)
                                {
                                    System.out.println("Enter \"Yes\" to continue & \"No\" to stop");
                                    continuity = in.next();
                                    if(continuity.equals("No"))
                                        break;
				}
                                else
                                {   // read next line
                                    line = reader.readLine();
                                    System.out.println(line);
                                }
                                i++;
                        }
			reader.close();
		} 
                catch (IOException e) 
                {
			e.printStackTrace();
		}
        }


        public void rmdir(String sourcePath)
        {
                sourcePath = appendCurrent(sourcePath);
                File file = new File(sourcePath); 
                if(file.isDirectory())
                {
                    String[] childFiles = file.list();
                    if(childFiles.length>0)
                    {   
                        System.out.println("Error, The Directroy isn't empty.");
                    }
                    else
                    {
                        file.delete();
                    }
                }
        }
        
        public void PipeOperator(String STR) throws IOException
        {
            int size = 0;
            String S = "";
            while(size<STR.length())
            {
                if(STR.charAt(size)!='|')
                {
                    S+=STR.charAt(size)+"";
                }
                else
                {
                    commands.add(S);
                    S="";
                }
                size++;
            }
            commands.add(S);   
        }
}
