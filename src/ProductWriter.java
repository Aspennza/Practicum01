import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        //This ArrayList holds the total number of product records entered by the user
        ArrayList<String> products = new ArrayList<>();

        //This Scanner takes all user input
        Scanner in = new Scanner(System.in);

        //This File holds the current directory
        File workingDirectory = new File(System.getProperty("user.dir"));

        //This Path holds the current directory concatenated with the location of the text file that will be created
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        //This boolean holds the true/false value that determines whether the user is done entering records
        boolean done = false;

        //This String holds the concatenated product record, containing the ID, name, description, and cost of a given product
        String productRec = "";

        //This String contains the 6-digit ID the user inputs for a product
        String ID = "";

        //This String contains the name the user inputs for a product
        String name = "";

        //This String contains the description the user inputs for a product
        String description = "";

        //This double contains the cost the user inputs for a product
        double cost = 0.00;

        //This algorithm takes the user's input to receive an ID, name, description, and cost for a product, concatenates them into a productRec, and adds the record to the products ArrayList, then checks if the user is done entering records
        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID for your product [6 digits]");
            name = SafeInput.getNonZeroLenString(in, "Please enter the name of your product");
            description = SafeInput.getNonZeroLenString(in, "Please enter a short description of your product");
            cost = SafeInput.getDouble(in, "Please enter the cost of your product [non-negative]");

            productRec = ID + ", " + name + ", " + description + ", " + cost;
            products.add(productRec);

            done = SafeInput.getYNConfirm(in, "Have you finished entering products?");
        }while(!done);

        //This algorithm prints each record in the products ArrayList to the console
        for (String p : products)
        {
            System.out.println(p);
        }

        //This algorithm writes each record in the products ArrayList into the ProductTestData.txt file indicated above, then checks for exceptions
        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            //This algorithm writes each record in the products ArrayList into the ProductTestData.txt file indicated above
            for(String rec : products)
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
