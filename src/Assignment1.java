import java.util.InputMismatchException;
import java.util.Scanner;

public class Assignment1 {
    public static void main(String[] args) {
        userInterface();
    }

    private static Scanner input = new Scanner(System.in);

    private static void userInterface() {
        int choice = 0;
        do {
            try {
                printIfo();
                System.out.print("What program do you want to run? ");
                choice = input.nextInt();
                ContactAccomplish myContact = new ContactAccomplish();
                switch (choice) {
                    case 1:
                        myContact.loadInput();
                        System.out.println("*Successfully load contacts.txt file into memory*");
                        break;
                    case 2:
                        myContact.viewContact();
                        break;
                    case 3:
                        myContact.addContact();
                        break;
                    case 4:
                        myContact.editContact();
                        break;
                    case 5:
                        myContact.deleteContact();
                        break;
                    case 6:
                        myContact.searchContact();
                        break;
                    case 7:
                        myContact.sortContact();
                        break;
                    case 8:
                        myContact.saveContact();
                        break;
                    case 9:
                        System.out.println("Goodbye");
                        break;
                    default:
                        System.out.println("Try again. (" + "should be integer from 1 to 9)");
                        break;
                }
            }
            catch (InputMismatchException ex){
                System.out.println("Try again. (" + "should be an integer)");
                input.nextLine();
            }
        } while (choice != 9);
    }
    public static void printIfo(){
        System.out.println("\n***************************************");
        System.out.println("1. Load contacts from file");
        System.out.println("2. View all contacts");
        System.out.println("3. Add new contact");
        System.out.println("4. Edit a contact");
        System.out.println("5. Delete a contact");
        System.out.println("6. Search contact list");
        System.out.println("7. Sort contact list");
        System.out.println("8. Save contacts to file");
        System.out.println("9. Quit");
        System.out.println();
    }


}
