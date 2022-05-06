package contacts.Repositories;


import contacts.CustomExceptions.ReturnToMainMenuException;
import contacts.Entries.PhoneBookEntry;
import contacts.Entries.PhoneBookEntryFactory;
import contacts.Utils.InOutFile;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneBookRepo {
    private final List<PhoneBookEntry> entries;
    private InOutFile inOutFile;


    public PhoneBookRepo() {
        this.entries = new ArrayList<>();
    }

    public PhoneBookRepo(InOutFile inOutFile) {
        this();
        this.inOutFile = inOutFile;
    }


    public List<PhoneBookEntry> getEntries() {
        return entries;
    }


    // -------------------------------  ACTIONS ON REPOSITORY ----------------------------------- //

    public void add(Scanner scanner) {
        PhoneBookEntry phoneBookEntry = PhoneBookEntryFactory.requestNewEntry(scanner);
        entries.add(phoneBookEntry);

        saveToFile();
        System.out.println("The record added.");
    }

    public void list(Scanner scanner) {
        listEntries(entries);

        System.out.print("Enter action ([number], back): ");
        String userInput = scanner.nextLine();

        try {
            int index = Integer.parseInt(userInput) - 1;
            displayEntryDetails(entries.get(index));
            while (true) {
                performActionsOnEntry(entries.get(index), scanner);
            }

        } catch (NumberFormatException | ReturnToMainMenuException e) {
            return;
        }
    }


    public void listEntries(List<PhoneBookEntry> list) {
        if (list.size() == 0) {
            System.out.println("There are no entries!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, list.get(i).getLongName());
            }
        }
    }


    public int count() {
        int size = entries.size();
        if (size == 1) {
            System.out.print("The Phone Book has 1 record.\n");
        } else {
            System.out.printf("The Phone Book has %d records.\n", size);
        }
        return size;
    }


    // -------------------------------  ACTIONS ON SINGLE ENTRY ----------------------------------- //

    public void search(Scanner scanner) {
        List<PhoneBookEntry> results = getSearchResults(scanner);

        while (true) {
            try {
                results = searchInteraction(scanner, results);
            } catch (ReturnToMainMenuException e) {
                return;
            }
        }

    }

    private List<PhoneBookEntry> getSearchResults(Scanner scanner) {
        System.out.print("Enter search query: ");
        String queryWord = scanner.nextLine().trim().toLowerCase();

        List<PhoneBookEntry> result = entries
                .stream()
                .filter(entry -> {

                    if(entry.getLongName().toLowerCase().contains(queryWord.toLowerCase())) {
                        return true;
                    } else return entry.getPhone().contains(queryWord);
                })
                .collect(Collectors.toList());

        System.out.printf("Found %s result(s):%n", result.size());
        listEntries(result);

        return result;
    }

    private List<PhoneBookEntry> searchInteraction(Scanner scanner, List<PhoneBookEntry> searchResults)
            throws ReturnToMainMenuException {

        System.out.print("\n[search] Enter action ([number], back, again, menu): ");
        String userInput = scanner.nextLine();

        try {
            int index = Integer.parseInt(userInput) - 1;
            displayEntryDetails(searchResults.get(index));
            performActionsOnEntry(searchResults.get(index), scanner);
        } catch (NumberFormatException e) {

            switch (userInput) {
                case "back":
                case "menu":
                    throw new ReturnToMainMenuException();
                case "again":
                    searchResults = getSearchResults(scanner);
                    break;
                default:
                    break;
            }
        } catch (IndexOutOfBoundsException ignored){

        }

        return searchResults;
    }

    public void displayEntryDetails(PhoneBookEntry entry) {

        if (Objects.nonNull(entry)) {
            System.out.println(entry);
        } else {
            System.out.println("No records to display!");
        }
    }


    private void performActionsOnEntry(PhoneBookEntry entry, Scanner scanner) throws ReturnToMainMenuException {
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String userInput = scanner.nextLine();

        switch (userInput) {
            case "edit":
                editEntry(scanner, entry);
                displayEntryDetails(entry);
                break;
            case "delete":
                deleteEntry(entry);
                break;
            case "menu":
                throw new ReturnToMainMenuException();
            default:
                break;
        }
    }

    public void editEntry(Scanner scanner, PhoneBookEntry entryToUpdate) {
        PhoneBookEntryFactory.requestChangeEntry(scanner, entryToUpdate);
        saveToFile();
        System.out.println("Saved");
    }

    public void deleteEntry(PhoneBookEntry entry) {

        if (Objects.nonNull(entry)) {
            entries.remove(entry);
            saveToFile();
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove");
        }
    }

    private boolean saveToFile() {
        if (Objects.nonNull(inOutFile.getFilename())) {
            inOutFile.saveToFile(this.getEntries());
            return true;
        } else {
            return false;
        }
    }
}
