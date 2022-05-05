package contacts.Configurators;

import contacts.Entries.Organization;

import java.time.LocalDateTime;
import java.util.Scanner;

public class OrganizationConfigurator extends EntryConfigurator {

    private Organization organization;
    private final Scanner scanner;

    public OrganizationConfigurator(Organization organization, Scanner scanner) {
        this.scanner = scanner;
        this.organization = organization;
    }

    @Override
    public void updateAll() {
    setName();
    setAddress();
    setPhone();
    setTimeUpdated();
    }

    @Override
    public void updateField() {
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine().toLowerCase().trim();

        switch (field) {
            case "name":
                setName();
                break;
            case "address":
                setAddress();
                break;
            case "number":
                setPhone();
                break;
            default:
                throw new RuntimeException("No such option on Contact");
        }
        setTimeUpdated();
    }

    public void setName(){
        System.out.print("Enter the Organization name: ");
        organization.setName(scanner.nextLine());
    }

    public void setAddress(){
        System.out.print("Enter the address: ");
        organization.setAddress(scanner.nextLine());
    }

    public void setPhone(){
        System.out.print("Enter the number: ");
        organization.setPhone(scanner.nextLine());
    }
    public void setTimeUpdated(){
        organization.setTimeUpdated(LocalDateTime.now());
    }



}
