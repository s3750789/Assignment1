import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

class ContactAccomplish {
    private static ArrayList<Contacts> contactsArr = new ArrayList<>();
    private Scanner typeIn = new Scanner(System.in);
    private java.io.File file = new java.io.File("contacts.txt");

    void loadInput() {
        if (!file.exists()){
            System.out.println(file + "file does not exit");
            System.exit(1);
        }
        try {
            Scanner input = new Scanner(file);                  //Get the input
            input.useDelimiter(";\\s?|\r?\n|\r");                   //Input will cut reading and save into variables once read [;] or "\n"
            while (input.hasNextLine()) {                       //Run through all lines in file
                String readName = input.next();                 //Find the first element is name
                String readPhoneNumber = input.next();          //Element is phone number
                String readEmail = input.next();                //Element is email address
                String readAddress = input.next();              //Element is address
                Contacts object = new Contacts(readName,readPhoneNumber,readEmail,readAddress);
                if (checkDuplication(object))
                    ContactAccomplish.contactsArr.add(object);     //Create an object contains 4 elements and add into arraylist
            }
            input.close();          //close the scanner
        }
        catch (IOException ex1 ){
            System.out.println("I/O error occurred " + ex1);
        }
    }

    void viewContact(){
        int i = 1;
        System.out.println("VIEW CONTACT");
        System.out.printf("%-10s%-30s%-30s%-40s%-60s\n","Number","Name", "Phone Number", "Email Address", "Home Address");
        for (Contacts person : contactsArr){
            System.out.print(String.format("%-10s",i)+person.toString());
            i++;
        }
    }

    void addContact() {
        Contacts object = new Contacts();
        object.setName();
        object.setPhoneNumber();
        object.setEmail();
        object.setAddress();
        if (checkDuplication(object)){
            confirmPutIn(object);
        }
        else
            System.out.println("Duplicate contact");
    }

    void editContact() {
        System.out.println("EDIT CONTACT: ");
        Contacts object = contactsArr.get(chooseLine()-1);
        System.out.println("What field do you want to edit? ");
        int selection = chooseField();
        switch (selection){
            case 1:
                object.setName();
                break;
            case 2:
                object.setPhoneNumber();
                break;
            case 3:
                object.setEmail();
                break;
            case 4:
                object.setAddress();
                break;
        }
        if (checkDuplication(object)) {
            confirmPutIn(object);
        }
        else
            System.out.println("Duplicate contact");
    }

    void deleteContact () {
        System.out.println("DELETE CONTACT: ");
        contactsArr.remove(chooseLine() - 1);
    }

    void searchContact () {
        System.out.println("SEARCH CONTACT: ");
        System.out.print("What do you want to search: ");
        String searchStr = typeIn.next();
        int i = 1, count = 0;
        for (Contacts person : contactsArr) {
            if (person.toString().contains(searchStr)) {
                System.out.print(String.format("%-10s", i) + person.toString());
                count ++;
            }
            i++;
        }
        System.out.println("There are " + count + " research results.");
    }

    void sortContact() {
        System.out.println("SORT CONTACT: ");
        System.out.print("What field do you want to sort? ");
        int selection = chooseField();
        switch (selection) {
            case 1:
                contactsArr.sort(new SortName());
                break;
            case 2:
                contactsArr.sort(new SortPhone());
                break;
            case 3:
                contactsArr.sort(new SortEmail());
                break;
            case 4:
                contactsArr.sort(new SortAddress());
                break;
        }
    }

    public void saveContact() {
        try {
            PrintWriter writer = new PrintWriter("contacts.txt");
            writer.print("");
            writer.print("hello");
            writer.close();
        }
        catch (IOException ex) {
            System.out.println("I/O error occur ");
        }
    }

    private int chooseField() {
        int selection = 0;
        do {
            try {
                System.out.println("1.Name\n2.Phone\n3.Email\n4.Address");
                System.out.print("Enter your input here (should be a number from 1-4): ");
                selection = typeIn.nextInt();
                if (selection < 1 || selection > 4)
                    System.out.println("Wrong input: should be 1-4");
            }
            catch (InputMismatchException ex){
                System.out.println("Wrong input (should be a number 1-4)");
            }
        } while(selection < 1 || selection > 4);
        return selection;
    }

    private int chooseLine () {
        int line = 0;
        do {
            try {
                System.out.print("Please choose a line: ");
                line = typeIn.nextInt();
                Contacts object = contactsArr.get(line-1);
                System.out.print("LINE "+ (line) + ": \n" );
                System.out.println(object.toString());
            }
            catch (InputMismatchException ex){
                System.out.println("Wrong input : should be a number. Try again");
            }
            catch (IndexOutOfBoundsException ex1){
                System.out.println("Wrong input: should an integer be greater than 1 and smaller than "+(contactsArr.size()+1)+". Try again");
            }
        } while (line < 1 || line > contactsArr.size() + 1);
        return line;
    }

    private boolean checkDuplication(Contacts contacts) {
        for (Contacts person : contactsArr){
            if (contacts.toString().equals(person.toString())){
                return false;
            }
        }
        return true;
    }

    private void confirmPutIn(Contacts contacts) {
        System.out.print(contacts.toString());
        System.out.println("1. Yes\n2. No");
        int selection = 0;
        do {
            try {
                System.out.print("Do you want to put into contact ? ");
                selection = typeIn.nextInt();
                if (selection != 1 && selection != 2)
                    System.out.println("Wrong input: should be 1 or 2. Try again");
            }
            catch (InputMismatchException ex) {
                System.out.println("Wrong input should be an integer 1 or 2. Try again");
            }
        } while (selection != 1 && selection != 2);
        if (selection == 1)
            contactsArr.add(contacts);
    }
}
