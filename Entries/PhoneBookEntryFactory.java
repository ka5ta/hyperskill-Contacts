package contacts.Entries;

import contacts.Configurators.EntryConfigurator;
import contacts.Configurators.EntryConfiguratorFactory;
import contacts.Repositories.PhoneBookRepo;

import java.util.Objects;
import java.util.Scanner;

public abstract class PhoneBookEntryFactory {


    public static PhoneBookEntry requestNewEntry(Scanner scanner) {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase().trim();
        PhoneBookEntry newEntry = createEntry(type);

        EntryConfigurator configurator = EntryConfiguratorFactory.createConfigurator(newEntry, scanner);
        configurator.updateAll();

        return newEntry;
    }

    private static PhoneBookEntry createEntry(String answer) {
        if("person".equals(answer)){
            return new Contact();

        } else if ("organization".equals(answer)) {
            return new Organization();

        } else {
            throw new RuntimeException("There is no such type!");
        }
    }

    public static void requestChangeEntry(Scanner scanner, PhoneBookRepo phoneBookRepo) {

        PhoneBookEntry entry = phoneBookRepo.selectEntry(scanner);

        if(Objects.nonNull(entry)){
            EntryConfigurator configurator = EntryConfiguratorFactory.createConfigurator(entry, scanner);
            configurator.updateField();
        } else {
            System.out.println("No records to edit!");
        }

    }
}
