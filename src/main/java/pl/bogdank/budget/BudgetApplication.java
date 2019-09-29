package pl.bogdank.budget;

import pl.bogdank.budget.dao.TransactionDao;
import pl.bogdank.budget.model.Transaction;
import pl.bogdank.budget.model.TransactionType;

import java.util.*;


public class BudgetApplication {
    static Transaction transaction = null;
    static List<Transaction> transactionList;
    static TransactionDao dao = new TransactionDao();


    public static void main(String[] args) {

        transactionList = new ArrayList<>();
        UserInteractions ui = new UserInteractions();
        while (true) {
            switch (ui.displayMenu()) {
                    case "1":
                        transaction = ui.getNewTransactionInputFromUser();
                        dao.create(transaction);
                        break;
                    case "2":
                        int id = ui.choiceIdTransaction();
                        transaction = dao.read(id);
                        showTransactions(transaction);
                        Transaction alteredTransaction = ui.getNewTransactionInputFromUser(transaction);
                        dao.update(alteredTransaction);
                        break;
                    case "3":
                        dao.delete(ui.choiceIdTransaction());
                        break;
                    case "4":
                        showTransactions("przychod");
                        break;
                    case "5":
                        showTransactions("wydatek");
                        break;
                    case "0":
                        dao.close();
                        return;
                default:
                    System.out.println("Niewłaściwa opcja menu !!");
                }
        }
    }

    private static void showTransactions(String type) {
        transactionList = dao.readAll(TransactionType.valueOf(type));
        for (Transaction transaction : transactionList) {
            System.out.println(transaction.toString());
        }
    }

    private static void showTransactions(Transaction transaction) {
        System.out.println(transaction.toString());
    }
}
