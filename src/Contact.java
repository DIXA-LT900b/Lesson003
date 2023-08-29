import java.util.ArrayList;

public class Contact {

    String firstName, lastName, patronymic;
    ArrayList<String> phoneNumbers;

    Contact(String lastName, String firstName, String patronymic, ArrayList<String> phoneNumbers) {
        this(firstName, lastName, phoneNumbers);
        this.patronymic = patronymic;
    }

    Contact(String firstName, String lastName, ArrayList<String> phoneNumbers) {
        this(firstName, phoneNumbers);
        this.lastName = lastName;
    }

    Contact(String firstName, ArrayList<String> phoneNumbers) {
        this.firstName = firstName;
        this.phoneNumbers = new ArrayList<>();
        this.phoneNumbers.addAll(phoneNumbers);
    }

    public void showContact() {
        System.out.println("    " + makeSolidName());
        for (String phoneNumber : phoneNumbers) {
            System.out.println("        " + phoneNumber);
        }
        System.out.println();
    }

    public boolean updateContactData(String firstName, String lastName, String patronymic, ArrayList<String> phoneNumbers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phoneNumbers = phoneNumbers;
        return true;
    }

    public String makeSolidName() {
        String solidName = "";

        if (this.lastName != null) {
            solidName = this.lastName;
        }
        if (this.firstName != null) {
            solidName = solidName + " " + this.firstName;
        }
        if (this.patronymic != null) {
            solidName = solidName + " " + this.patronymic;
        }
        return solidName;
    }
}