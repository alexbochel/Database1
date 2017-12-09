///**
// * This test class tests the memory manager class.
// * 
// * @author Alexander James Bochel and Brady Engleman
// * @version 12.3.2017
// */
//public class MemoryManagerTest extends student.TestCase
//{
//
//    private MemoryManager memManager;
//
//
//    /**
//     * This method sets up the testig environment for the test class.
//     */
//    public void setUp()
//    {
//        int initialSize = 100;
//        memManager = new MemoryManager(initialSize);
//    }
//
//
//    /**
//     * Tests that we can successfully insert an artist name into the byte array
//     * that holds them
//     */
//    public void testInsertArtist()
//    {
//        memManager.insertItem("The Beatles");
//        assertEquals(14, memManager.getDatabaseSize());
//
//        memManager.insertItem("EDEN");
//        assertEquals(21, memManager.getDatabaseSize());
//    }
//
//
//    /**
//     * Tests that we can successfully insert a song name into the byte array
//     * that holds them
//     */
//    public void testInsertName()
//    {
//        memManager.insertItem("Shelter");
//        assertEquals(10, memManager.getNameSize());
//
//        memManager.insertItem("Riptide");
//        assertEquals(20, memManager.getNameSize());
//    }
//
//
//    /**
//     * Tests that deleting a song will set the correct element in the byte array
//     * to 0
//     */
//    public void testDeleteName()
//    {
//        memManager.insertItem("Shelter");
//        memManager.insertItem("Riptide");
//        memManager.deleteItem(0);
//        memManager.deleteItem(10);
//
//        assertEquals(0, memManager.getDataItems()[0]);
//        assertTrue(memManager.getSongNames()[4] != 0);
//        assertEquals(0, memManager.getSongNames()[10]);
//        assertTrue(memManager.getSongNames()[15] != 0);
//    }
//
//
//    /**
//     * Tests that deleting an artist will set the correct element in the byte
//     * array to 0
//     */
//    public void testDeleteArtist()
//    {
//        memManager.insertArtist("The Beatles");
//        memManager.insertArtist("EDEN");
//        memManager.deleteArtist(0);
//        memManager.deleteArtist(14);
//
//        assertEquals(0, memManager.getArtistNames()[0]);
//        assertTrue(memManager.getArtistNames()[5] != 0);
//        assertEquals(0, memManager.getArtistNames()[14]);
//        assertTrue(memManager.getArtistNames()[17] != 0);
//    }
//
//
//    /**
//     * Tests that the byte array that holds the artist names will automatically
//     * expand if it is not big enough to hold the next entry. The capacity of
//     * the array should double every time it expands
//     */
//    public void testExpandArtistArray()
//    {
//        assertEquals(100, memManager.getArtistNames().length);
//
//        for (int i = 0; i < 10; i++)
//        {
//            memManager.insertArtist("The Beatles");
//        }
//
//        assertEquals(200, memManager.getArtistNames().length);
//
//        for (int i = 0; i < 5; i++)
//        {
//            memManager.insertArtist("The Beatles");
//        }
//
//        assertEquals(400, memManager.getArtistNames().length);
//    }
//
//
//    /**
//     * Tests that the byte array that holds the song names will automatically
//     * expand if it is not big enough to hold the next entry. The capacity of
//     * the array should double every time it expands
//     */
//    public void testExpandNameArray()
//    {
//        assertEquals(100, memManager.getSongNames().length);
//
//        for (int i = 0; i < 15; i++)
//        {
//            memManager.insertName("Shelter");
//        }
//
//        assertEquals(200, memManager.getSongNames().length);
//
//        for (int i = 0; i < 10; i++)
//        {
//            memManager.insertName("The Beatles");
//        }
//
//        assertEquals(400, memManager.getSongNames().length);
//    }
//
//}
