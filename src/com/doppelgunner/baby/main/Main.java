package com.doppelgunner.baby.main;

import com.doppelgunner.baby.model.Baby;

import java.io.*;
import java.util.Scanner;

/**
 * Created by robertoguazon on 12/08/2016.
 */
public class Main {

    private static Scanner cin = new Scanner(System.in);

    public static int showFiles(){
        File folder = new File("data");
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles == null || listOfFiles.length == 0) {
            System.out.println("The folder is empty");
            return 0;
        }

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("\t"+listOfFiles[i].getName());
            }
        }

        return listOfFiles.length;
    }

    public static void save(String filename, Baby baby) throws IOException {
        new File("data").mkdir();
        File file = new File("resources/data/"+filename+".bob");

        if (file.exists()) {
            while (true) {
                System.out.println("file already exists want to overwrite?");
                System.out.println("Enter a command: 1 - yes, 2 - no");
                String command = cin.nextLine();
                command = command.trim();
                if (command.equals("1")) {
                    System.out.println("overwriting " + file);
                    break;
                } else if (command.equals("2")) {
                    System.out.println("Saving file was cancelled.");
                    return;
                }

            }
        }

        FileOutputStream fileOut = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(baby);

        out.close();
        fileOut.close();
        System.out.println("The data is saved on: " + file);
    }

    public static Baby load(String filename) throws Exception {
        File file = new File("resources/data/"+filename+".bob");
        FileInputStream fileInput = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileInput);
        Baby baby = (Baby)in.readObject();
        return baby;
    }

    public static void main(String[] args) {
        Baby baby = new Baby();
        while(true) {
            System.out.println("Enter a command: -load, -save, -teach, -ask, -exit");
            String command = cin.nextLine();
            switch(command) {

                //---load
                case "-load":
                    System.out.println("Files in the data folder:");
                    int files = showFiles();
                    if (files > 0) {
                        while(true) {

                            System.out.print("Input a valid filename: ");
                            String filename = cin.nextLine();
                            //
                            if (filename.equals("-exit")) break;

                            try {
                                baby = load(filename);
                                break;
                            } catch (Exception ex) {
                                System.out.println("File not found");
                            }
                        }
                    }
                    break;
                //---end load

                //---save
                case "-save":
                    while(true) {
                        System.out.print("Input a valid filename: ");
                        String filename = cin.nextLine();
                        //
                        if (filename.equals("-exit")) break;
                        try {
                            save(filename,baby);
                            break;
                        } catch (Exception ex) {
                            System.out.println("not a valid filename.");
                            ex.printStackTrace();
                        }
                    }
                    break;
                //---end save

                //--teach
                case "-teach":
                    System.out.println("Enter: (subject : content1 : content2 : content...)");
                    while(true) {
                        System.out.print("Enter an idea: ");
                        String query = cin.nextLine();
                        query = query.trim();
                        if (query.equals("-exit")) break;

                        String[] ideas = query.split(":");
                        for (int i = 0; i < ideas.length; i++) {
                            ideas[i] = ideas[i].trim();
                        }

                        String subject = ideas[0];
                        System.out.println(subject);
                        for (int i = 1; i < ideas.length; i++) {
                            baby.teach(subject, ideas[i]);
                            System.out.println(ideas[i]);
                        }
                        System.out.println("The baby learned it.");
                    }
                    break;
                //--end teach

                //--ask
                case "-ask":
                    System.out.println("Enter: (subject)");
                    String subject = "";
                    String answer = "";
                    while(true) {
                        System.out.println("commands -ask, -exit, -forgot (index)");
                        System.out.print("Ask something: ");
                        String query = cin.nextLine();
                        query = query.trim();

                        if (query.equals("-exit")) break;
                        else if(query.equals("-forgot")) {
                            if (subject == null || subject.length() == 0) {
                                System.out.println("Ask something first.");
                            } else {
                                if (answer != null || answer.length() != 0) {
                                    while (true) {
                                        System.out.println(answer);
                                        System.out.print("Type the index to forget a memory: ");
                                        String toForget = cin.nextLine();
                                        toForget = toForget.trim();
                                        if (toForget.equals("-exit")) break;
                                        else{
                                            try {
                                                int index = Integer.parseInt(toForget);
                                                baby.forget(subject, index);
                                                answer = baby.ask(subject);
                                            } catch (Exception ex) {
                                                continue;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            subject = query;
                            try {
                                answer = baby.ask(subject);
                                System.out.println("The baby can answer that one.");
                                System.out.println(answer);
                            } catch (Exception ex) {
                                System.out.println("Ohh! he don't know anything about that topic.");
                            }
                        }

                    }
                    break;
                //--end ask

                case "-exit":
                    System.out.println("Thank you for using the app");
                    System.exit(0);
            }
        }
    }

}
