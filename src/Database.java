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
    private KVPairTree    artistTree;
    private KVPairTree    songTree;
    private Scanner       scanner;


    public Database(int initialHashSize, int blockSize, String name)
    {
        // 1. Initialize MemoryManager.
        memManager = new MemoryManager(initialHashSize);

        // 2. Initialize two tables and two trees.
        songTable = new HashTable(initialHashSize, memManager, true);
        artistTable = new HashTable(initialHashSize, memManager, false);

        // 3. Initialize two KVPairTrees
        artistTree = new KVPairTree(memManager, true);
        songTree = new KVPairTree(memManager, false);

        // 4. Call parse method
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

                // Getting the strings for the artist and name of the song
                scanner.skip(" ");
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];

                if (!artistTable.containsElement(artist) && !songTable
                    .containsElement(name))
                {
                    // First, create the Handles and add to memManager
                    Handle artistHandle = new Handle(memManager
                        .getDatabaseSize());
                    memManager.insertItem(artist);

                    Handle nameHandle = new Handle(memManager
                        .getDatabaseSize());
                    memManager.insertItem(name);

                    // Then create the KVPairs
                    KVPair pairArtist = new KVPair(artistHandle, nameHandle);
                    KVPair pairName = new KVPair(nameHandle, artistHandle);

                    // Add the Handles to the HashTables
                    artistTable.insert(artistHandle);
                    songTable.insert(nameHandle);

                    // Add to the KVPairTrees
                    artistTree.insert(pairArtist);
                    songTree.insert(pairName);
                }
                // duplicate
                else
                {
                    // print out duplicate info
                    System.out.println("|" + artist
                        + "| duplicates a record already in the Artist database.");
                    System.out.println("|" + name
                        + "| duplicates a record already in the Song database.");
                    System.out.println("The KVPair (|" + artist + "|,|" + name
                        + "|),(" + artistTable.getEntry(artist).getOffset()
                        + "," + songTable.getEntry(name).getOffset()
                        + ") duplicates a record already in the tree.");
                    System.out.println("The KVPair (|" + name + "|,|" + artist
                        + "|),(" + songTable.getEntry(name).getOffset() + ","
                        + artistTable.getEntry(artist).getOffset()
                        + ") duplicates a record already in the tree.");
                }
            }

            // delete
            else if (command.equals("delete"))
            {
                // Getting the strings for the artist and name of the song
                scanner.skip(" ");
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];

                if (artistTable.containsElement(artist))
                {
                    if (songTable.containsElement(name))
                    {
                        // Delete from the trees first (since they rely on the
                        // memManager)
                        artistTree.delete(artist, name);
                        songTree.delete(artist, name);

                        // Delete from the memManager
                        memManager.deleteItem(artistTable.getEntry(artist)
                            .getOffset());
                        memManager.deleteItem(songTable.getEntry(name)
                            .getOffset());

                        // Delete from the Hash Tables last
                        artistTable.delete(artist);
                        songTable.delete(name);
                    }
                    // does not contain song
                    else
                    {
                        System.out.println("|" + name
                            + "| does not exist in the song database.");
                    }
                }
                // does not contain artist
                else
                {
                    System.out.println("|" + artist
                        + "| does not exist in the artist database.");
                    // ALSO does not contain name
                    if (!songTable.containsElement(name))
                    {
                        System.out.println("|" + name
                            + "| does not exist in the song database.");
                    }
                }
            }

            // remove artist/song
            else if (command.equals("remove"))
            {
                String next = scanner.next();

                // remove artist
                if (next.equals("artist"))
                {
                    // Get artist String
                    scanner.skip(" ");
                    String artist = scanner.nextLine();

                    // First check if it is in the table
                    if (artistTable.containsElement(artist))
                    {

                        // delete artist from memManager and then artistTable
                        memManager.deleteItem(artistTable.getEntry(artist)
                            .getOffset());
                        artistTable.delete(artist);

                        // delete all songs from that artist from memManager and
                        // then songTable
                        for (String str : artistTree.getAllSongsFromArtist(
                            artist))
                        {
                            memManager.deleteItem(songTable.getEntry(str)
                                .getOffset());
                            songTable.delete(str);
                        }

                        // delete from trees
                        artistTree.removeArtist(artist);
                        songTree.removeArtist(artist);
                    }
                    else
                    {
                        System.out.println("|" + artist
                            + "| does not exist in the artist database.");
                    }
                }

                // remove song (name)
                else if (next.equals("song"))
                {
                    // Get name String
                    scanner.skip(" ");
                    String name = scanner.nextLine();

                    // First check if it's there
                    if (songTable.containsElement(name))
                    {
                        // delete name from memManager and then artistTable
                        memManager.deleteItem(artistTable.getEntry(name)
                            .getOffset());
                        artistTable.delete(name);

                        // delete all songs from that artist from memManager and
                        // then songTable
                        for (String str : artistTree.getAllArtistsFromSong(
                            name))
                        {
                            memManager.deleteItem(songTable.getEntry(str)
                                .getOffset());
                            songTable.delete(str);
                        }

                        // delete from trees
                        artistTree.removeName(name);
                        songTree.removeName(name);
                    }
                    else
                    {
                        System.out.println("| " + name
                            + "| does not exist in the song database.");
                    }
                }
            }

            // print artist / song and print tree
            else if (command.equals("print"))
            {

                String next = scanner.next();

                // print artist
                if (next.equals("artist"))
                {
                    artistTable.listElements();
                }

                // print song
                else if (next.equals("song"))
                {
                    songTable.listElements();

                }
                else if (next.equals("tree"))
                {
                    System.out.println("Printing tree:");
                    artistTree.dump(); // TODO fix this
                }
            }

            // list
            else if (command.equals("list"))
            {

                String next = scanner.next();

                // list artist
                if (next.equals("artist"))
                {
                    scanner.skip(" ");
                    String artist = scanner.nextLine();

                    if (artistTable.containsElement(artist))
                    {
                        artistTree.listArtist(artist);
                    }
                    else
                    {
                        System.out.println("|" + artist
                            + "| does not exist in the artist database.");
                    }
                }

                // list song
                else if (next.equals("song"))
                {
                    scanner.skip(" ");
                    String name = scanner.nextLine();
                    if (songTable.containsElement(name))
                    {
                        songTree.listSong(name);
                    }
                    else
                    {
                        System.out.println("|" + name
                            + "| does not exist in the artist database.");
                    }
                }
            }
        }

        scanner.close();
    }
}
