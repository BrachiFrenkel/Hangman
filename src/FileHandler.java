import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {

    public static ArrayList<String> readFile(String filename) throws FileNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        Scanner scan = new Scanner(new File(filename));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            list.add(line);
        }
        scan.close();
        return list;
    }

}
