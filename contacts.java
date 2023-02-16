/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.Formatter;

/**
 *
 * @author a7027
 */
public class contacts {

    public static void callContact(String name) { // to find the Number and then call it 

        String s[] = findNumber(name);

        if (s != null) {
            System.out.println("Calling " + name + " at " + s[1]);
        } else {
            System.out.println("No person found name : " + name);
        }

    }

    public static void saveContact(String Name, long number) {  // create new file and add a contact then save it in conact.txt 
        System.out.println("Saving Contact " + Name + " : " + number);

        File file = new File("contact.txt");
        try {

            file.createNewFile();
            PrintWriter pw = new PrintWriter(new FileWriter(file, true));
            pw.println(Name + " : " + number);
            pw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // to Searching contact if it is in contact.txt or not found
    public static String[] findNumber(String name) {

        try ( Scanner in = new Scanner(new File("contact.txt"))) {
            String s[] = new String[0];

            boolean foundperson = false;
            while (in.hasNextLine()) {
                s = in.nextLine().split(" : ");

                if (s[0].equals(name)) {
                    System.out.println("The number associated with " + s[0] + " is " + s[1]);
                    foundperson = true;
                    break;
                }
            }
            if (!foundperson) {
                System.out.println("Could not find " + name);
                s = null;
            }

            return s;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // to edit the contact and then save it 
    public static void Editing(String newName, String name, long newNumber) throws IOException {
        System.out.println("contact edited to " + newName + " and number is " + newNumber);

        Scanner input = new Scanner(Paths.get("contact.txt"));
        Formatter output = new Formatter("New.txt");

        String Name;
        long Number;

        Name = input.next();
        output.format("%s : %d", newName, newNumber);
        
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
        input = new Scanner(Paths.get("New.txt"));
        output = new Formatter("contact.txt");

        while (input.hasNext()) {
            Name = input.next();
            Number = input.nextLong();
            if (Name.equals(name)) {
                output.format("%s : %d", newName, newNumber);
            } else {
                output.format("%s : %d", name, Number);
            }
        }
        if (input != null) {
            input.close();
        }
        if (output != null) {
            output.close();
        }
    }

    // to deleting a line of the contact if it in contact.txt
    public static void Deletecontact(String oldfile, int LineNumber) {

        String newfile = "file.txt";

        File oldFile = new File(oldfile);
        File newFile = new File(newfile);
        String currentLine;
        int line = 0;
        try {

            FileWriter fw = new FileWriter(newFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter N = new PrintWriter(bw);

            FileReader F = new FileReader(oldfile);
            BufferedReader br = new BufferedReader(F);

            while ((currentLine = br.readLine()) != null) {

                line++;

                if (LineNumber != line) {
                    N.println(currentLine);
                }
            }
            N.flush();
            N.close();
            F.close();
            br.close();
            bw.close();
            fw.close();

            // Here the data is transferred to the new file and the old file is deleted   
            File ne = new File(oldfile);
            newFile.renameTo(ne);
            oldFile.delete();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static String[] findFirstLetter(String firstLetter) {

        firstLetter = firstLetter.toUpperCase();
        try ( Scanner in = new Scanner(new File("contact.txt"))) {
            String S[] = new String[0];

            boolean foundperson = false;
            while (in.hasNextLine()) {

                S = in.nextLine().split(" : ");

                while (firstLetter.equals(S[0].substring(0, 1).toUpperCase())) {
                    System.out.println(S[0] + " the number is " + S[1]);

                    foundperson = true;
                    break;

                }

            }
            if (!foundperson) {
                System.out.println("Could not find name began with " + firstLetter);
                S = null;
            }

            return S;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public static void disPlay() { // this method Display evrything in the project
        try ( Scanner in = new Scanner(System.in)) {
            String name;
            System.out.println("------Welcome to contacts------");

            do {
             

                System.out.println("Waht would you like to do?(1,2,3....,etc)");
                System.out.println("1.Add new contact ");
                System.out.println("2.Make a phone call ");
                System.out.println("3.Find a number ");
                System.out.println("4.Edit contact");  //Edit a contact
                System.out.println("5.Delete contact ");
                System.out.println("6.Find a first letter");

                int choice = in.nextInt();
                
                switch (choice) {

                    case 1 -> {

                        System.out.println("What is the name of the person whose phone number you are saving? ");
                        name = in.next();

                        System.out.println("What is the phone number of the person you are saving ? ");
                        long number = in.nextLong();
                        in.nextLine();
                        saveContact(name, number);
                    }
                    case 2 -> {
                        System.out.println("What is a person's name? ");
                        name = in.next();
                        callContact(name);
                    }
                    case 3 -> {
                        System.out.println("What is the name of the person whose phone number you are searching? ");
                        name = in.next();
                        findNumber(name);
                    }
                    case 4 -> {
                        System.out.println("What is the name of the person whose phone number you are searching? ");
                        name = in.next();
                        if (findNumber(name) != null) {

                        } else {
                            System.out.println("This is not found name in your contact !");
                            break;
                        }
                        System.out.println(" Are you want to modify his name and number  ?(Y|N)");

                        if (in.next().toLowerCase().charAt(0) != 'y') {
                            break;
                        }
                        System.out.println("What is the new name you want?");
                        String newName = in.next();

                        System.out.println("What is the new number you want?");
                        long newNumber = in.nextLong();
                        Editing(newName, name, newNumber);
                    }
                    case 5 -> {
                        System.out.println("What is the Line of the person you want to delete in your contact ?");
                        int LineNumber = in.nextInt();
                        if (LineNumber != 0) {
                        } else {
                            System.out.println("This is not found Line in your contact !");
                            break;
                        }
                        Deletecontact("contact.txt", LineNumber);
                    }
                    case 6 -> {
                        System.out.println("What is the first letter in the name ?");
                        String firstLetter = in.next();

                        findFirstLetter(firstLetter);
                    }

                }
                System.out.println("Do you wish to perform another action? (Y/N)");
                if (in.next().toLowerCase().charAt(0) != 'y') {
                    break;
                }

            } while (true); // Loop for disPlay method
        } catch ( Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
