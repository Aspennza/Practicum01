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

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";
        ArrayList<String> products = new ArrayList<>();

        final int FIELDS_LENGTH = 4;
        String ID = "";
        String name = "";
        String description = "";
        double cost = 0.00;

        try
        {
            System.out.println("Please select a file containing product records to read to the console.");

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                int line = 0;
                while(reader.ready())
                {
                    rec = reader.readLine();
                    products.add(rec);
                    line++;

                    System.out.printf("\nLine %-4d %-60s", line, rec);
                }
                reader.close();
                System.out.println("\n\nThe data file has been read.\n");

                System.out.println("ID#     Name          Description         Cost");
                System.out.println("===================================================");

                String[] fields;
                for(String p : products)
                {
                    fields = p.split(",");

                    if(fields.length == FIELDS_LENGTH)
                    {
                        ID = fields[0].trim();
                        name = fields[1].trim();
                        description = fields[2].trim();
                        cost = Double.parseDouble(fields[3].trim());
                        System.out.printf("\n%-8s%-14s%-20s%8f", ID, name, description, cost);
                    }
                    else
                    {
                        System.out.println("One of the records in your file may be corrupted. Please select a different file.");
                        System.out.println(p);
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
