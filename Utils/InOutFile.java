package contacts.Utils;

import contacts.Entries.PhoneBookEntry;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class InOutFile {

    private static String filename;

    public InOutFile(String[] args ) {
        filename = getFileNameFromArgs(args);
    }

    public String getFilename() {
        return filename;
    }

    public String getFileNameFromArgs(String[] args) {
        try {
            return args[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("No file specified.");
        }
        return null;
    }

    public void saveToFile(List<PhoneBookEntry> repo) {
        if (Objects.isNull(filename)) {
            System.out.println("File do not exists. Database will be lost");
            return;
        }

        try (
                FileOutputStream fos = new FileOutputStream(filename);
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ) {
            oos.writeObject(repo);

            System.out.printf("Database with %s item(s) was saved to: %s%n", repo.size(), filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public void uploadFromFile(List<PhoneBookEntry> repo) {
        if (Objects.isNull(filename)) {
            System.out.println("File do not exists.");
            return;
        }

        try (
                FileInputStream fis = new FileInputStream(filename);
                BufferedInputStream bis = new BufferedInputStream(fis);
                ObjectInputStream ois = new ObjectInputStream(bis);
        ) {
            List<PhoneBookEntry> repoFromFile = (List<PhoneBookEntry>) ois.readObject();
            repo.addAll(repoFromFile);
            System.out.printf("PhoneBook repository was loaded, added %s item(s).", repoFromFile.size());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
