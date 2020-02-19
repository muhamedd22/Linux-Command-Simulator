import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	
	List<String> args = new ArrayList<String>();
	String cmd;
	public boolean isOperatorExist = false;
	public String operator = "";
	public List<String> args_2 = new ArrayList<String>();
	
	boolean isCorrectPath(String path)
	{
		File f = new File(path);
		if(f.isDirectory() || f.isFile())
			return true;
		return false;
	}
	
        public boolean isPipe(String input)
        {
            for(int i = 0 ; i<input.length() ; i++)
            {
                if(input.charAt(i)=='|')
                    return true;
            }
            return false;
        }
        
	public boolean parse(String input)
	{	
		String args3[] = input.split(" ");
		
		for(int i = 0 ; i< args3.length ; i++)
		{
			args_2.add(args3[i]);
		}
		int SIZE = args_2.size();
		String cmd_2 = args_2.get(0);
		
		if(cmd_2.equals("cp"))
		{
			if(SIZE == 3)
			{
					cmd = cmd_2;
					if(isCorrectPath(args_2.get(1)) && isCorrectPath(args_2.get(2)))
					{
						args.add(args_2.get(1));
						args.add(args_2.get(2));
						return true;
					}
					else
					{
						System.out.println("This Path does not exist");
						return false;
					}
			}
			else
			{
				for(int i = 1 ; i<SIZE ; i++)
				{
					File f = new File(args_2.get(i));
					if(i==SIZE-1)
					{
						if(!f.isDirectory())
						{
							System.out.print("Error the last argument must be directory\n");
							return false;
						}
					}
					else
					{
						if(!f.isFile())
						{
							System.out.print("This Path  To File does not exist\n");
							return false;
						}
					}
				}
				cmd = cmd_2;
				for(int i = 0 ; i<SIZE-1 ; i++)
				{
					args.add(args_2.get(i+1));
				}
				return true;
			}
		}
		
		
		else if (cmd_2.equals("mv"))
		{
			if(SIZE == 3)
			{
					cmd = cmd_2;
					if(isCorrectPath(args_2.get(1)) && isCorrectPath(args_2.get(2)))
					{
						args.add(args_2.get(1));
						args.add(args_2.get(2));
						return true;
					}
					else
					{
						System.out.println("This Path does not exist");
						return false;
					}
			}
			else
			{
				for(int i = 1 ; i<SIZE ; i++)
				{
					File f = new File(args_2.get(i));
					if(i==SIZE-1)
					{
						if(!f.isDirectory())
						{
							System.out.print("Error the last argument must be directory\n");
							return false;
						}
					}
					else
					{
						if(!f.isFile())
						{
							System.out.print("This Path  To File does not exist\n");
							return false;
						}
					}
				}
				cmd = cmd_2;
				for(int i = 0 ; i<SIZE-1 ; i++)
				{
					args.add(args_2.get(i+1));
				}
				return true;
			}
		}
		
		else if(cmd_2.equals("mkdir"))
		{
			if(SIZE == 2)
			{
				cmd = cmd_2;
				for(int i = 0 ; i<args_2.get(1).length() ; i++)
				{
					if(args_2.get(1).charAt(i)=='.')
					{
						System.out.print("you enter a file not directory\n");
						return false;
					}
				}				
				args.add(args_2.get(1));
				return true;
			}
			System.out.print("mkdir requires 1 arguments\n");
			return false;
		}
		
		else if (cmd_2.equals("rm"))
		{
				
				for(int i = 1 ; i < args_2.size() ; i++)
				{
					File f = new File(args_2.get(i));
					if(!f.isFile())
					{	
						System.out.println("This Path does not exist");
						return false;
					}
				}
				cmd = cmd_2;
				for(int i = 1 ; i < args_2.size() ; i++)
				{
					args.add(args_2.get(i));
				}
				return true;
		}
		
		else if(cmd_2.equals("cd"))
		{
			if(SIZE == 2 || SIZE == 1)
			{
				cmd = cmd_2;
				if(args_2.size() == 2)
				{
					if(this.isCorrectPath(args_2.get(1)))
					{
						args.add(args_2.get(1));
						return true;
					}
					else
					{
						System.out.println("This Path does not exist");
						return false;
					}
				}
				return true;
			}
			System.out.print("cd requires 1 arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("rmdir"))
		{
			if(SIZE == 2)
			{
				cmd = cmd_2;
				if(this.isCorrectPath(args_2.get(1)))
				{
					args.add(args_2.get(1));
					return true;
				}
				else
				{
					System.out.println("This Path does not exist");
					return false;
				}
			}
			System.out.print("rmdir requires 1 arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("pwd"))
		{
			if(SIZE == 1 || SIZE == 3)
			{
				cmd = cmd_2;
				if(SIZE==3)
				{
					isOperatorExist = true;
					args.add(args_2.get(1));
					args.add(args_2.get(2));
					if(args.get(0).equals(">"))
					{
						operator = ">";
						return true;
					}
					else if(args.get(0).equals(">>"))
					{
						operator = ">>";
						return true;
					}
					else
						return false;
				}
				else
					return true;
			}
			System.out.print("pwd has no arguments\n");
			return false;
		}
		
		else if(cmd_2.equals("ls"))
		{
			if(SIZE == 1 || SIZE == 3)
			{
				cmd = cmd_2;
				if(SIZE==3)
				{
					isOperatorExist = true;
					args.add(args_2.get(1));
					args.add(args_2.get(2));
					if(args.get(0).equals(">"))
					{
						operator = ">";
						return true;
					}
					else if(args.get(0).equals(">>"))
					{
						operator = ">>";
						return true;
					}
					else
						return false;
					}
					else
						return true;
			}
			System.out.print("ls has no arguments\n");
			return false;
		}
		
		else if(cmd_2.equals("clear"))
		{
			if(SIZE == 1)
			{
				cmd = cmd_2;
				return true;
			}
			System.out.print("clear has no arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("date"))
		{

			if(SIZE == 1 || SIZE == 3)
			{
				cmd = cmd_2;
				if(SIZE==3)
				{
					isOperatorExist = true;
					args.add(args_2.get(1));
					args.add(args_2.get(2));
					if(args.get(0).equals(">"))
					{
						operator = ">";
						return true;
					}
					else if(args.get(0).equals(">>"))
					{
						operator = ">>";
						return true;
					}
					else
						return false;
				}
				else
					return true;
			}
			System.out.print("date has no arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("help"))
		{
			if(SIZE == 1)
			{
				cmd = cmd_2;
				return true;
			}
			System.out.print("help has no arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("exit"))
		{
			if(SIZE == 1)
			{
				cmd = cmd_2;
				return true;
			}
			System.out.print("exit has no arguments\n");
			return false;
		}
		
		
		else if(cmd_2.equals("more"))
		{
			if(SIZE == 2)
			{
				cmd = cmd_2;
				if(isCorrectPath(args_2.get(1)))
				{
					args.add(args_2.get(1));
					return true;
				}
				System.out.print("This is not correct path\n");
			}
			System.out.print("more has no arguments\n");
			return false;
		}
		
		else if(cmd_2.equals("args"))
		{
			if(SIZE == 2)
			{
				cmd = cmd_2;
				args.add(args_2.get(1));
				return true;
			}
			System.out.print("args must has 1 arguments\n");
			return false;
		}
		
		else if(cmd_2.equals("cat"))
		{
			if(SIZE >= 2)
			{
				cmd = cmd_2;
				for(int i = 0 ; i <args_2.size() ; i++)
				{
					if(args_2.get(i).equals(">") || args_2.get(i).equals(">>"))
					{
						isOperatorExist = true;
						if(args_2.get(i).equals(">"))
						{
							operator = ">";				
						}
						else
						{
							operator = ">>";
						}	
					}
				}
				if(isOperatorExist)
					SIZE-=2;
				for(int i = 1 ; i<SIZE ; i++)
				{
					if(!isCorrectPath(args_2.get(i)))
					{

						System.out.print("This is not correct path\n");
						return false;
					}
					args.add(args_2.get(i));
				}
				return true;
			}
			else
			{
				System.out.print("cat must have atleast 1 arguments\n");
				return false;
			}
		}
		
		System.out.print("wrong command\n");
		return false;
	}
	
	public String getCmd()
	{
		return cmd;
	}
	
	public List<String> getArgument()
	{
		return args;
	}
}
