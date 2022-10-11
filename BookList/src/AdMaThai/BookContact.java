package AdMaThai;

import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BookContact {
    ArrayList<Contact> listOfContacts = new ArrayList<Contact>();

    /**
     * adding new contact object to the main Arraylist
     *
     * @param name  name of contact
     * @param phone phone of the contact
     */
    void addContact(String name, String phone) {
        listOfContacts.add(new Contact(name, phone)); //
    }

    /**
     * this method deletes a contact by name
     *
     * @param name name of contact to delete
     */
    void deleteContactByName(String name) {
        name = name.trim();
        for (Contact contact : listOfContacts) {
            if (Objects.equals(contact.getName(), name)) {
                listOfContacts.remove(contact);
                break;
            }
        }
    }

    /**
     * print all the contact that exist in the ArrayList
     */
    void printAllContact() {
        for (Contact contact : listOfContacts) {
            System.out.println("name of contact - " + contact.getName() + " phone of contact " + contact.getPhone());
        }
    }

    /**
     * @param name name of contact to find.
     *             finds all the contact with the same name
     */
    void findContact(String name) {
        name = name.trim();
        for (Contact contact : listOfContacts) {
            if (Objects.equals(contact.getName(), name)) {
                System.out.println("Heyyy I found " + contact.getName());
            }
        }
    }

    /**
     * ordering the list by phone or name
     *
     * @param isPhone If true the method will return the order by phone attribute else it will order by the name attribute
     */
    void orderContact(boolean isPhone) {
        ArrayList<Contact> copyOfPhoneBook = (ArrayList<Contact>) listOfContacts.clone(); // coping the list to a new one
        if (isPhone) {
            copyOfPhoneBook.sort(new AntherCompare().reversed()); // ordering the list by contact phone from big to small
        } else {
            copyOfPhoneBook.sort(new ClassForSort()); // ordering the list by contact name from small to big
        }
        System.out.println("Here is the order you wished");
        for (Contact contact : copyOfPhoneBook) { // printing the ArrayList with the new order
            System.out.println("name of contact - " + contact.getName() + " phone of contact " + contact.getPhone());
        }
    }

    /**
     * Deleting all the duplicates with the same phone and name
     */
    void deleteDuplicates() {
        HashMap<String, String> duplicates = new HashMap<String, String>(); // Dict for saving all the contact we already found
        for (Contact contact : listOfContacts) {
            if (duplicates.containsValue(duplicates.get(contact.getName()))) {
                listOfContacts.remove(contact); // if we found in the Dict the same contact we delete it from the list
            } else {
                duplicates.put(contact.getName(), contact.getPhone());
            }
        }
    }

    /**
     * Reversing the order of the original list
     */
    void reversedOrder() {
        ArrayList<Contact> copyOfPhoneBook = (ArrayList<Contact>) listOfContacts.clone();
        copyOfPhoneBook.sort(Collections.reverseOrder());
        System.out.println("Here is the order you wished");
        for (Contact contact : copyOfPhoneBook) {
            System.out.println("name of contact - " + contact.getName() + " phone of contact " + contact.getPhone());
        }
    }

    /**
     * Basic method for loading a file to the Arraylist. path should be with the .txt at the end
     *
     * @param path path to the file that needs to be loaded
     */
    void loadingFromFile(String path) {

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split("(?<=\")(.*)(?=\")");
                addContact(data[0], data[1]);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Basic method for writing to a file, path will be decided by the user.
     *
     * @param path path to the file that needs to be loaded
     */
    void writeToFile(String path) {
        try {

            FileWriter myWriter = new FileWriter(path + "RandomName" + LocalDate.now() + ".txt");
            for (Contact contact : listOfContacts) {
                myWriter.write("Name - \"" + contact.getName() + " \" Phone - \"" + contact.getPhone() + "\"");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BookContact phoneList = new BookContact();
        System.out.println("Welcome to the phone book app. please be nice (:");
        System.out.println("1. Enter new contact");
        System.out.println("2. Delete contact");
        System.out.println("3. Print all contacts");
        System.out.println("4. Find contact");
        System.out.println("5. Order contacts by name");
        System.out.println("6. Order contacts by phone");
        System.out.println("7. Delete all duplicate contacts");
        System.out.println("8. Reverse contact order");
        System.out.println("9. Load txt file");
        System.out.println("10. Create txt file");
        System.out.println("11. Exit");
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        int errorCounter = 0;

        do {
            System.out.println("What's your order boy? ");
            userInput = sc.nextLine().trim();
            switch (userInput) {
                case "1":
                    System.out.println("please enter new contact name");
                    String name = sc.nextLine();
                    System.out.println("please enter new contact phone");
                    String phone = sc.nextLine();
                    phoneList.addContact(name, phone);
                    break;
                case "2":
                    System.out.println("please enter the contact name you wish to delete");
                    phoneList.deleteContactByName(sc.nextLine());
                    break;
                case "3":
                    phoneList.printAllContact();
                    break;
                case "4":
                    System.out.println("please enter the contact name you have");
                    phoneList.findContact(sc.nextLine());
                case "5":
                    phoneList.orderContact(false);
                    break;
                case "6":
                    phoneList.orderContact(true);
                    break;
                case "7":
                    phoneList.deleteDuplicates();
                    break;
                case "8":
                    phoneList.reversedOrder();
                    break;
                case "9":
                    System.out.println("---IMPORTANT--- The syn text of the txt should be like the example blow:");
                    System.out.println("Name - \"Here you put the name\" Phone - \"Here you put the phone\"");
                    System.out.println("please enter a path to load contact from a file (with the .txt end)");
                    phoneList.loadingFromFile(sc.nextLine());
                    break;
                case "10":
                    System.out.println("please enter a path to create contact txt file (with the .txt end)");
                    phoneList.writeToFile(sc.nextLine());
                    break;
                case "11":
                    break;
                default:
                    if (errorCounter == 2) {
                        System.out.println("To many Errors... go home....");
                    } else {
                        System.out.println("please enter a valid number");
                    }
                    errorCounter++;

            }

        } while (!userInput.equals("11") && errorCounter < 3);
    }

}
