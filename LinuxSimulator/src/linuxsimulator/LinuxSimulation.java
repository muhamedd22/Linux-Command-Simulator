import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class LinuxSimulation {
	
	static Scanner in = new Scanner(System.in);
	static boolean flag = true;
	static void main0(Parser p,Terminal t) throws IOException
	{
 
                if(p.getCmd().equals("mkdir"))
					t.mkdir(p.args.get(0));
				else if(p.getCmd().equals("date"))
				{
					if(p.isOperatorExist)
					{
						if(p.operator.equals(">"))
							t.operatorOne(p.getCmd(),p.args.get(1));
						else if(p.operator.equals(">>"))
							t.operatorTwo(p.getCmd(),p.args.get(1));
					}
					else
						t.date();
				}
				else if(p.getCmd().equals("args"))
						t.args(p.args.get(0));
				else if(p.getCmd().equals("help"))
					t.help();
				else if(p.getCmd().equals("clear"))
					t.clear();
				else if(p.getCmd().equals("cp"))
				{
					if(p.args.size()==2)
						t.cp(p.args.get(0), p.args.get(1));
					else
						t.cp(p.args, p.args.get(p.args.size()-1));
				}
				
				else if(p.getCmd().equals("mv"))
				{
					if(p.args.size()==2)
						t.mv(p.args.get(0), p.args.get(1));
					else
						t.mv(p.args, p.args.get(p.args.size()-1));
				}
				
				else if(p.getCmd().equals("cd") && p.args.size() == 1 )
					t.cd( p.args.get(0));
				
				
				else if(p.getCmd().equals("cd") && p.args.size() == 0 )
					t.cd();
				
				
				else if(p.getCmd().equals("pwd"))
				{
					if(p.isOperatorExist)
					{
						if(p.operator.equals(">"))
							t.operatorOne(p.getCmd(),p.args.get(1));
						else if(p.operator.equals(">>"))
							t.operatorTwo(p.getCmd(),p.args.get(1));
					}
					else
						t.pwd();
				}
				
				
				else if(p.getCmd().equals("rm"))
					t.rm(p.args);
				
				
				else if(p.getCmd().equals("ls"))
				{
					if(p.isOperatorExist)
					{
						if(p.operator.equals(">"))
							t.operatorOne(p.getCmd(),p.args.get(1));
						else if(p.operator.equals(">>"))
							t.operatorTwo(p.getCmd(),p.args.get(1));
					}
					else
						t.ls();
				}
				
				else if(p.getCmd().equals("cat"))
				{
					if(p.isOperatorExist)
					{
						if(p.operator.equals(">"))
							t.operatorOne(p.getCmd(),p.args,p.args_2.get(p.args_2.size()-1));
						else if(p.operator.equals(">>"))
							t.operatorTwo(p.getCmd(),p.args,p.args_2.get(p.args_2.size()-1));
					}
					else
						t.cat(p.args);
				}
                                else if(p.getCmd().equals("more"))
                                    t.more(p.getArgument().get(0));
                                
                                
                                else if(p.getCmd().equals("rmdir"))
                                    t.rmdir(p.getArgument().get(0));
                                
				else if(p.getCmd().equals("exit"))
					{
						flag = false;
					}
                                
           
        }
	
	public static void main(String args[]) throws IOException
	{
		
		while(flag)
		{
			String str = in.nextLine();
			boolean check = true;
			Parser p = new Parser();
			Terminal t = new Terminal();
			
			if(p.isPipe(str))
			{
				t.PipeOperator(str);
				for(int z = 0 ; z<t.commands.size(); z++)
		        {
		           Parser po = new Parser();
		           Terminal to = new Terminal();
		           if(po.parse(t.commands.get(z)))
		           {
		        	   main0(po,to);
		           }
		        }
				check = false;
			}
			
			else if(p.parse(str) && check == true)
			{
				main0(p,t);
			}    
		}	
	}
}


