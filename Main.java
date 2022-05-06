package contacts;

import contacts.Constants.Action;
import contacts.CustomExceptions.ExitProgramException;
import contacts.CustomExceptions.ReturnToMainMenuException;
import contacts.Repositories.PhoneBookRepo;
import contacts.Utils.InOutFile;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneBookRepo phoneBookRepo;

        //Set filename if file exists
        InOutFile inOutFile = new InOutFile(args);
        phoneBookRepo = new PhoneBookRepo(inOutFile);


        //Load database from file to 'in memory' database
        //inOutFile.saveToFile(phoneBookRepo.getEntries());
        inOutFile.uploadFromFile(phoneBookRepo.getEntries());

        try (
                Scanner scanner = new Scanner(System.in);
        ) {
            while (true) {
                actionFromUser(phoneBookRepo, scanner);
            }
        } catch (ExitProgramException e) {
            //save database to file if exists
            inOutFile.saveToFile(phoneBookRepo.getEntries());
            e.printStackTrace();
        }
    }

    private static void actionFromUser(PhoneBookRepo repo, Scanner scanner) {
        Action action = getActionFromUser(scanner);

        switch (action) {
            case ADD:
                repo.add(scanner);
                break;
            case COUNT:
                repo.count();
                break;
            case LIST:
                repo.list(scanner);
                break;
            case SEARCH:
                repo.search(scanner);
                break;
            case EXIT:
                throw new ExitProgramException("Exit");
            default:
                break;
        }
    }

    private static Action getActionFromUser(Scanner scanner) {
        System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
        return Action.valueOf(scanner.nextLine().toUpperCase());
    }


}
