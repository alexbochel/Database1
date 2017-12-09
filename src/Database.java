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

                // Neither artist nor name are there already
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
                // at least one is a duplicate
                else
                {
                    // both are there and pair exists
                    if (artistTable.containsElement(artist) && songTable
                        .containsElement(name) && artistTree.doesPairExist(
                            artist, name))
                    {
                        // print out duplicate info
                        System.out.println("|" + artist
                            + "| duplicates a record already in the Artist database.");
                        System.out.println("|" + name
                            + "| duplicates a record already in the Song database.");
                        System.out.println("The KVPair (|" + artist + "|,|"
                            + name + "|),(" + artistTable.getEntry(artist)
                                .getOffset() + "," + songTable.getEntry(name)
                                    .getOffset()
                            + ") duplicates a record already in the tree.");
                        System.out.println("The KVPair (|" + name + "|,|"
                            + artist + "|),(" + songTable.getEntry(name)
                                .getOffset() + "," + artistTable.getEntry(
                                    artist).getOffset()
                            + ") duplicates a record already in the tree.");
                    }
                    // both are there but the pair does not exist
                    else if (artistTable.containsElement(artist) && songTable
                        .containsElement(name) && !artistTree.doesPairExist(
                            artist, name))
                    {
                        // print out duplicate info
                        System.out.println("|" + artist
                            + "| duplicates a record already in the Artist database.");
                        System.out.println("|" + name
                            + "| duplicates a record already in the Song database.");

                        // Create handles
                        Handle artistHandle = artistTable.getEntry(artist);
                        Handle nameHandle = songTable.getEntry(name);

                        // Then create the KVPairs
                        KVPair pairArtist = new KVPair(artistHandle,
                            nameHandle);
                        KVPair pairName = new KVPair(nameHandle, artistHandle);

                        // Add to the KVPairTrees
                        artistTree.insert(pairArtist);
                        songTree.insert(pairName);
                    }
                    // artist exists but song does not
                    else if (artistTable.containsElement(artist) && !songTable
                        .containsElement(name))
                    {
                        // print out info
                        System.out.println("|" + artist
                            + "| duplicates a record already in the Artist database.");

                        // First, create the Handles and add to memManager
                        Handle artistHandle = artistTable.getEntry(artist);

                        Handle nameHandle = new Handle(memManager
                            .getDatabaseSize());
                        memManager.insertItem(name);

                        // Then create the KVPairs
                        KVPair pairArtist = new KVPair(artistHandle,
                            nameHandle);
                        KVPair pairName = new KVPair(nameHandle, artistHandle);

                        // Add the Handles to the HashTables
                        songTable.insert(nameHandle);

                        // Add to the KVPairTrees
                        artistTree.insert(pairArtist);
                        songTree.insert(pairName);

                    }
                    // song exists but artist does not
                    else if (!artistTable.containsElement(artist) && songTable
                        .containsElement(name))
                    {
                        // First, create the Handles and add to memManager
                        Handle artistHandle = new Handle(memManager
                            .getDatabaseSize());
                        memManager.insertItem(artist);

                        Handle nameHandle = songTable.getEntry(name);

                        // Then create the KVPairs
                        KVPair pairArtist = new KVPair(artistHandle,
                            nameHandle);
                        KVPair pairName = new KVPair(nameHandle, artistHandle);

                        // Add the Handles to the HashTables
                        artistTable.insert(artistHandle);

                        // print out duplicate info
                        System.out.println("|" + name
                            + "| duplicates a record already in the Song database.");

                        // Add to the KVPairTrees
                        artistTree.insert(pairArtist);
                        songTree.insert(pairName);

                    }
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
                        if (artistTree.doesPairExist(artist, name))
                        {
                            // Delete from the trees first (since they rely on
                            // the
                            // memManager)
                            artistTree.delete(artist, name);
                            songTree.delete(artist, name);

                            // Delete from the memManager if needed (not in tree
                            // anymore)
                            if (!artistTree.hasArtist(artist))
                            {
                                memManager.deleteItem(artistTable.getEntry(
                                    artist).getOffset());
                            }

                            if (!songTree.hasSong(name))
                            {
                                memManager.deleteItem(songTable.getEntry(name)
                                    .getOffset());
                            }

                            // Delete from the Hash Tables last if needed
                            if (!artistTree.hasArtist(artist) && artistTable
                                .delete(artist) != -1)
                            {
                                System.out.println("|" + artist
                                    + "| is deleted from the artist database.");
                            }

                            if (!songTree.hasSong(name) && songTable.delete(
                                name) != -1)
                            {
                                System.out.println("|" + name
                                    + "| is deleted from the song database.");
                            }
                        }
                        // Both exist but are not pairs
                        else
                        {
                            System.out.println("The KVPair (|" + artist + "|,|"
                                + name + "|) was not found in the database.");
                            System.out.println("The KVPair (|" + name + "|,|"
                                + artist + "|) was not found in the database.");
                        }
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

                        String[] songsByArtist = artistTree
                            .getAllSongsFromArtist(artist);

                        // delete from trees
                        while (artistTree.hasArtist(artist))
                        {
                            artistTree.removeArtist(artist);
                            songTree.removeArtist(artist);
                        }

                        // delete artist from memManager and then artistTable
                        memManager.deleteItem(artistTable.getEntry(artist)
                            .getOffset());
                        artistTable.delete(artist);

                        System.out.println("|" + artist
                            + "| is deleted from the Artist database.");

                        // delete all songs from that artist from memManager and
                        // then songTable
                        for (String str : songsByArtist)
                        {
                            // check if any other artist has a song of the names
                            // of the ones that "artist" had
                            if (!songTree.hasSong(str))
                            {
                                memManager.deleteItem(songTable.getEntry(str)
                                    .getOffset());
                                songTable.delete(str);
                                System.out.println("|" + str
                                    + "| is deleted from the Song database.");
                            }
                        }

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

                        String[] artistsFromSong = songTree
                            .getAllArtistsFromSong(name);

                        // delete from trees
                        while (songTree.hasSong(name))
                        {
                            songTree.removeName(name);
                            artistTree.removeName(name);
                        }

                        // delete name from memManager and then songTable
                        memManager.deleteItem(songTable.getEntry(name)
                            .getOffset());
                        songTable.delete(name);

                        System.out.println("|" + name
                            + "| is deleted from the Song database.");

                        // delete all artists from that song from memManager and
                        // then artistTable
                        for (String str : artistsFromSong)
                        {
                            // check if any other songs have the same artist
                            // as the artists you just deleted
                            if (!artistTree.hasArtist(str))
                            {
                                memManager.deleteItem(artistTable.getEntry(str)
                                    .getOffset());
                                artistTable.delete(str);
                                System.out.println("|" + str
                                    + "| is deleted from the Artist database.");
                            }
                        }

                    }
                    else
                    {
                        System.out.println("|" + name
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
                    System.out.println("Printing artist tree:");
                    artistTree.dump(); // TODO fix this

                    System.out.println("Printing song tree:");
                    songTree.dump();
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
