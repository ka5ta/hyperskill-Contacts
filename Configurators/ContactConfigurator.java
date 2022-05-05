package contacts.Configurators;

import contacts.Constants.Gender;
import contacts.Entries.Contact;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class ContactConfigurator extends EntryConfigurator {

    private final Contact contact;
    private final Scanner scanner;

    public ContactConfigurator(Contact contact, Scanner scanner) {
        this.contact = contact;
        this.scanner = scanner;
    }

    @Override
    public void updateAll() {
        setName();
        setSurname();
        setBirthday();
        setGender();
        setPhone();
        setTimeUpdated();
    }

    @Override
    public void updateField() {
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine().toLowerCase().trim();

        switch (field) {
            case "name":
                setName();
                break;
            case "surname":
                setSurname();
                break;
            case "birthday":
                setBirthday();
                break;
            case "gender":
                setGender();
                break;
            case "number":
                setPhone();
                break;
            default:
                throw new RuntimeException("No such option on Contact");
        }
        setTimeUpdated();
    }

    public void setName() {
        System.out.print("Enter the name: ");
        contact.setName(scanner.nextLine());
    }

    public void setSurname() {
        System.out.print("Enter the surname: ");
        contact.setSurname(scanner.nextLine());
    }

    public void setBirthday() {
        System.out.print("Enter the birth date: ");
        contact.setBirthday(verifyBirth(scanner.nextLine()));
    }

    public LocalDate verifyBirth(String inputBirthday) {
        LocalDate birth = dateConverter(inputBirthday);
        if (birth == null) {
            System.out.println("Bad birth date!");
        }
        return birth;
    }

    public LocalDate dateConverter(String input) {
        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void setGender() {
        System.out.print("Enter the gender (M, F): ");
        contact.setGender(verifyGender(scanner.nextLine()));
    }

    public Gender verifyGender(String inputGender) {

        Gender gender = Gender.byLabel(inputGender);

        if (gender == Gender.UNDEFINED) {
            System.out.println("Bad gender!");
        }

        return gender;
    }

    public void setPhone() {
        System.out.print("Enter the number: ");
        contact.setPhone(scanner.nextLine());
    }

    public void setTimeUpdated(){
        contact.setTimeUpdated(LocalDateTime.now());
    }


}
