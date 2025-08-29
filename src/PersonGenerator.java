import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
         //This ArrayList holds a list of all person records input by the user
        ArrayList<String> people = new ArrayList<>();

         //This Scanner takes all user input
        Scanner in = new Scanner(System.in);

         //This file holds the current directory
        File workingDirectory = new File(System.getProperty("user.dir"));

         //This Path holds the current directory concatenated with the location of the text file that will be created
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

         //This boolean holds the true/false value that determines if the user continues or stops entering records
        boolean done = false;

         //This String holds the finished comma-separated list of each person's ID, firstName, lastName, title, and YOB
        String personRec = "";

         //This String holds the 6-digit ID of each person record input by the user
        String ID = "";

         //This String holds the first name of each person input by the user
        String firstName = "";

         //This String holds the last name of each person input by the user
        String lastName = "";

         //This String holds the title (Mr., Ms., Esq., etc.) of each person input by the user
        String title = "";

         //This int holds the birth year of each person input by the user
        int YOB = 0;

         //This algorithm prompts the user to enter an ID, firstName, lastName, title, and YOB, concatenates these values into a record, adds the record to the people ArrayList, then determines if the user is finished entering records
        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID for your record [6 digits]");
            firstName = SafeInput.getNonZeroLenString(in, "Please enter the first name for your record");
            lastName = SafeInput.getNonZeroLenString(in, "Please enter the last name for your record");
            title = SafeInput.getNonZeroLenString(in, "Please enter the title for your record");
            YOB = SafeInput.getRangedInt(in, "Please enter the year of birth for your record [4 digits]", 1000, 9999);

            personRec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            people.add(personRec);

            done = SafeInput.getYNConfirm(in, "Have you finished entering records?");
        }while(!done);

         //This algorithm prints each record in the people ArrayList to the console
        for (String p : people)
        {
            System.out.println(p);
        }

         //This algorithm writes each record in the people ArrayList into the PersonTestData.txt file indicated above, then checks for exceptions
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

             //This algorithm writes each record in the people ArrayList into the PersonTestData.txt file
            for(String rec : people)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println("\nData file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}