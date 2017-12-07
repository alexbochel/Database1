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
    private HashTable     songTable;
    private HashTable     artistTable;
    private Scanner       scanner;


    public Database(int initialHashSize, int blockSize, String name, int size)
    {
        // 1. Initialize MemoryManager.
        memManager = new MemoryManager(size);

        // 2. Initialize two tables and two trees.
        songTable = new HashTable(size, memManager, true);
        artistTable = new HashTable(size, memManager, false);

        this.parseData(name);
    }


    /**
     * This method parses through the commands on the file passed in to the
     * scanner class.
     * 
     * @param file
     *            The data file passed in through main(...)
     */
    public void parseData(String file)
    {
        Exception exception = new Exception();

        try
        {
            scanner = new Scanner(new File(file));
        }
        catch (Exception e)
        {
            exception = e;
        }

        if (exception instanceof FileNotFoundException)
        {
            exception.printStackTrace();
            System.exit(1);
        }

        while (scanner.hasNext())
        {
            String command = scanner.next();

            // command = scanner.next();
            // callCommand(command);

            // insert
            if (command.equals("insert"))
            {
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];
                
                memManager.insertArtist(artist);
                memManager.insertName(name);
                
            }

            // delete
            else if (command.equals("delete"))
            {

            }
            
            // remove
            else if (command.equals("remove"))
            {
                
            }
            
            // print artist / song and print tree
            else if (command.equals("print"))
            {
                
            }
            
            // list
            else if (command.equals("list"))
            {
                
            }

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
