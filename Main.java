package contacts;

import contacts.Constants.Action;
import contacts.Repositories.PhoneBookRepo;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PhoneBookRepo phoneBookRepo = new PhoneBookRepo();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                actionFromUser(phoneBookRepo, scanner);
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            scanner.close();
        }
    }

    private static void actionFromUser(PhoneBookRepo repo, Scanner scanner) {
        Action action = getActionFromUser(scanner);

        switch (action){
            case ADD:
                repo.add(scanner);
                break;
            case REMOVE:
                repo.delete(scanner);
                break;
            case EDIT:
                repo.edit(scanner);
                break;
            case COUNT:
                repo.count();
                break;
            case INFO:
                repo.info(scanner);
                break;
            case EXIT:
                throw new RuntimeException("Exit");
            default:
                break;
        }
    }

    private static Action getActionFromUser(Scanner scanner){
        System.out.print("\nEnter action (add, remove, edit, count, info, exit): ");
        return Action.valueOf(scanner.nextLine().toUpperCase());
    }


}
