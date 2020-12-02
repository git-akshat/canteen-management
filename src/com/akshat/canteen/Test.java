package com.akshat.canteen;

import java.util.*;

public class Test {
    static Scanner scanner = new Scanner(System.in);
    static int tokenId = 1;

    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();
        Map<Integer, Integer> userOrders = new HashMap<>();

        while (true) {
            System.out.println("Enter username: ");
            String username = scanner.next();

            System.out.println("Enter password: ");
            String pass = scanner.next();

            int userCount = 0;
            for (UserLogin u : UserLogin.users) {
                if (u.getUsername().equals(username.trim()) && u.getUsername().equals("admin") && u.getPassword().equals(pass)) {

                    int ch = 0;
                    do {
                        showListOfItems();
                        showUsers();
                        System.out.println(
                                "1. Add item" +
                                        "\n2. Modify item" +
                                        "\n3. Delete item " +
                                        "\n4. Add money to user account " +
                                        "\n5. Logout" +
                                        "\nEnter your choice: ");
                        ch = scanner.nextInt();

                        switch (ch) {
                            case 1:
                                System.out.println("Enter item: ");
                                String item = scanner.next();
                                int editIndex = 0;
                                for (int j = 0; j < FoodMenu.foodMenus.size(); j++) {
                                    if (FoodMenu.foodMenus.get(j).getItem().equalsIgnoreCase(item.trim())) {
                                        System.out.println("Item already exist. Enter details to modify");
                                        editIndex = j;
                                        break;
                                    }
                                }
                                if (editIndex < FoodMenu.foodMenus.size()) {
                                    FoodMenu.foodMenus.set(editIndex, modify(item));
                                } else {
                                    FoodMenu.foodMenus.add(modify(item));
                                }
                                break;

                            case 2:
                                System.out.println("Enter item: ");
                                String it = scanner.next();
                                int i = 0;
                                for (i = 0; i < FoodMenu.foodMenus.size(); i++) {
                                    if (FoodMenu.foodMenus.get(i).getItem().equalsIgnoreCase(it)) {
                                        FoodMenu.foodMenus.set(i, modify(it));
                                        break;
                                    }
                                }
                                if (i == FoodMenu.foodMenus.size()) {
                                    System.out.println("Item not found");
                                }
                                break;

                            case 3:
                                System.out.println("Enter item: ");
                                String itm = scanner.next();
                                int count = 0;
                                for (FoodMenu f : FoodMenu.foodMenus) {
                                    if (f.getItem().equalsIgnoreCase(itm.trim())) {
                                        System.out.println("Are you sure you want to delete the item? (Y/N)");
                                        String choice = scanner.next();
                                        if (choice.trim().equalsIgnoreCase("Y")) {
                                            FoodMenu.foodMenus.remove(f);
                                        }
                                        break;
                                    }
                                    count++;
                                }
                                if (count == FoodMenu.foodMenus.size()) {
                                    System.out.println("Item not found");
                                }
                                break;

                            case 4:
                                System.out.println("Enter user id: ");
                                int user_id = scanner.nextInt();
                                int index = 0;
                                for (int j = 0; j < User.users.size(); j++) {
                                    if (User.users.get(j).getUser_id() == user_id) {
                                        System.out.println("Enter amount to add");
                                        int amount = scanner.nextInt();

                                        if (amount < 0) {
                                            System.out.println("Amount cannot be negative");
                                            break;
                                        } else {
                                            amount += User.users.get(index).getBalance();
                                            User.users.get(index).setBalance(amount);
                                        }
                                        break;
                                    }
                                }
                                break;

                            case 5:
                                return;

                            default:
                                System.out.println("Invalid choice");
                        }
                    } while (ch != 5);
                }
                else if (u.getUsername().equals(username.trim()) && u.getPassword().equals(pass)) {
                    int ch = 0;
                    do {
                        System.out.println(
                                "1. View Balance" +
                                        "\n2. Order Food" +
                                        "\n3. Logout" +
                                        "\nEnter your choice: ");
                        ch = scanner.nextInt();
                        switch (ch) {
                            case 1:
                                for (int i = 0; i < User.users.size(); i++) {
                                    if (User.users.get(i).getUser_id() == u.getUser_id()) {
                                        System.out.println("Your account balance = " + User.users.get(i).getBalance());
                                        break;
                                    }
                                }
                                break;

                            case 2:
                                showListOfItems();
                                String foodChoice = "Y";
                                int billAmount = 0;
                                int totalAmount = 0;
                                int count = 0;
                                String res = "Token Id: " + tokenId++ + "\n\n";
                                res += "id \t\t item \t\t cost\n";
                                Map<String, Integer> itemQuantity = new HashMap<>();

                                do {
                                    System.out.println("Enter item to order");
                                    String item = scanner.next();
                                    System.out.println("Enter quantity to order: ");
                                    int quantity = scanner.nextInt();
                                    boolean itemFound = false;

                                    for (int i = 0; i < FoodMenu.foodMenus.size(); i++) {
                                        if (FoodMenu.foodMenus.get(i).getItem().equalsIgnoreCase(item)) {
                                            itemFound = true;
                                            if (FoodMenu.foodMenus.get(i).getPrice() * quantity > User.users.get(userCount-1).getBalance()) {
                                                System.out.println("You do not have enough balance to order the food.");
                                                break;
                                            } else if (quantity > FoodMenu.foodMenus.get(i).getMaxPerCust() ||
                                                    quantity > FoodMenu.foodMenus.get(i).getAvailable_count() ||
                                                    (itemQuantity.get(item) != null && itemQuantity.get(item) > FoodMenu.foodMenus.get(i).getMaxPerCust())) {
                                                System.out.println("Error! Max " + FoodMenu.foodMenus.get(i).getMaxPerCust() + " are allowed.");
                                                break;
                                            } else {
                                                FoodMenu.foodMenus.get(i).setAvailable_count(FoodMenu.foodMenus.get(i).getAvailable_count() - quantity);
                                                billAmount = quantity * FoodMenu.foodMenus.get(i).getPrice();
                                                totalAmount += billAmount;
                                                User.users.get(userCount-1).setBalance(User.users.get(userCount-1).getBalance() - billAmount);
                                                if (itemQuantity.get(item) == null) {
                                                    itemQuantity.put(item, quantity);
                                                } else {
                                                    itemQuantity.put(item, itemQuantity.get(item) + quantity);
                                                }
                                                count++;
                                                res += count + " \t\t " + item + " (" + quantity + ")" +"\t\t" + billAmount + "\n";
                                            }
                                            break;
                                        }
                                    }
                                    if(!itemFound) {
                                        System.out.println("Item not found. Please provide valid item");
                                    }
                                    System.out.println("Order more(Y/N)");
                                    foodChoice = scanner.next().trim();


                                } while (foodChoice.equalsIgnoreCase("Y"));

                                res += "  \t\t Total \t\t " + totalAmount;
                                System.out.println(res);


                        }
                    } while (ch != 3);


                }
                userCount++;
            }
            if (userCount >= UserLogin.users.size()) {
                System.out.println("User does not exist. Enter details to signup");
                System.out.println("Enter username: ");
                username = scanner.next();

                System.out.println("Enter password: ");
                pass = scanner.next();

                UserLogin.users.add(new UserLogin(username, pass));
                User.users.add(new User(300, (User.users.size()), new ArrayList<>()));
                showUserLoginDetails();
            }
        }
    }

    private static void showUsers() {
        for (User u : User.users) {
            System.out.println(u);
        }
        System.out.println();
    }

    private static void showUserLoginDetails() {
        for (UserLogin u : UserLogin.users) {
            System.out.println(u);
        }
        System.out.println();
    }

    private static FoodMenu modify(String item) {
        System.out.println("Enter available_count: ");
        int avail_count = scanner.nextInt();

        System.out.println("Enter price: ");
        int price = scanner.nextInt();

        System.out.println("Enter max unit/customer: ");
        String max = scanner.next();

        if (max.equalsIgnoreCase("unlimited")) {
            max = String.valueOf(Integer.MAX_VALUE);
        }

        return new FoodMenu(item, avail_count, price, Integer.parseInt(max));
    }

    private static void showListOfItems() {
        for (FoodMenu f : FoodMenu.foodMenus) {
            System.out.println(f);
        }
        System.out.println();
    }
}
