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
<<<<<<< HEAD
    private KVPairTree    artistTree;
    private KVPairTree    songTree;
=======
>>>>>>> fa1f355d55756663b0ad9c515f23584c9345b141
    private Scanner       scanner;


    public Database(int initialHashSize, int blockSize, String name, int size)
    {
        // 1. Initialize MemoryManager.
        memManager = new MemoryManager(size);

        // 2. Initialize two tables and two trees.
        songTable = new HashTable(size, memManager, true);
        artistTable = new HashTable(size, memManager, false);

<<<<<<< HEAD
        // 3. Initialize two KVPairTrees
        artistTree = new KVPairTree(memManager, true);
        songTree = new KVPairTree(memManager, false);

        // 4. Call parse method
=======
>>>>>>> fa1f355d55756663b0ad9c515f23584c9345b141
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
<<<<<<< HEAD
                // Getting the strings for the artist and name of the song
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];

                // First, create the Handles
                Handle artistHandle = new Handle(memManager.getArtistSize());
                Handle nameHandle = new Handle(memManager.getNameSize());

                // Then create the KVPairs
                KVPair pairArtist = new KVPair(artistHandle, nameHandle);
                KVPair pairName = new KVPair(nameHandle, artistHandle);

                // Add the Handles to the HashTables
                artistTable.insert(artistHandle);
                songTable.insert(nameHandle);

                // Add to the MemoryManager
                memManager.insertArtist(artist);
                memManager.insertName(name);

                // Add to the KVPairTrees
                artistTree.insert(pairArtist);
                songTree.insert(pairName);

=======
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];
                
                memManager.insertArtist(artist);
                memManager.insertName(name);
                
>>>>>>> fa1f355d55756663b0ad9c515f23584c9345b141
            }

            // delete
            else if (command.equals("delete"))
            {
<<<<<<< HEAD
                // Getting the strings for the artist and name of the song
                String song = scanner.nextLine();
                String artist = song.split("<SEP>")[0];
                String name = song.split("<SEP>")[1];

                // Delete from the trees first (since they rely on the
                // memManager)
                artistTree.delete(artist, name);
                songTree.delete(artist, name);

                // Delete from the memManager
                memManager.deleteArtist(artistTable.getEntry(artist)
                    .getOffset());
                memManager.deleteName(songTable.getEntry(name).getOffset());

                // Delete from the Hash Tables last
                artistTable.delete(artist);
                songTable.delete(name);
            }

            // remove artist/song
            else if (command.equals("remove"))
            {
                // remove artist
                if (scanner.next().equals("artist"))
                {
                    // Get artist String
                    String artist = scanner.nextLine();

                    // delete artist from memManager and then artistTable
                    memManager.deleteArtist(artistTable.getEntry(artist)
                        .getOffset());
                    artistTable.delete(artist);

                    // delete all songs from that artist from memManager and
                    // then songTable
                    for (String str : artistTree.getAllSongsFromArtist(artist))
                    {
                        memManager.deleteName(songTable.getEntry(str)
                            .getOffset());
                        songTable.delete(str);
                    }

                    // delete from trees
                    artistTree.removeArtist(artist);
                    songTree.removeArtist(artist);

                }

                // remove song (name)
                else if (scanner.next().equals("song"))
                {
                    // Get name String
                    String name = scanner.nextLine();

                    // delete name from memManager and then artistTable
                    memManager.deleteName(artistTable.getEntry(name)
                        .getOffset());
                    artistTable.delete(name);

                    // delete all songs from that artist from memManager and
                    // then songTable
                    for (String str : artistTree.getAllArtistsFromSong(name))
                    {
                        memManager.deleteName(songTable.getEntry(str)
                            .getOffset());
                        songTable.delete(str);
                    }

                    // delete from trees
                    artistTree.removeName(name);
                    songTree.removeName(name);
                }
            }

            // print artist / song and print tree
            else if (command.equals("print"))
            {
                // print artist
                if (scanner.next().equals("artist"))
                {
                    artistTable.listElements();
                }

                // print song
                else if (scanner.next().equals("song"))
                {
                    songTable.listElements();

                }
                else if (scanner.next().equals("tree"))
                {
                    artistTree.dump(); // TODO fix this
                }
            }

            // list
            else if (command.equals("list"))
            {
                // list artist
                if (scanner.next().equals("artist"))
                {
                    String artist = scanner.nextLine();
                    artistTree.listArtist(artist);
                }

                // list song
                else if (scanner.next().equals("song"))
                {
                    String name = scanner.next();
                    songTree.listSong(name);
                }
            }
=======

            }
            
            // remove
            else if (command.equals("remove"))
            {
                
            }
            
            // print artist / song and print tree
            else if (command.equals("print"))
            {
                
            }
            
            // list
            else if (command.equals("list"))
            {
                
            }

>>>>>>> fa1f355d55756663b0ad9c515f23584c9345b141
        }

        scanner.close();
    }
<<<<<<< HEAD
=======


    /**
     * This method calls the specific command for
     */
    private void callCommand(String command)
    {

    }
>>>>>>> fa1f355d55756663b0ad9c515f23584c9345b141
}
