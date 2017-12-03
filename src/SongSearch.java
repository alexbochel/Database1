/**
 * This is the starter class for the project. 
 * 
 * @author Alexander James Bochel
 * @version 12.2.2017
 */
public class SongSearch 
{
    /** 
     * This method creates the input class. 
     * 
     * @param args Command line arguments. 
     */
    public static void main(String[] args)
    {
        @SuppressWarnings("unused")
        Database database = new Database(Integer.parseInt(args[0]),
                Integer.parseInt(args[1]), args[2]);
    }
}
