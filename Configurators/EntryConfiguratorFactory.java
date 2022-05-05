package contacts.Configurators;

import contacts.Entries.Contact;
import contacts.Entries.Organization;
import contacts.Entries.PhoneBookEntry;

import java.util.Scanner;

public abstract class EntryConfiguratorFactory {

    public static EntryConfigurator createConfigurator(PhoneBookEntry entry, Scanner scanner){
        if(entry instanceof Contact){
            return new ContactConfigurator( (Contact) entry, scanner);
        } else if (entry instanceof Organization) {
            return new OrganizationConfigurator( (Organization) entry, scanner);
        } else {
            throw new RuntimeException("There is no such Configurator!");
        }
    }



}
