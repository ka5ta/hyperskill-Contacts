package contacts.Repositories;


import contacts.Entries.PhoneBookEntry;
import contacts.Entries.PhoneBookEntryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class PhoneBookRepo {
    private final List<PhoneBookEntry> entries;


    public PhoneBookRepo() {
        this.entries = new ArrayList<>();
    }

    public void add(Scanner scanner) {
        PhoneBookEntry phoneBookEntry = PhoneBookEntryFactory.requestNewEntry(scanner);
        entries.add(phoneBookEntry);
        System.out.println("The record added.");
    }

    public void edit(Scanner scanner) {
        PhoneBookEntryFactory.requestChangeEntry(scanner, this);
        System.out.println("The record updated!");
    }

    public void listEntries() {
        if (entries.size() == 0) {
            System.out.println("There are no entries!");
            return;
        }

        for (int i = 0; i < entries.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, entries.get(i).getLongName());
        }
    }

    public int count() {
        int size = entries.size();
        System.out.printf("The Phone Book has %d records.\n", size);
        return size;
    }

    public void info(Scanner scanner) {

        PhoneBookEntry entry = selectEntry(scanner);

        if(Objects.nonNull(entry)){
            System.out.println(entry);
        } else {
            System.out.println("No records to display!");
        }
    }

    public void delete(Scanner scanner) {
        PhoneBookEntry entry = selectEntry(scanner);

        if (Objects.nonNull(entry)) {
            entries.remove(entry);
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove");
        }
    }


    public PhoneBookEntry selectEntry(Scanner scanner) {

        if (entries.size() > 0) {
            listEntries();

            System.out.print("Select a record: ");
            int index = Integer.parseInt(scanner.nextLine()) - 1;

            if (inRange(index)) {
                return entries.get(index);
            }
        }

        return null;
    }

    public boolean inRange(int index) {
        if (entries.size() > index) {
            return true;
        } else {
            throw new ArrayIndexOutOfBoundsException("Entry number do not exist");
        }
    }

}
