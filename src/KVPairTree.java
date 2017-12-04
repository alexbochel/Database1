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
     * This constructor calls the BST constructor.
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
     * @param handle
     *            The handle being inserted.
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
                    System.out.print(memManager.getArtistString(pair.getKey()
                        .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getSongString(pair.getValue()
                        .getOffset()));
                    System.out.print("|),");
                }
                else
                {
                    System.out.print(memManager.getSongString(pair.getKey()
                        .getOffset()));
                    System.out.print("|,|");
                    System.out.print(memManager.getArtistString(pair.getValue()
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
            System.out.print(memManager.getArtistString(pair.getKey()
                .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getSongString(pair.getValue()
                .getOffset()));
            System.out.print("|),");
        }
        else
        {
            System.out.print(memManager.getSongString(pair.getKey()
                .getOffset()));
            System.out.print("|,|");
            System.out.print(memManager.getArtistString(pair.getValue()
                .getOffset()));
            System.out.print("|),");
        }

        System.out.print(pair.getKey().getOffset() + "," + pair.getValue()
            .getOffset());

        System.out.print(" is added to the tree.");
        return super.insert(pair);
    }


    public void removeArtist(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
    }


    public void removeName(String name)
    {
        Iterator<KVPair> iterator = this.iterator();

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(name))
                {
                    System.out.print("The KVPair (|");

                    if (isArtistTree)
                    {
                        System.out.print(memManager.getArtistString(pair
                            .getKey().getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getSongString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }
                    else
                    {
                        System.out.print(memManager.getSongString(pair.getKey()
                            .getOffset()));
                        System.out.print("|,|");
                        System.out.print(memManager.getArtistString(pair
                            .getValue().getOffset()));
                        System.out.print("|),");
                    }

                    System.out.print(pair.getKey().getOffset() + "," + pair
                        .getValue().getOffset());

                    System.out.print(" is deleted from the tree.");
                    super.delete(pair);
                }
            }
        }
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
        boolean found = false;

        if (isArtistTree)
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getArtistString(pair.getKey().getOffset())
                    .equals(artist))
                {
                    // if song name also matches
                    if (memManager.getSongString(pair.getValue().getOffset())
                        .equals(name))
                    {
                        found = true;
                        // TODO print it out
                        super.delete(pair);
                    }
                }
            }

            if (!found)
            {
                // TODO print out that it wasn't there
            }
        }
        else
        {
            while (iterator.hasNext())
            {
                KVPair pair = iterator.next();

                // if artists are the same
                if (memManager.getArtistString(pair.getValue().getOffset())
                    .equals(artist))
                {
                    // if song name also matches
                    if (memManager.getSongString(pair.getKey().getOffset())
                        .equals(name))
                    {
                        found = true;
                        // TODO print it out
                        super.delete(pair);
                    }
                }
            }

            if (!found)
            {
                // TODO print out that it wasn't there
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
            return;
        }

        // recursively traverse left tree:
        if (treeRoot.getLeft() != null)
        {
            listDumpArtist(treeRoot.getLeft(), artist);
        }

        if (isArtistTree)
        {
            // If artist in the KVPair matches the artist we are trying to list
            if (memManager.getArtistString(((KVPair)treeRoot.getElement())
                .getKey().getOffset()).equals(artist))
            {
                // TODO print it out
            }
        }
        else
        {
            // If artist in the KVPair matches the artist we are trying to list
            if (memManager.getArtistString(((KVPair)treeRoot.getElement())
                .getValue().getOffset()).equals(artist))
            {
                // TODO print it out
            }
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
            return;
        }

        // recursively traverse left tree:
        if (treeRoot.getLeft() != null)
        {
            listDumpSong(treeRoot.getLeft(), name);
        }

        if (isArtistTree)
        {
            // If artist in the KVPair matches the artist we are trying to list
            if (memManager.getSongString(((KVPair)treeRoot.getElement())
                .getValue().getOffset()).equals(name))
            {
                // TODO print it out
            }
        }
        else
        {
            // If artist in the KVPair matches the artist we are trying to list
            if (memManager.getSongString(((KVPair)treeRoot.getElement())
                .getKey().getOffset()).equals(name))
            {
                // TODO print it out
            }
        }

        // recursively traverse right tree:
        if (treeRoot.getRight() != null)
        {
            listDumpSong(treeRoot.getRight(), name);
        }
    }

}
