package vedant.tiwari.expense_management.room_database.Repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import vedant.tiwari.expense_management.room_database.entity.Expense;
import vedant.tiwari.expense_management.room_database.main.RoomDatabase;

public class ExpenseRepo {

    private RoomDatabase roomDatabase;

    public RoomDatabase initializeRepo(Context context) {
        roomDatabase = RoomDatabase.getDatabaseInstance(context);
        return roomDatabase;
    }

    public LiveData<List<Expense>> getAllExpenses(Context context) {
        roomDatabase = initializeRepo(context);
        return roomDatabase.expenseDao().getAllExpense();
    }

    public void insertExpense(Context context, Expense expense) {
        roomDatabase = initializeRepo(context);
        new Thread(() -> roomDatabase.expenseDao().insertExpense(expense)).start();
    }

    public void deleteExpense(Context context, int id) {
        roomDatabase = initializeRepo(context);
        new Thread(() -> roomDatabase.expenseDao().deleteExpense(id)).start();
    }

    public void updateExpense(Context context, Expense expense) {
        roomDatabase = initializeRepo(context);
        new Thread(() -> roomDatabase.expenseDao().updateExpense(expense)).start();
    }

    public LiveData<List<Expense>> getExpensesByCategory(Context context, String category) {
        roomDatabase = initializeRepo(context);
        return roomDatabase.expenseDao().getExpensesByCategory(category);
    }

    public LiveData<List<Expense>> getExpensesByDuration(Context context, String startDate, String endDate) {
        roomDatabase = initializeRepo(context);
        return roomDatabase.expenseDao().getExpensesByDuration(startDate, endDate);
    }

}