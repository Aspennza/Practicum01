import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

//Need to write javadoc later!

public class PersonGenerator
{
    public static void main(String[] args)
    {
        ArrayList<String> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\PersonTestData.txt");

        boolean done = false;

        //here go scanner, variables, arraylist, etc.
        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;


        //open a loop I think?

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

        for (String p : people)
        {
            System.out.println(p);
        }
        //prompt the user to enter data
        //put data into arraylist

        //ask the user if they want to enter more records

        //create writer
        //open a try catch around the writer
        //create the writer and all the filler code for that

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

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