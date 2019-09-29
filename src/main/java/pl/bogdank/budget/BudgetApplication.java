package pl.bogdank.budget;

import pl.bogdank.budget.DAO.TransactionDao;
import pl.bogdank.budget.model.Transaction;
import pl.bogdank.budget.model.TransactionType;

import java.util.*;

import static pl.bogdank.budget.UserInteractions.*;

public class BudgetApplication {
    static Transaction transaction = null;
    static List<Transaction> transactionList;
    static TransactionDao dao = new TransactionDao();


    public static void main(String[] args) {

        transactionList = new ArrayList<>();

        while (true) {
            switch (displayMenu()) {
                    case "1":
                        transaction = enterTransaction();
                        dao.create(transaction);
                        break;
                    case "2":
                        int id = choiceIdTransaction();
                        transaction = dao.read(id);
                        showTransactions(transaction);
                        Transaction newTransaction = enterTransaction(transaction);
                        dao.update(newTransaction);
                        break;
                    case "3":
                        dao.delete(choiceIdTransaction());
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
