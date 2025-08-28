import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

        String productRec = "";
        String ID = "";
        String name = "";
        String description = "";
        double cost = 0.00;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Please enter the ID for your product [6 digits]");
            name = SafeInput.getNonZeroLenString(in, "Please enter the name of your product");
            description = SafeInput.getNonZeroLenString(in, "Please enter a short description of your product");
            cost = SafeInput.getDouble(in, "Please enter the cost of your product [non-negative]");

            productRec = ID + ", " + name + ", " + description + ", " + cost;
            products.add(productRec);

            done = SafeInput.getYNConfirm(in, "Have you finished entering products?");
        }while(!done);
    }
}
