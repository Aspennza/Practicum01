import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.System.out;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

public class PersonReader
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //This file chooser is used to select a file to read
        JFileChooser chooser = new JFileChooser();

        //This File holds the file selected by the user
        File selectedFile;

        //This String holds the line that has just been read from the selected file
        String rec = "";

        //This ArrayList holds every line from the document that is being read
        ArrayList<String> records = new ArrayList<>();

        //This int sets a permanent value for the number of fields that should be found in each record
        final int FIELDS_LENGTH = 5;

        //This String holds the ID of each record read from the chosen text file
        String ID = "";

        //This String holds the firstName of each record read from the chosen text file
        String firstName = "";

        //This String holds the lastName of each record read from the chosen text file
        String lastName = "";

        //This String holds the title of each record read from the chosen text file
        String title = "";

        //This int holds the year of birth of each record read from the chosen text file
        int YOB = 0;

        //This algorithm prompts the reader to select a file to read, reads the contents of the file to the records ArrayList, splits each record in the ArrayList into five fields, prints them to the console, and checks for exceptions
        try
        {
            System.out.println("Please select a file containing personal records to read to the console.");

            //This File holds the current directory
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            //This algorithm identifies whether the user has selected a file, reads the file to the records ArrayList, splits each record in the ArrayList into five fields, then prints them to the console
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                //this int counts the number of lines that have been read
                int line = 0;

                //This algorithm reads the records from the chosen text file into the records ArrayList and prints each one out as it is read
                while(reader.ready())
                {
                    rec = reader.readLine();
                    records.add(rec);
                    line++;

                    System.out.printf("\nLine %4d %-60s ", line, rec);
                }
                reader.close();
                System.out.println("\n\nThe data file has been read.\n");

                System.out.println("ID#     Firstname     Lastname     Title     YOB");
                System.out.println("================================================");

                //This array holds the values from each record after they have been split
                String[] fields;

                //This algorithm splits each record in the records ArrayList based on comma delimiters, puts the split values into the fields array, then puts each value from the fields array into a separate variable for printing
                for(String r : records)
                {
                    fields = r.split(",");

                    //This algorithm checks if the number of values per record is as expected, trims each value from the fields array, then puts it into a separate variable for printing
                    if(fields.length == FIELDS_LENGTH)
                    {
                        ID = fields[0].trim();
                        firstName = fields[1].trim();
                        lastName = fields[2].trim();
                        title = fields[3].trim();
                        YOB = Integer.parseInt(fields[4].trim());
                        System.out.printf("\n%-8s%-14s%-13s%-8s%5d", ID, firstName, lastName, title, YOB);
                    }
                    else
                    {
                        System.out.println("One of the records in your file may be corrupted. Please select a different file.");
                        System.out.println(r);
                    }
                }
            }
            else
            {
                System.out.println("No file was selected.");
                System.out.println("Please run this program again to select a file.");
                System.exit(0);
            }

        }
        catch (FileNotFoundException e)
        {
            System.out.println("The file couldn't be found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.out.println("An exception occurred.");
            e.printStackTrace();
        }
    }
}
