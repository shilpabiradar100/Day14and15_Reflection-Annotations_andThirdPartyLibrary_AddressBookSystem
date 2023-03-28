

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
public class AddressBookFeatures {
    List<ContactStoring> info = new LinkedList<>();

    HashMap<String, ArrayList> multipleAddressBook = new HashMap<>();

    ContactStoring contact;


    Scanner sc = new Scanner(System.in);

    public void addNew() {

        ArrayList<ContactStoring> temp = new ArrayList<>();

        System.out.print("Enter AddressBook Name : ");
        String input2 = sc.next();

        if (multipleAddressBook.containsKey(input2)) {
            System.out.println("-----Address Book Already Exists-----");
        } else {

            System.out.print("How Many Contacts You Want To Store? -->> ");
            int userInput = sc.nextInt();
            for (int i = 0; i < userInput; i++) {

                System.out.print("Enter First Name : ");
                String firstName = sc.next();

                boolean update = temp.stream().anyMatch(x -> x.getFirstName().equalsIgnoreCase(firstName));
                if (update) {
                    System.out.println("---Contact Already Exists---");
                } else {

                    System.out.print("Enter The Last Name : ");
                    String lastName = sc.next();

                    System.out.print("Enter Your City : ");
                    String city = sc.next();

                    System.out.print("Enter Your State : ");
                    String state = sc.next();

                    System.out.print("Enter Pin Number : ");
                    int zip = sc.nextInt();

                    System.out.print("Enter Your Phone Number : ");
                    long phoneNumber = sc.nextLong();

                    System.out.print("Enter Your Email Address : ");
                    String email = sc.next();


                    contact = new ContactStoring(firstName, lastName, city, state, zip, phoneNumber, email);

                    info.add(contact);

                    temp.add(contact);
                }

            }

        }

        multipleAddressBook.put(input2, temp);

    }


    public boolean editContact(long phoneNumber, String addressBookName) {

        try {
            ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);

            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) != null && temp.get(i).getPhoneNumber() == phoneNumber) {

                    System.out.print("Enter First Name : ");
                    String firstName = sc.next();

                    System.out.print("Enter last Name : ");
                    String lastName = sc.next();

                    System.out.print("Enter Your City : ");
                    String city = sc.next();

                    System.out.print("Enter Your State : ");
                    String state = sc.next();

                    System.out.print("Enter Your Pin Number : ");
                    int zip = sc.nextInt();

                    System.out.print("Enter Your Email : ");
                    String email = sc.next();

                    info.get(i).setFirstName(firstName);
                    info.get(i).setLastName(lastName);
                    info.get(i).setCity(city);
                    info.get(i).setState(state);
                    info.get(i).setZip(zip);
                    info.get(i).setEmail(email);

                    return true;

                }

            }

        } catch (Exception e) {

            System.out.println("---Address Book Not Found---");

        }

        return false;

    }

    public boolean deleteByNumber(long phoneNumber, String addressBookName) {

        try {

            ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);

            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i) != null && temp.get(i).getPhoneNumber() == phoneNumber) {
                    temp.remove(i);
                    return true;
                }
            }

        } catch (Exception e) {

            System.out.println("---Address Book Not Found---");
        }


        return false;
    }

    public boolean deleteAddressBook(String addressBookKey) {

        ArrayList temp = multipleAddressBook.remove(addressBookKey);
        if (temp == null) {
            return false;
        } else {
            return true;
        }

    }

    public boolean searchByCity(String addressBookName, String city) {

        try {
            ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);
            temp.stream().filter(a -> a.getCity().equalsIgnoreCase(city)).forEach(x -> System.out.println(x.getFirstName() + "\n"));
            return true;
        } catch (Exception e) {
            System.out.println("---Address Book Not Found---");
        }
        return false;

    }

    public boolean getCountOfContact(String addressBookName, String city) {

        try {
            ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);
            long count = temp.stream().filter(x -> x.getCity().equalsIgnoreCase(city)).collect(Collectors.counting());
            System.out.println("Persons Available in The City is : " + count);
            return true;
        } catch (Exception e) {
            System.out.println("---AddressBook Not Found---");
        }
        return false;
    }

    public void sortByName(String addressBookName){

        ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);
        List list = temp.stream().sorted((f,s)-> f.getFirstName().compareTo(s.getFirstName())).collect(Collectors.toList());
        System.out.println(list);

    }

    public void sortByChoice(String addressBookName){

        ArrayList<ContactStoring> temp = multipleAddressBook.get(addressBookName);
        System.out.println("Press 1 to Sort By City\nPress 2 to Sort By State\nPress 3 to Sort By ZIP");
        System.out.print("Your Choice : ");
        int input = sc.nextInt();

        switch (input){
            case 1:{
                List cityList = temp.stream().sorted((f,s)-> f.getCity().compareTo(s.getCity())).collect(Collectors.toList());
                System.out.println(cityList);
                break;
            }
            case 2:{
                List cityList = temp.stream().sorted((f,s)-> f.getState().compareTo(s.getState())).collect(Collectors.toList());
                System.out.println(cityList);
                break;
            }
            case 3:{
                List cityList = temp.stream().sorted((f,s)-> Long.valueOf(f.getZip()).compareTo(Long.valueOf(s.getZip()))).collect(Collectors.toList());
                System.out.println(cityList);
                break;
            }

        }

    }

    public ArrayList<ContactStoring> getContact(){

        return new ArrayList<ContactStoring>(info);

    }

    public void writeDataToCSVFile() {

        try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\asaha\\Desktop\\Java_Fellowship_242\\Address_Book_CSV_And_JSON\\src\\main\\resources\\csvOutput.csv"))) {
            StatefulBeanToCsvBuilder<ContactStoring> contactsStatefulBeanToCsvBuilder = new StatefulBeanToCsvBuilder<>(writer);
            StatefulBeanToCsv<ContactStoring> contactStatefulBeanToCsv = contactsStatefulBeanToCsvBuilder.build();
            contactStatefulBeanToCsv.write(getContact());
            System.out.println("---File Stored In CSV Format---");

        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    public void readFromCSVFile() {
        try (Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\asaha\\Desktop\\Java_Fellowship_242\\Address_Book_CSV_And_JSON\\src\\main\\resources\\csvOutput.csv"));

             CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build()) {
            String[] record;
            while ((record = csvReader.readNext()) != null) {
                System.out.println("First name : " + record[2]);
                System.out.println("Last name : " + record[3]);
                System.out.println("Email : " + record[1]);
                System.out.println("Phone number : " + record[4]);
                System.out.println("City : " + record[0]);
                System.out.println("State : " + record[5]);
                System.out.println("Zip : " + record[6]);
                System.out.println("----------------------------------------");
            }
            System.out.println("Successfully read from CSV file");
            System.out.println();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void displayContacts() {

        for (String key : multipleAddressBook.keySet()) {
            System.out.println(key + " : " + multipleAddressBook.get(key));
        }

    }

}
