import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class extends the BST and handles printing.
 * 
 * @author Alexander James Bochel Brady Engleman
 * @version 12.3.2017
 */
public class KVPairTree extends BinarySearchTree<KVPair>
{

    private MemoryManager memManager;
    private boolean       isArtistTree;


    /**
     * This constructor calls the constructor for the generic BST. 
     * @param manager Memory Maneger object from database. 
     * @param isArtistTree Whether or not this will contain artists. 
     */
    public KVPairTree(MemoryManager manager, boolean isArtistTree)
    {
        super();
        memManager = manager;

        // if isArtistTree is true, that means that artists are the keys and
        // songs are the value
        this.isArtistTree = isArtistTree;
    }


    /**
     * This method inserts a Handle into the tree.
     * 
     * @param pair The KVPair being inserted. 
     * @return boolean Whether or not it inserted. 
     */
    public boolean insert(KVPair pair)
    {
        if (super.find(pair) != null)
        {
            if (super.find(pair).getValue().getOffset() == pair.getValue()
                    .getOffset())
            {
                // there is a duplicate in the tree already

                System.out.print("The KVPair (|");

                if (isArtistTree)
                {
                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|),");
                }
                else
                {
                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|),");

                }

                System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                System.out.print(" duplicates a record already in the tree.");

                return false;
            }
        }

        System.out.print("The KVPair (|");

        if (isArtistTree)
        {
            System.out.print(memManager.getItemString(pair.getKey()
                    .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getItemString(pair.getValue()
                    .getOffset()));
            System.out.print("|),(");
        }
        else
        {
            System.out.print(memManager.getItemString(pair.getKey()
                    .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getItemString(pair.getValue()
                    .getOffset()));
            System.out.print("|),(");
        }

        System.out.print(pair.getKey().getOffset() + "," + pair.getValue()
            .getOffset());

        System.out.println(") is added to the tree.");

        boolean didInsert = super.insert(pair);

        return didInsert;

    }

    /**
     * This method removes a specific artist from the tree and prints
     * the results. 
     * @param artist Being deleted. 
     * @return Whether or not the delete was succesful. 
     */
    public boolean removeArtist(String artist)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Artist matches what we are looking for
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        artist))
                {
                    found = true;

                    // print it to the console
                    System.out.print("The KVPair (|");

                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|)");

                    System.out.println(" is deleted from the tree.");

                    super.delete(pair);

                    return found;
                }
            }

        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Artist matches what we are looking for
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(artist))
                {
                    found = true;

                    // print it out
                    System.out.print("The KVPair (|");

                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|)");

                    System.out.println(" is deleted from the tree.");

                    super.delete(pair);

                    return found;
                }
            }

        }

        // Artist was not in the tree
        if (!found)
        {
            System.out.println("|" + artist + "|"
                    + " does not exist in the artist database.");
            return false;
        }

        return true;
    }

    /**
     * This method removes all items in the tree with a certain name.
     * @param name Song to be removed. 
     * @return Whether or not the remove was succesful. 
     */
    public boolean removeName(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Song matches what we are looking for
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(name))
                {
                    found = true;

                    // print to the console
                    System.out.print("The KVPair (|");

                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|)");

                    System.out.println(" is deleted from the tree.");

                    super.delete(pair);

                    return found;
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Song matches what we are looking for
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        name))
                {
                    found = true;

                    System.out.print("The KVPair (|");

                    System.out.print(memManager.getItemString(pair.getKey()
                            .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getItemString(pair.getValue()
                            .getOffset()));
                    System.out.print("|)");

                    System.out.println(" is deleted from the tree.");

                    super.delete(pair);

                    return found;
                }
            }
        }

        // Song was not in the tree
        if (!found)
        {
            System.out.println(name + " does not exist in the song database.");
            return false;
        }

        return true;
    }

    /**
     * This method gets all songs of a specific artist. 
     * @param artist The artist you want songs for. 
     * @return A list of all the song names. 
     */
    public String[] getAllSongsFromArtist(String artist)
    {
        ArrayList<String> songList = new ArrayList<String>();
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Artist matches what we are looking for
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        artist))
                {
                    songList.add(memManager.getItemString(pair.getValue()
                            .getOffset()));
                }
            }
        }
        else
        {
            System.out.println(
                    "You called the getAllSongsFromArtist method on a "
                            + "songTree! "
                            + "Don't do that!");
        }

        String[] strings = new String[songList.toArray().length];

        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = songList.get(i);
        }

        return strings;
    }

    /**
     * This method gets all artist of a specific song. 
     * @param name The song you want artists for. 
     * @return A list of all the artist names. 
     */
    public String[] getAllArtistsFromSong(String name)
    {
        ArrayList<String> artistList = new ArrayList<String>();
        Iterator<KVPair> iterator = this.iterator();

        if (!isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // Song name matches what we are looking for
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        name))
                {
                    artistList.add(memManager.getItemString(pair.getValue()
                            .getOffset()));
                }
            }
        }
        else
        {
            System.out.println(
                    "You called the getAllArtistFromSong method on an "
                            + "artistTree! Don't do that!");
        }

        String[] strings = new String[artistList.toArray().length];

        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = artistList.get(i);
        }

        return strings;
    }


    /**
     * This method calls the listDumpArtist helper method, which will be called
     * whenever the "list" command is called. It will print out information
     * about the Artist "artist"
     * 
     * @param artist
     *            is the name of the artist to list information about
     */
    public void listArtist(String artist)
    {

        this.listDumpArtist(super.getRoot(), artist);
    }


    /**
     * This method calls the listDumpSong helper method, which will be called
     * whenever the "list" command is called. It will print out information
     * about the Song with the name "name"
     * 
     * @param name
     *            is the name of the song to list information about
     */
    public void listSong(String name)
    {
        this.listDumpSong(super.getRoot(), name);
    }


    /**
     * This method will iterate through the tree to delete the KVPair that
     * contains the Artist "artist" with the song name "name"
     * 
     * @param artist
     *            the name of the artist to delete
     * @param name
     *            the name of the song to delete
     */
    public void delete(String artist, String name)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean foundArtist = false;
        boolean foundSong = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        artist))
                {
                    foundArtist = true;

                    // if song name also matches
                    if (memManager.getItemString(pair.getValue().getOffset())
                            .equals(name))
                    {
                        foundSong = true;

                        // print to the console
                        System.out.print("The KVPair (|");

                        System.out.print(memManager.getItemString(pair.getKey()
                                .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getItemString(pair
                                .getValue().getOffset()));
                        System.out.print("|)");

                        System.out.println(" is deleted from the tree.");
                        super.delete(pair);

                        super.delete(pair);
                    }
                }
            }

        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(artist))
                {
                    foundArtist = true;

                    // if song name also matches
                    if (memManager.getItemString(pair.getKey().getOffset())
                            .equals(name))
                    {
                        foundSong = true;

                        // print to the console
                        System.out.print("The KVPair (|");

                        System.out.print(memManager.getItemString(pair.getKey()
                                .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getItemString(pair
                                .getValue().getOffset()));
                        System.out.print("|)");

                        System.out.println(" is deleted from the tree.");

                        super.delete(pair);
                    }
                }
            }
        }

        if (!foundArtist || !foundSong)
        {
            if (!foundArtist)
            {
                System.out.println(artist
                        + " does not exist in the artist database.");
            }
            else
            {
                System.out.println(name
                        + " does not exist in the song database.");
            }
        }
    }


    /**
     * This method calls the super's dump method, which does an in order
     * traversal through the tree and lists information about each KVPair
     */
    public void printTree()
    {
        super.dump();
    }


    /**
     * This method will do an in-order traversal through the tree and print out
     * artists that match the artist we are trying to list
     * 
     * @param treeRoot
     *            the root of the tree, for starting the traversal
     * @param artist
     *            the name of the artist we are looking for
     */
    private void listDumpArtist(BinaryNode<KVPair> treeRoot, String artist)
    {
        if (treeRoot == null)
        {
            System.out.println("|" + artist + "|"
                    + " does not exist in the artist database.");
            return;
        }

        // recursively traverse left tree:
        if (treeRoot.getLeft() != null)
        {
            listDumpArtist(treeRoot.getLeft(), artist);
        }

        // If artist in the KVPair matches the artist we are trying to list
        if (memManager.getItemString(((KVPair)treeRoot.getElement()).getKey()
                .getOffset()).equals(artist))
        {
            System.out.println("|" + memManager.getItemString(((KVPair)treeRoot
                    .getElement()).getValue().getOffset()) + "|");
        }

        // recursively traverse right tree:
        if (treeRoot.getRight() != null)
        {
            listDumpArtist(treeRoot.getRight(), artist);
        }

    }


    /**
     * This method will do an in-order traversal through the tree and print out
     * songs that match the song we are trying to list
     * 
     * @param treeRoot
     *            the root of the tree, for starting the traversal
     * @param name
     *            the name of the song we are looking for
     */
    private void listDumpSong(BinaryNode<KVPair> treeRoot, String name)
    {
        if (treeRoot == null)
        {
            System.out.println(name + " does not exist in the song database.");
            return;
        }

        // recursively traverse left tree:
        if (treeRoot.getLeft() != null)
        {
            listDumpSong(treeRoot.getLeft(), name);
        }

        // If song in the KVPair matches the song we are trying to list
        if (memManager.getItemString(((KVPair)treeRoot.getElement()).getKey()
                .getOffset()).equals(name))
        {
            System.out.println("|" + memManager.getItemString(((KVPair)treeRoot
                    .getElement()).getValue().getOffset()) + "|");

        }

        // recursively traverse right tree:
        if (treeRoot.getRight() != null)
        {
            listDumpSong(treeRoot.getRight(), name);
        }
    }


    /**
     * Checks to see if a particular pair of artist and name exists in the tree.
     * 
     * @param artist
     *            is the name of the artist
     * @param name
     *            is the song name
     * @return true if the pair exists, false otherwise
     */
    public boolean doesPairExist(String artist, String name)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the artist and name are the same
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        artist) && memManager.getItemString(pair.getValue()
                                .getOffset()).equals(name))
                {
                    return true;
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the artist and name are the same
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(artist) && memManager.getItemString(pair.
                                getKey().getOffset()).equals(name))
                {
                    return true;
                }
            }
        }

        return found;
    }

    /**
     * This method checks to see if the artist exists in the database. 
     * @param artist Artist we are looking for. 
     * @return Whether or not the table has a certain artist. 
     */
    public boolean hasArtist(String artist)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the artist is the same
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        artist))
                {
                    return true;
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the artist is the same
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(artist))
                {
                    return true;
                }
            }
        }

        return found;
    }

    /**
     * This method checks to see if the song exists in the database. 
     * @param song Song we are looking for. 
     * @return Whether or not the table has a certain song. 
     */
    public boolean hasSong(String song)
    {
        Iterator<KVPair> iterator = this.iterator();
        boolean found = false;

        if (!isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the song name is the same
                if (memManager.getItemString(pair.getKey().getOffset()).equals(
                        song))
                {
                    return true;
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // test if the song name is the same
                if (memManager.getItemString(pair.getValue().getOffset())
                        .equals(song))
                {
                    return true;
                }
            }
        }

        return found;
    }

}
