package practive_v2.file;

public class ImportExport {
    static ReadFile read = new ReadFile();
    static WriteFile write = new WriteFile();

    public static void importBase(String filename){
        read.readFromFile(filename);
    }

    public static void exportBase(String filename){
        write.writeToFile(filename);
    }
}
