/**
 * This class tests the Handle class by calling all of its methods and making
 * sure they run correctly
 * 
 * @author Brady Engleman and Alex Bochel
 * @version 12/2/2017
 */
public class HandleTest extends student.TestCase
{

    private Handle handle;


    /**
     * This initializes the handle to be used for testing
     */
    public void setUp()
    {
        handle = new Handle(5);
    }


    /**
     * This tests the getOffset mathod to make sure it returns the correct
     * integer
     */
    public void testGetOffset()
    {
        assertEquals(5, handle.getOffset());
    }


    /**
     * This method tests the Handle's compareTo method by creating handles of
     * different sizes and comparing their offsets
     */
    public void testCompareTo()
    {
        Handle biggerHandle = new Handle(10);
        Handle smallerHandle = new Handle(1);
        Handle sameHandle = new Handle(5);

        assertTrue(handle.compareTo(biggerHandle) <= 0);
        assertTrue(handle.compareTo(smallerHandle) >= 0);
        assertEquals(0, handle.compareTo(sameHandle));
    }

}
