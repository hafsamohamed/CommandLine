import java.util.ArrayList;

public class Parser {
    public ArrayList<String> args; // Will be filled by arguments extracted by parse method
    public ArrayList<String> bonus; // Will be filled by arguments extracted by parse method
    public static String cmd; // Will be filled by the command extracted by parse method
    public ArrayList<String> command = Main.CommandLine;
    public static String[] pipe_list;
    public  String[] arr;
    public String new_xx = "";
    public String input;


    public void split1() { //"xx    yyy    dd"
            input = Main.inp;

            if(input.contains(String.valueOf('"')))
            {
                bonus = new ArrayList<String>();
                for (int i=0; i<input.length();i++) {
                    if (!(input.charAt(i)==' ')) {
                        new_xx += input.charAt(i); // add to new_xx until you find a space
                    }
                    if ((input.charAt(i)==' ') || (i == input.length()-1))
                    {
                        bonus.add(new_xx);
                        new_xx = "";
                    }
                    if (input.charAt(i)=='"')
                    {
                        int x , y;
                        x = i;
                        y = input.indexOf('"', x+1); //index of second "  x==> start "
                        new_xx = input.substring(x+1,y); //new_xx==> word between ""
                        bonus.add(new_xx);
                        new_xx = "";
                        i = y+1; // start after end "
                    }

                }
                arr = new String[bonus.size()];
                for (int t=0;t<bonus.size();t++)
                {
                    //args.add(bonus.get(t));
                    arr[t] = bonus.get(t);
                }
                cmd = arr[0];
                getArguments();

               /* arr=input.split(String.valueOf('"'));

                for (int i=0;i<arr.length;i++)
                {
                    arr[i] = arr[i].trim();

                }
                getArguments();
                cmd = arr[0];
                System.out.print(cmd + "cmd\n" );
                */

            }
            else
            {
                arr = input.split(" ");
                getArguments();
                cmd = arr[0];
            }
    }
    public boolean parse() {
        int x = arr.length;
        if ((cmd.equals("pwd")) && (arr.length != 1)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if (cmd.equals("ls")){
            if ((arr.length!=3) && (arr.length!=1))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }
            if((arr.length==3) && (!input.contains(">") && !input.contains(">>")))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }

        }
        if ((cmd.equals("clear")) && (arr.length != 1)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("cp")) && (arr.length != 3)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("mv")) && (arr.length != 3)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("rm")) && (arr.length != 2)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
       /* if ((cmd.equals("date")) && (arr.length != 1)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }*/
        if (cmd.equals("date")){
            if ((arr.length!=3) && (arr.length!=1))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }
            if((arr.length==3) && (!input.contains(">") && !input.contains(">>")))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }

        }
        if ((cmd.equals("cd")) && (arr.length != 2)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("mkdir")) && (arr.length != 2)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("rmdir")) && (arr.length != 2)) {
            System.out.println("CommandLine------------- error args");
            return false;
        }
        if ((cmd.equals("cat"))&&((arr.length < 2))) {
            if (arr.length!=3 &&(input.contains(">") && input.contains(">>")))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }

                return false;

        }
        if ((cmd.equals("args"))&&((arr.length != 2))) {
            System.out.println("CommandLine------------- error args");
            return false;

        }
        /*if ((cmd.equals("help"))&&((arr.length != 1))) {
            System.out.println("CommandLine------------- error args");
            return false;

        }*/
        if (cmd.equals("help")){
            if ((arr.length!=3) && (arr.length!=1))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }
            if((arr.length==3) && (!input.contains(">") && !input.contains(">>")))
            {
                System.out.println("CommandLine------------- error args");
                return false;
            }


        }
        if ((cmd.equals("more"))&&((arr.length != 1))) {
            System.out.println("CommandLine------------- error args");
            return false;

        }
        /*if((( cmd.equals("cat")) && (arr.length!=2)) )
        {
            System.out.println("CommandLine------------- error args");
            return false;
        }*/

        else {
            return true;
        }
    }
    public String getCmd()
    {
        return cmd;

    }
    public ArrayList<String> getArguments()
    {
        args = new ArrayList<String>();
        for(int i=1;i<arr.length;i++) {
            args.add(arr[i]);
        }
        return args;
    }


    boolean checkExist()
    {
        if (command.contains(cmd))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
