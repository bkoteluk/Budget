package pl.bogdank.budget;

import pl.bogdank.budget.model.Transaction;
import pl.bogdank.budget.model.TransactionType;

import java.time.LocalDate;
import java.util.Scanner;

public class UserInteractions {
    public static String displayMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1: Dodanie nowej transakcji");
        System.out.println("2: Modyfikacj wybranej transakcji");
        System.out.println("3: Usunięcie wybranej transakcji");
        System.out.println("4: Wyświetlenie wszystkich przychodów");
        System.out.println("5: Wyświetlenie wszystkich wydatków");
        System.out.println("0: Wyjście");

        System.out.print("> ");
        return sc.nextLine();
    }

    public static Transaction enterTransaction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj typ transakcji (przychod, wydatek):");
        TransactionType type = TransactionType.valueOf(sc.nextLine());
        System.out.println("Podaj opis transakcji:");
        String description = sc.nextLine();
        System.out.println("Podaj kwotę transakcji:");
        double amount = sc.nextDouble();
        sc.nextLine();
        System.out.println("Podaj datę transakcji:");
        LocalDate localDate = LocalDate.parse(sc.nextLine());
        Transaction transaction = new Transaction();
        transaction.setType(type);
        transaction.setDescription(description);
        transaction.setAmount(amount);
        transaction.setDate(localDate);
        return transaction;
    }

    public static int choiceIdTransaction() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj ID transakcji :");
        int userChoice = sc.nextInt();
        sc.nextLine();
        return userChoice;
    }
    public static TransactionType newType() {
        Scanner sc = new Scanner(System.in);
        String type = sc.nextLine();
        return type.equals("") ? null : TransactionType.valueOf(type);

    }

    public static String newDescription() {
        Scanner sc = new Scanner(System.in);
        String description = sc.nextLine();
        return description.equals("") ? null : description;

    }

    public static double newAmount() {
        Scanner sc = new Scanner(System.in);
        String amount = sc.nextLine();
        return amount.equals("") ? 0 : Double.parseDouble(amount);
    }

    public static LocalDate newDate() {
        Scanner sc = new Scanner(System.in);
        String date = sc.nextLine();
        return date.equals("") ? null : LocalDate.parse(date);
    }

    public static Transaction enterTransaction(Transaction transaction) {
        System.out.println("Podaj nowy typ transakcji (przychod, wydatek lub <ENTER>) :");
        TransactionType type = newType();
        if (type != null)
            transaction.setType(type);


        System.out.println("Podaj nowy opis transakcji (lub <ENTER>) :");
        String description = newDescription();
        if (description != null)
            transaction.setDescription(description);

        System.out.println("Podaj nową kwotę transakcji (lub <ENTER>) :");
        double amount = newAmount();
        if(amount != 0)
            transaction.setAmount(amount);

        System.out.println("Podaj nową datę transakcji (lub <ENTER>) :");
        LocalDate localDate = newDate();
        if(localDate != null) {
            transaction.setDate(localDate);
        }

        return transaction;
    }

}
