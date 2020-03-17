import java.io.*;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.lang.System;
import java.util.Date;
import java.util.Scanner;

public class Termianl {

    Parser parser;
    public static String default_path = new File("").getAbsolutePath();

    public void display() throws IOException, InterruptedException {
        parser = new Parser();
        parser.split1();
        if (parser.checkExist()) {
            switch (parser.command.indexOf(parser.getCmd())) {
                case 0:
                    if (parser.parse())
                        System.out.println("CommandLine------------- " + pwd());
                    break;
                case 1:
                    if (parser.parse())
                        if (parser.getArguments().size()==0)
                        {
                            System.out.println("CommandLine------------- \n" + ls());
                            }
                        if (parser.getArguments().size()==2) {
                            if (parser.getArguments().get(0).equals(">>")) {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), true));
                                writer.append(ls());
                                writer.close();

                            }
                            if (parser.getArguments().get(0).equals(">")) {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), false));
                                writer.write(ls());
                                writer.close();

                            }
                        }
                             break;
                case 2:
                    if (parser.parse())
                        clear();
                    break;
                case 3:
                    if (parser.parse())
                        cp(parser.getArguments().get(0), parser.getArguments().get(1));
                    break;
                case 4:
                    if (parser.parse())
                        mv(parser.getArguments().get(0), parser.getArguments().get(1));
                    break;
                case 5:
                    if (parser.parse())
                        rm(parser.getArguments().get(0));
                    break;
                case 6:
                    /*if (parser.parse())
                        date();
                    break;*/
                    if (parser.parse())
                        if (parser.getArguments().size()==0)
                        {
                            System.out.println("CommandLine------------- \n" + date());
                        }
                    if (parser.getArguments().size()==2) {
                        if (parser.getArguments().get(0).equals(">>")) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), true));
                            writer.append(date());
                            writer.close();

                        }
                        if (parser.getArguments().get(0).equals(">")) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), false));
                            writer.write(date());
                            writer.close();

                        }
                    }
                    break;
                case 7:
                    if (parser.parse())
                        cd(parser.getArguments().get(0));
                    break;
                case 8:
                    if (parser.parse())
                        mkdir(parser.getArguments().get(0));
                    break;
                case 9:
                    if (parser.parse())
                        rmdir(parser.getArguments().get(0));
                    break;
                case 10:
                    if (parser.parse())
                        if (parser.getArguments().get(0).equals(">>"))
                        {
                            Scanner s = new Scanner(System.in);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), true));
                            String c = s.nextLine();
                            writer.append(c);
                            writer.close();
                            break;

                        }
                        if (parser.getArguments().get(0).equals(">"))
                        {
                            Scanner s = new Scanner(System.in);
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), false));
                            String c = s.nextLine();
                            writer.write(c);
                            writer.close();
                            break;
                        }
                        else {
                            String total="";
                            if (parser.getArguments().contains(">")) {
                                String file_name = parser.getArguments().get(parser.getArguments().indexOf(">")+1);
                                for (int i = 0; i < parser.getArguments().size()-2; i++) {
                                   total+= cat(parser.getArguments().get(i));
                                }
                                //System.out.print(total);
                                BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + file_name, false));
                                writer.write(total);
                                total = "";
                                writer.close();
                            }
                            if (parser.getArguments().contains(">>")) {
                                String file_name = parser.getArguments().get(parser.getArguments().indexOf(">>")+1);
                                for (int i = 0; i < parser.getArguments().size()-2; i++) {
                                    total+= cat(parser.getArguments().get(i));
                                }
                                System.out.print(total);
                                BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + file_name, true));
                                writer.append(total);
                                total = "";
                                writer.close();
                            }
                            if ((!parser.getArguments().contains(">>"))&&(!parser.getArguments().contains(">")))
                            {
                                for (int i = 0; i < parser.getArguments().size(); i++) {
                                    total+= cat(parser.getArguments().get(i));
                                }
                                System.out.print(total);
                                total = "";
                            }
                        }
                    break;
                case 11:
                    if (parser.parse())
                            args(parser.getArguments().get(0));
                    break;
                case 12:
                    /*if (parser.parse())
                        help();
                    break;*/
                    if (parser.parse())
                        if (parser.getArguments().size()==0)
                        {
                            System.out.println("CommandLine------------- \n" + help());
                        }
                    if (parser.getArguments().size()==2) {
                        if (parser.getArguments().get(0).equals(">>")) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), true));
                            writer.append(help());
                            writer.close();

                        }
                        if (parser.getArguments().get(0).equals(">")) {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(default_path + "\\" + parser.getArguments().get(1), false));
                            writer.write(help());
                            writer.close();

                        }
                    }
                    break;
                case 13:
                    if (parser.parse())
                        more("xx.txt" , default_path);
                    break;
                default:
                    System.out.println("no match");
            }
        } else {
            System.out.print("CommandLine-------------" + "not exist command" + '\n');
        }
    }

    public String pwd() {
        //default_path =
        return default_path;
    }

    public String ls() {
        String all_files = "";
        File f = new File(default_path);
        File arr[] = f.listFiles();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            all_files += arr[i].getName();
            all_files += '\n';
        }
        return all_files;
    }

    public void clear() throws IOException, InterruptedException {
        for (int i = 0; i < 60; i++) {
            System.out.println('\n');
        }
    }

    public void cp(String sourcePath, String destinationPath) throws IOException {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        //System.out.println(default_path);
        if (!sourcePath.contains(":")) {
            sourcePath = default_path + "\\" + sourcePath;
        }
        if (!destinationPath.contains(":")) {
            destinationPath = default_path + "\\" + destinationPath;
        }
        try {
            File f1 = new File(sourcePath);
            File f2 = new File(destinationPath);

            if (f1.exists() && (!f2.exists())) {
                sourceChannel = new FileInputStream(sourcePath).getChannel();
                destChannel = new FileOutputStream(destinationPath).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }
            else
            {
                System.out.print("CommandLine-------------" + "exist file" + '\n');
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sourceChannel.close();
            destChannel.close();
        }
    }

    public void mv(String sourcePath, String destinationPath) {
        try {
            if (!sourcePath.contains(":")) {
                sourcePath = default_path + "\\" + sourcePath;
            }
            if (!destinationPath.contains(":")) {
                destinationPath = default_path + "\\" + destinationPath;
            }
            File file = new File( sourcePath);
            File newFile = new File(destinationPath);
            if (newFile.exists())
            {
                Scanner x = new Scanner(System.in);
                System.out.print("CommandLine-------------" + "file already exist" +'\n' + "press 1 to replace...");
                int choice = x.nextInt();
                if (choice==1)
                {
                    newFile.delete();
                    file.renameTo(newFile);
                }

            }
            if (!file.exists())
            {
                System.out.print("CommandLine-------------" + "file Not exist" + "\n");
            }
            else {
                if (file.renameTo(newFile)) {
                } else {
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void rm(String sourcePath) {
        if (!sourcePath.contains(":"))
        {
            sourcePath = default_path + "\\" + sourcePath;
        }
        File file = new File(sourcePath); //remove the file
        if (file.exists()) {
            file.delete();
        }
        else
        {
            System.out.print("CommandLine-------------" + "file Not exist" + "\n");
        }
    }

    public String date() {
        Date date = new Date();
        // display time and date using toString()
        return date.toString();
    }

    public void cd(String direct) throws IOException {
        if (direct.contains(":")) {
            File myFile = new File(direct);
            default_path = myFile.getAbsolutePath();
            System.out.println(myFile.getAbsolutePath());
        }
        if (!direct.contains(":")) {
            File myFile = new File("");
            default_path = myFile.getAbsolutePath();
            String x = default_path + "\\" + direct;
            File File = new File(x);
            default_path = File.getAbsolutePath();
            System.out.println(default_path);
        }
    }

    public void mkdir(String path) {

        if (!path.contains(":")) {
            path = default_path + "\\" + path;
        }
        File f = new File(path);
        if (!f.exists())
        {
            f.mkdir();
        }
        else {
            System.out.println("CommandLine-------------" + "exist dir");
        }
    }

    public void rmdir(String path) {
        if (!path.contains(":")) {
            path = default_path + "\\" + path;
        }
        File f = new File(path);
        if (f.isDirectory() && f.exists()) {
            f.delete();
        } else {
            System.out.println("CommandLine-------------" + "not exist");
        }
    }

    public String cat(String path) throws IOException {
        String totat_str = "";
        if (!path.contains(":")) {
            path = default_path + "\\" + path;
        }
        File f = new File(path);
        if (f.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = null;
            while ((line = br.readLine()) != null) {
                totat_str+=line;
                totat_str+='\n';
            }
        } else {
            totat_str += "CommandLine------------- not exist";
        }
        return totat_str;
    }

    public void args(String arg) {
        String argss="";
        if ((arg.equals("ls")) || (arg.equals("pwd")) || (arg.equals("clear")) || (arg.equals("date")) || (arg.equals("help"))) {
            System.out.print( "CommandLine-------------" + "no args \n");
        }
        if ((arg.equals("cd")) || (arg.equals("mkdir")) || (arg.equals("rmdir")) || (arg.equals("args")) || (arg.equals("rm"))) {
            System.out.print( "CommandLine-------------" + "  args1:source path \n");

        }
        if ((arg.equals("cp")) || (arg.equals("mv"))) {
            System.out.print("CommandLine-------------" + "  args1:source path  " + "  args2:source path  \n");
        }
        if ((arg.equals("cat"))) {
            System.out.print("CommandLine-------------" + "  args1:alot \n");

        }
    }
        public String help()
        {
            String hep="";
            hep+="CommandLine-------------" + "  pwd " + ":print working directory\n";
            hep+="CommandLine-------------" + "  ls " + ":print list of all files in this directory\n";
            hep+="CommandLine-------------" + "  clear " + ":clear the console\n";
            hep+="CommandLine-------------" + "  cp " + ":copy source file to destination file\n";
            hep+="CommandLine-------------" + "  mv " + ":move source file to destination file\n";
            hep+="CommandLine-------------" + "  rm " + ":remove source file to destination file\n";
            hep+="CommandLine-------------" + "  date " + ":print hte current date\n";
            hep+="CommandLine-------------" + "  cd " + ":change directory\n";
            hep+="CommandLine-------------" + "  mkdir " + ":make directory\n";
            hep+="CommandLine-------------" + "  rmdir " + ":remove directory\n";
            hep+="CommandLine-------------" + "  cat " + ":display files content\n";
            hep+="CommandLine-------------" + "  args " + ":display arguments of each command\n";
            hep+="CommandLine-------------" + "  more " + ":enable us to scroll down\n";
            hep+="CommandLine-------------" + "  stop " + ":to terminate the program\n";
            return hep;
        }
        public void more(String path , String current)
        {

                try {
                    File f = new File(current+"\\"+path);
                    if(f.exists())
                    {
                    FileInputStream a = new FileInputStream(f);
                    BufferedReader pr = new BufferedReader(new InputStreamReader(a));
                    String l="";
                    int c = 0,x;
                    Scanner n = new Scanner(System.in);
                    while ((l = pr.readLine()) != null ) {
                        System.out.print(l + "\n");
                        c++;
                        if (c % 10 == 0) {
                            System.out.print("...........................................more press 1 or 2 to quit");
                            x = n.nextInt();
                            if (x == 2)
                                break;
                        }
                        }
                    }
                    else
                    {
                     System.out.print("CommandLine-------------" + "not exist");
                    }

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return;
        }
    }
    //mv C:\Users\ahmedelsayed\Desktop\adapter.txt C:\Users\ahmedelsayed\IdeaProjects\CommandLine\
    /*
    CommandLine.add("pwd"); //0
        CommandLine.add("ls"); //0
        CommandLine.add("clear"); //0
        CommandLine.add("cp"); //3
        CommandLine.add("mv"); //3
        CommandLine.add("rm"); // 2
        CommandLine.add("date");//0
        CommandLine.add("cd");//2
        CommandLine.add("mkdir");//2
        CommandLine.add("rmdir");//2
        CommandLine.add("cat");//alot
        CommandLine.add("args"); //2

        CommandLine.add("more");
        CommandLine.add("rmdir");
        CommandLine.add("man ls");
        CommandLine.add("grep");
        CommandLine.add("help");
     */