
import java.util.*;
public class AddressBookingMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        System.out.println("---Welcome To The Address Book Storing System---");
        AddressBookFeatures contact = new AddressBookFeatures();


        int in;
        do {

            System.out.println("---------------------------------------------------------");

            System.out.println("-->>Press 0 to Add AddressBooks<<--" +
                    "\n-->>Press 1 To Edit Contacts<<--" +
                    "\n-->>Press 2 To Delete Contacts<<--" +
                    "\n-->>Press 3 To Delete Address Book<<--" +
                    "\n-->>Press 4 To Display The Address Books<<--" +
                    "\n-->>Press 5 To Search Contacts By City<<--" +
                    "\n-->>Press 6 To See The Person Count According To City<<--" +
                    "\n-->>Press 7 To Sort AddressBook Using Name<<--" +
                    "\n-->>Press 8 To Sort By Choice<<--" +
                    "\n-->>Press 9 To Write To File<<--" +
                    "\n-->>Press 10 To Read From File<<--" +
                    "\n-->>Press 11 To Close The Program<<--");
            System.out.println("----------------------------------------------------------");
            System.out.print("YOUR INPUT --->> ");
            in = sc.nextInt();
            switch (in) {

                case 0: {

                    contact.addNew();
                    break;

                }


                case 1: {

                    System.out.print("Enter The Address Book Name: ");
                    String userInput = sc.next();

                    System.out.print("Enter Your PhoneNumber For Confirmation: ");
                    long phoneNumber = sc.nextLong();
                    boolean updated = contact.editContact(phoneNumber, userInput);

                    if (updated) {
                        System.out.println("---Contact Updated---");
                    } else {
                        System.out.println("---Contact not found---");
                    }
                    break;
                }

                case 2: {

                    System.out.print("Enter The Address Book Name: ");
                    String userInput = sc.next();

                    System.out.print("Enter Your PhoneNumber For Confirmation : ");
                    long phoneNumber = sc.nextLong();
                    boolean updated = contact.deleteByNumber(phoneNumber, userInput);
                    if (updated) {
                        System.out.println("---Contact Deleted---");
                    } else {
                        System.out.println("---Contact not found---");
                    }
                    break;
                }

                case 4: {

                    contact.displayContacts();
                    break;

                }

                case 11: {

                    System.out.println("--->Application Closing || Thank You For Using Address Book Service<---");
                    break;
                }

                case 3: {

                    System.out.print("Enter The Address Book Name: ");
                    String userInput = sc.next();
                    boolean updated = contact.deleteAddressBook(userInput);
                    if (updated) {
                        System.out.println("---Address Book Deleted---");
                    } else {
                        System.out.println("---Address Book Not Found---");
                    }
                    break;


                }

                case 5: {

                    System.out.print("Enter AddressBook Name : ");
                    String userInput = sc.next();
                    System.out.print("Enter The City : ");
                    String user = sc.next();
                    boolean update = contact.searchByCity(userInput, user);
                    if (!update) {
                        System.out.println("---City Not Found---");
                    }
                    break;

                }

                case 6: {

                    System.out.print("Enter The AddressBook Name : ");
                    String input = sc.next();
                    System.out.print("Enter The City : ");
                    String cityInput = sc.next();

                    boolean update = contact.getCountOfContact(input, cityInput);
                    if (!update) {
                        System.out.println("---City Not Found---");
                    }
                    break;

                }

                case 7: {
                    System.out.print("Enter AddressBook Name : ");
                    String userInput = sc.next();
                    contact.sortByName(userInput);
                    break;
                }
                case 8:{
                    System.out.print("Enter The AddressBook Name : ");
                    String userInput = sc.next();
                    contact.sortByChoice(userInput);
                    break;
                }

                case 9:{

                    contact.writeDataToJSONFile();
                    break;

                }
                JSON
                case 10:{

                    contact.readFromJSONFile();
                    break;

                }

                default:
                    System.out.println("Please Enter The Correct Choice");
                    break;
            }

        } while (in != 11);

    }
}
