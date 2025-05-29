import java.util.*;

class File {
    String name;
    int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

class Collection {
    String name;
    Set<File> files;
    Set<Collection> subCollections; // For follow-up 2

    public Collection(String name) {
        this.name = name;
        this.files = new HashSet<>();
        this.subCollections = new HashSet<>();
    }

    // Add a file to this collection
    public void addFile(File file) {
        files.add(file);
    }

    // Add a sub-collection
    public void addSubCollection(Collection subCollection) {
        subCollections.add(subCollection);
    }

    // Calculate the total size for this collection
    public int getSize() {
        int totalSize = 0;
        Set<File> uniqueFiles = new HashSet<>(files);

        // Add sizes of sub-collections recursively (for follow-up 2)
        for (Collection subCollection : subCollections) {
            uniqueFiles.addAll(subCollection.getAllFiles());
        }

        for (File file : uniqueFiles) {
            totalSize += file.getSize();
        }
        return totalSize;
    }

    // Get all files in this collection, including sub-collections (for follow-up 2)
    public Set<File> getAllFiles() {
        Set<File> allFiles = new HashSet<>(files);
        for (Collection subCollection : subCollections) {
            allFiles.addAll(subCollection.getAllFiles());
        }
        return allFiles;
    }
}

class FileSystem {
    List<Collection> collections;

    public FileSystem() {
        this.collections = new ArrayList<>();
    }

    // Add a collection to the system
    public void addCollection(Collection collection) {
        collections.add(collection);
    }

    // Calculate the total size of all files
    public int getTotalFileSize() {
        Set<File> uniqueFiles = new HashSet<>();
        for (Collection collection : collections) {
            uniqueFiles.addAll(collection.getAllFiles());
        }

        int totalSize = 0;
        for (File file : uniqueFiles) {
            totalSize += file.getSize();
        }
        return totalSize;
    }

    // Get the top N collections by file size
    public List<Collection> getTopNCollections(int N) {
        collections.sort((c1, c2) -> Integer.compare(c2.getSize(), c1.getSize()));
        return collections.subList(0, Math.min(N, collections.size()));
    }
}

public class FileCollectionSystem {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        // Files
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);
        File file3 = new File("file3.txt", 300);

        // Collections
        Collection collection1 = new Collection("collection1");
        collection1.addFile(file1);
        collection1.addFile(file2);

        Collection collection2 = new Collection("collection2");
        collection2.addFile(file2);
        collection2.addFile(file3);

        // Adding a sub-collection (Follow-up 2)
        Collection collection3 = new Collection("collection3");
        collection3.addSubCollection(collection1);
        collection3.addSubCollection(collection2);

        // Adding collections to the file system
        fileSystem.addCollection(collection1);
        fileSystem.addCollection(collection2);
        fileSystem.addCollection(collection3);

        // Total size of all files
        System.out.println("Total size of all files: " + fileSystem.getTotalFileSize());

        // Top N collections by size
        List<Collection> topCollections = fileSystem.getTopNCollections(2);
        System.out.println("Top 2 collections by size:");
        for (Collection collection : topCollections) {
            System.out.println(collection.name + ": " + collection.getSize());
        }
    }
}
