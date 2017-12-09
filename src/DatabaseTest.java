/**
 * This class tests the Database input. 
 * @author Alexander James Bochel
 * @version 12.9.2017
 *
 */
public class DatabaseTest extends student.TestCase
{
    private SongSearch main;
    
    /**
     * This sets up the testing environment. 
     */
    public void setUp()
    {
        main = new SongSearch();
    }
    
    /**
     * This tests the database input. 
     */
    @SuppressWarnings("static-access")
    public void testDatabase()
    {
        String[] args = {"100", "100", "text"};
        assertNotNull(main);
        main.main(args);
    }
}
