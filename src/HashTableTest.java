/**
 * This class tests the hashtable implementation. 
 * 
 * @author Alexander James Bochel
 * @version 12.3.2017
 *
 */
public class HashTableTest extends student.TestCase {
    
    private MemoryManager memManager;
    private HashTable artistTable;
    private Handle handle;
    private Handle handle2;
    private Handle handle3;
    private Handle handle4;
    private Handle handle5;
    private Handle handle6;
    
    /**
     * This method sets up the testing environment for the test class. 
     */
    public void setUp()
    {
        int initialSize = 100;
        
        memManager = new MemoryManager(initialSize);
        handle = new Handle(memManager.getArtistSize());
        memManager.insertArtist("The Beatles");
        handle2 = new Handle(memManager.getArtistSize());
        memManager.insertArtist("EDEN"); 
        handle3 = new Handle(memManager.getArtistSize());
        memManager.insertArtist("Sum 41");
        handle4 = new Handle(memManager.getArtistSize());
        memManager.insertArtist("Radical Face");
        handle5 = new Handle(memManager.getArtistSize());
        memManager.insertArtist("Owl City");
        handle6 = new Handle(memManager.getArtistSize());
        memManager.insertArtist("Soulstice");
        
        artistTable = new HashTable(10, memManager, false);
        artistTable.insert(handle);
        artistTable.insert(handle2);
        artistTable.insert(handle3);
    }
    
    /**
     * This tests the hashing function in the HashTable class. 
     */
    public void testHash()
    {
        // Added
        System.out.println(artistTable.hash("The Beatles", 10));
        System.out.println(artistTable.hash("EDEN", 10));
        System.out.println(artistTable.hash("Sum 41", 10));
        
        // Not added but not dupes
        System.out.println(artistTable.hash("Soulstice", 10));
        System.out.println(artistTable.hash("Bon Jovi", 10));
        System.out.println(artistTable.hash("Prince", 10));
        
        // Repeats
        System.out.println(artistTable.hash("Radical Face", 10));
        System.out.println(artistTable.hash("Owl City", 10));
        System.out.println(artistTable.hash("The Bums", 10));
    }
    
    /**
     * This tests the insert function for the hash table when there are no
     * same slot items. 
     */
    public void testInsert()
    {
        assertEquals(handle.getOffset(), 
                artistTable.getTable()[0].getOffset());
        assertEquals(handle2.getOffset(), 
                artistTable.getTable()[9].getOffset());
        assertEquals(handle3.getOffset(), 
                artistTable.getTable()[7].getOffset());
    }
    
    /**
     * This tests the insert function for the hash table when probing is
     * necessary.
     */
    public void testInsertWithProbing()
    {
        artistTable.insert(handle4);
        assertEquals(handle4.getOffset(), 
                artistTable.getTable()[8].getOffset());
    }
    
    /**
     * This tests the get entry method in the HashTable. 
     */
    public void testGetEntry()
    {
        
    }
    
    /**
     * This tests the delete method in the HashTable. 
     */
    public void testDelete()
    {
        
    }
    
    /**
     * This tests the expand table method in the HashTable. 
     */
    public void testExpandTable()
    {
        artistTable.insert(handle4);
        artistTable.insert(handle5);
        artistTable.insert(handle6);
    }
}
