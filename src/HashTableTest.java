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
    private HashTable songTable;
    private Handle handle;
    private Handle handle2;
    private Handle handle3;
    private Handle handle4;
    private Handle handle5;
    
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
        
        songTable = new HashTable(10, memManager, true);
        artistTable = new HashTable(10, memManager, false);
        
    }
    
    /**
     * This tests the hashing function in the HashTable class. 
     */
    public void testHash()
    {
        // First Times
        System.out.println(artistTable.hash("The Beatles", 10));
        System.out.println(artistTable.hash("EDEN", 10));
        System.out.println(artistTable.hash("Sum 41", 10));
        System.out.println(artistTable.hash("Soulstice", 10));
        System.out.println(artistTable.hash("Bon Jovi", 10));
        System.out.println(artistTable.hash("Prince", 10));
        
        // Repeats
        System.out.println(artistTable.hash("Radical Face", 10));
        System.out.println(artistTable.hash("Owl City", 10));
        System.out.println(artistTable.hash("Sia", 10));
    }
    
    /**
     * This tests the insert function for the hash table when there are no
     * same slot items. 
     */
    public void testInsert()
    {
        artistTable.insert(handle);
        artistTable.insert(handle2);
        //assertEquals();
    }
    
    /**
     * This tests the insert function for the hash table when probing is
     * necessary.
     */
    public void testInsertWithProbing()
    {
        
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
        
    }
}
