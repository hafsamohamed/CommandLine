import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<String> CommandLine;
    public static String inp;
    public static String inpt;
    public static String[] pipe_list ;
    public static void main(String[] args) throws IOException, InterruptedException {
        CommandLine = new ArrayList<String>();
        Termianl termianl = new Termianl();
        getCommands();
        Scanner sc = new Scanner(System.in);
        //___________________________________________________________________
        System.out.print("CommandLine------------- " );
        inpt = sc.nextLine();
        while (!(inpt.equalsIgnoreCase("stop"))) {
            if(inpt.contains(";"))
            {
                pipe_list = inpt.split(";");
                for(int i=0; i<pipe_list.length;i++)
                {
                    inp = pipe_list[i].trim();// remove space only begin and end
                    termianl.display();
                }
            }
            else {
                inp = inpt;
                termianl.display();
            }
            System.out.print("CommandLine------------- " );
            inpt = sc.nextLine();
        }
    }


    public static void getCommands()
    {
        CommandLine.add("pwd");
        CommandLine.add("ls");
        CommandLine.add("clear");
        CommandLine.add("cp");
        CommandLine.add("mv");
        CommandLine.add("rm");
        CommandLine.add("date");
        CommandLine.add("cd");
        CommandLine.add("mkdir");
        CommandLine.add("rmdir");
        CommandLine.add("cat");
        CommandLine.add("args");
        CommandLine.add("help");
        CommandLine.add("more");


    }
}
