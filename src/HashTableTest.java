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
    private Handle handle7;
    
    /**
     * This method sets up the testing environment for the test class. 
     */
    public void setUp()
    {
        int initialSize = 100;
        
        memManager = new MemoryManager(initialSize);

        handle = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("The Beatles");
        handle2 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("EDEN"); 
        handle3 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Sum 41");
        handle4 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Radical Face");
        handle5 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Owl City");
        handle6 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Soulstice");
        handle7 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("The Bums");
        
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
     * This tests the case of inserting a duplicate element into the table. 
     */
    public void testInsertDuplicate()
    {
        assertEquals(-1, artistTable.insert(handle));
        assertEquals(-1, artistTable.insert(handle2));
        assertEquals(-1, artistTable.insert(handle3));
    }
    
    /**
     * This tests the outcome of adding many elements to the string
     * without checking for correct placement. It only ensures that the table
     * does not crash or throw an exception. 
     */
    public void testInsertMany()
    {
        handle = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Taylor Swift");
        handle2 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Ariana Grande"); 
        handle3 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Spose");
        handle4 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Big Time Rush");
        handle5 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Aly and AJ");
        handle6 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Soulstice");
        handle7 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("The Bums");
        Handle handle8 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Eminem");
        Handle handle9 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Camp Rock"); 
        Handle handle10 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Rihanna");
        Handle handle11 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Queen");
        Handle handle12 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("The Animals");
        Handle handle13 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("ZZ Top");
        Handle handle14 = new Handle(memManager.getDatabaseSize());
        memManager.insertItem("Ben Crosby");  
        artistTable.insert(handle);
        artistTable.insert(handle2);
        artistTable.insert(handle3);
        artistTable.insert(handle4);
        artistTable.insert(handle5);
        artistTable.insert(handle6);
        artistTable.insert(handle7);   
        artistTable.insert(handle8);
        artistTable.insert(handle9);
        artistTable.insert(handle10);
        artistTable.insert(handle11);
        artistTable.insert(handle12);
        artistTable.insert(handle13);
        artistTable.insert(handle14);
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
        artistTable.insert(handle7);
        assertEquals(handle7.getOffset(), 
                artistTable.getTable()[2].getOffset());
    }
    
    /**
     * This tests the get entry method in the HashTable. 
     */
    public void testGetEntry()
    {
        assertEquals(handle.getOffset(), artistTable.getEntry("The Beatles")
                .getOffset());
        assertEquals(handle2.getOffset(), artistTable.getEntry("EDEN")
                .getOffset());
        assertEquals(handle3.getOffset(), artistTable.getEntry("Sum 41")
                .getOffset());
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
        assertEquals(20, artistTable.getCurrentTableSize());
    }
}
