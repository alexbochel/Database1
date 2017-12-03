import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class will be responsible for reading through the input file, creating
 * the byte arrays, and handling methods that are called in the input file
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class Database
{
    private MemoryManager memManager;
    private HashTable songTable;
    private HashTable artistTable;
    private Scanner scanner;
    
    public Database(int initialHashSize, int blockSize, String file)
    {
        // 1. Initialize two tables and two trees. 
        // 2. Initialize MemoryManager.
    }
    
    /**
     * This method parses through the commands on the file passed in to the 
     * scanner class. 
     * @param file The data file passed in through main(...)
     */
    public void parseData(String file)
    {
        Exception exception = new Exception();

        try {
            scanner = new Scanner(new File(file));
        } 
        catch (Exception e) {
            exception = e;
        }

        if (exception instanceof FileNotFoundException) {
            exception.printStackTrace();
            System.exit(1);
        }
         
        while (scanner.hasNext())
        {
            String command = ""; 
            command = scanner.next();
            callCommand(command);
        }
        scanner.close();
    }
    
    /**
     * This method calls the specific command for 
     */
    private void callCommand(String command)
    {
        
    }
}
