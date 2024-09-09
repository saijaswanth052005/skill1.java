import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

// Step 1: Define the Contact class
class Contact {
    private String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Override equals and hashCode to ensure unique contacts based on phone number
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }

    @Override
    public String toString() {
        return "Contact{" + "name='" + name + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", email='" + email + '\'' + '}';
    }
}

// Contact Management System using Sets and Maps
public class ContactManager {
    // Step 2: Use HashSet to store unique contacts
    private Set<Contact> contactSet = new HashSet<>();

    // Step 3: Use HashMap to map phone numbers to contacts for efficient access
    private Map<String, Contact> contactMap = new HashMap<>();

    // Step 4: CRUD Operations

    // Add Contact
    public void addContact(Contact contact) {
        if (contactSet.add(contact)) {  // Add to Set (ensures uniqueness)
            contactMap.put(contact.getPhoneNumber(), contact);  // Add to Map
            System.out.println("Contact added: " + contact);
        } else {
            System.out.println("Contact already exists with phone number: " + contact.getPhoneNumber());
        }
    }

    // Update Contact
    public void updateContact(String phoneNumber, String newName, String newEmail) {
        Contact contact = contactMap.get(phoneNumber);
        if (contact != null) {
            // Remove the old contact from the set
            contactSet.remove(contact);
            // Create a new updated contact
            contact = new Contact(newName, phoneNumber, newEmail);
            // Add the updated contact to the set and map
            contactSet.add(contact);
            contactMap.put(phoneNumber, contact);
            System.out.println("Contact updated: " + contact);
        } else {
            System.out.println("No contact found with phone number: " + phoneNumber);
        }
    }

    // Remove Contact
    public void removeContact(String phoneNumber) {
        Contact contact = contactMap.remove(phoneNumber);
        if (contact != null) {
            contactSet.remove(contact);  // Remove from set
            System.out.println("Contact removed: " + contact);
        } else {
            System.out.println("No contact found with phone number: " + phoneNumber);
        }
    }

    // Search Contact
    public Contact searchContact(String phoneNumber) {
        return contactMap.get(phoneNumber);
    }

    // Step 5: Display All Contacts
    public void displayContacts() {
        System.out.println("All Contacts:");
        for (Contact contact : contactSet) {
            System.out.println(contact);
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        ContactManager manager = new ContactManager();

        // Create sample contacts
        Contact contact1 = new Contact("Alice", "1234567890", "alice@example.com");
        Contact contact2 = new Contact("Bob", "0987654321", "bob@example.com");
        Contact contact3 = new Contact("Charlie", "1122334455", "charlie@example.com");

        // Add Contacts
        manager.addContact(contact1);
        manager.addContact(contact2);
        manager.addContact(contact3);

        // Display Contacts
        manager.displayContacts();

        // Update Contact
        manager.updateContact("1234567890", "Alice Smith", "alice_new@example.com");

        // Display Contacts After Update
        manager.displayContacts();

        // Search Contact
        Contact foundContact = manager.searchContact("0987654321");
        System.out.println("Found Contact: " + foundContact);

        // Remove Contact
        manager.removeContact("1122334455");

        // Display Contacts After Removal
        manager.displayContacts();
    }
}
