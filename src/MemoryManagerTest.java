/**
 * This test class tests the memory manager class.
 * 
 * @author Alexander James Bochel and Brady Engleman
 * @version 12.3.2017
 */
public class MemoryManagerTest extends student.TestCase
{

    private MemoryManager memManager;


    /**
     * This method sets up the testig environment for the test class.
     */
    public void setUp()
    {
        int initialSize = 100;
        memManager = new MemoryManager(initialSize);
    }


    /**
     * Tests that we can successfully insert an artist name into the byte array
     * that holds them
     */
    public void testInsertArtist()
    {
        memManager.insertArtist("The Beatles");
        assertEquals(11, memManager.getArtistSize());

        memManager.insertArtist("EDEN");
        assertEquals(15, memManager.getArtistSize());
    }


    /**
     * Tests that we can successfully insert a song name into the byte array
     * that holds them
     */
    public void testInsertName()
    {
        memManager.insertName("Shelter");
        assertEquals(7, memManager.getNameSize());

        memManager.insertName("Riptide");
        assertEquals(14, memManager.getNameSize());
    }


    /**
     * Tests that deleting a song will set the correct element in the byte array
     * to 0
     */
    public void testDeleteName()
    {
        memManager.insertName("Shelter");
        memManager.insertName("Riptide");
        memManager.deleteName(0);
        memManager.deleteName(7);

        assertEquals(0, memManager.getSongNames()[0]);
        assertTrue(memManager.getSongNames()[1] != 0);
        assertEquals(0, memManager.getSongNames()[7]);
        assertTrue(memManager.getSongNames()[8] != 0);
    }


    /**
     * Tests that deleting an artist will set the correct element in the byte
     * array to 0
     */
    public void testDeleteArtist()
    {
        memManager.insertArtist("The Beatles");
        memManager.insertArtist("EDEN");
        memManager.deleteArtist(0);
        memManager.deleteArtist(11);

        assertEquals(0, memManager.getArtistNames()[0]);
        assertTrue(memManager.getArtistNames()[1] != 0);
        assertEquals(0, memManager.getArtistNames()[11]);
        assertTrue(memManager.getArtistNames()[12] != 0);
    }


    /**
     * Tests that the byte array that holds the artist names will automatically
     * expand if it is not big enough to hold the next entry. The capacity of
     * the array should double every time it expands
     */
    public void testExpandArtistArray()
    {
        assertEquals(100, memManager.getArtistNames().length);

        for (int i = 0; i < 15; i++)
        {
            memManager.insertArtist("The Beatles");
        }

        assertEquals(200, memManager.getArtistNames().length);

        for (int i = 0; i < 5; i++)
        {
            memManager.insertArtist("The Beatles");
        }

        assertEquals(400, memManager.getArtistNames().length);
    }


    /**
     * Tests that the byte array that holds the song names will automatically
     * expand if it is not big enough to hold the next entry. The capacity of
     * the array should double every time it expands
     */
    public void testExpandNameArray()
    {
        assertEquals(100, memManager.getSongNames().length);

        for (int i = 0; i < 15; i++)
        {
            memManager.insertName("Shelter");
        }

        assertEquals(200, memManager.getSongNames().length);

        for (int i = 0; i < 10; i++)
        {
            memManager.insertName("The Beatles");
        }

        assertEquals(400, memManager.getSongNames().length);
    }

}
