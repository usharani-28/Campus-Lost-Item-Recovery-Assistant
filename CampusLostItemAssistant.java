import java.util.*;

class Item {
    int id;
    String name;
    String location;
    String status; // Lost or Found

    Item(int id, String name, String location, String status) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = status;
    }

    void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Status: " + status);
        System.out.println("----------------------");
    }
}

public class CampusLostItemAssistant {

    static ArrayList<Item> items = new ArrayList<>();
    static HashMap<Integer, Item> itemMap = new HashMap<>();
    static int idCounter = 1;

    public static void addLostItem(Scanner sc) {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        System.out.print("Enter location lost: ");
        String location = sc.nextLine();

        Item item = new Item(idCounter, name, location, "Lost");
        items.add(item);
        itemMap.put(idCounter, item);

        System.out.println("Lost item reported successfully!");
        idCounter++;
    }

    public static void addFoundItem(Scanner sc) {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        System.out.print("Enter location found: ");
        String location = sc.nextLine();

        Item item = new Item(idCounter, name, location, "Found");
        items.add(item);
        itemMap.put(idCounter, item);

        System.out.println("Found item reported successfully!");
        idCounter++;
    }

    public static void searchItem(Scanner sc) {
        System.out.print("Enter item name to search: ");
        String search = sc.nextLine();

        boolean found = false;

        for (Item item : items) {
            if (item.name.equalsIgnoreCase(search)) {
                item.display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Item not found.");
        }
    }

    public static void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items reported.");
            return;
        }

        for (Item item : items) {
            item.display();
        }
    }

    public static void matchItems() {
        System.out.println("Possible Matches:");

        for (Item lost : items) {
            if (lost.status.equals("Lost")) {
                for (Item found : items) {
                    if (found.status.equals("Found") &&
                        lost.name.equalsIgnoreCase(found.name) &&
                        lost.location.equalsIgnoreCase(found.location)) {

                        System.out.println("Match Found!");
                        System.out.println("Lost Item:");
                        lost.display();

                        System.out.println("Found Item:");
                        found.display();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n----- Campus Lost Item Recovery Assistant -----");
            System.out.println("1. Report Lost Item");
            System.out.println("2. Report Found Item");
            System.out.println("3. Search Item");
            System.out.println("4. Display All Items");
            System.out.println("5. Match Lost and Found Items");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addLostItem(sc);
                    break;

                case 2:
                    addFoundItem(sc);
                    break;

                case 3:
                    searchItem(sc);
                    break;

                case 4:
                    displayItems();
                    break;

                case 5:
                    matchItems();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}