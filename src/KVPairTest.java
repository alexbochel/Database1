/**
 * This class tests the KVPair class by calling all of its methods and making
 * sure they run correctly
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class KVPairTest extends student.TestCase
{
    private Handle name;
    private Handle artist;
    private KVPair pair;


    /**
     * Initializes two Handles and a KVPair object to use for testing
     */
    public void setUp()
    {
        name = new Handle(10);
        artist = new Handle(50);
        pair = new KVPair(artist, name);
    }


    /**
     * Tests the getKey() method to make sure it is returning the correct Handle
     */
    public void testGetKey()
    {
        assertEquals(50, pair.getKey().getOffset());
    }


    /**
     * Tests the getValue() method to make sure it is returning the correct
     * Handle
     */
    public void testGetValue()
    {
        assertEquals(10, pair.getValue().getOffset());
    }
}
