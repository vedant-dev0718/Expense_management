package vedant.tiwari.expense_management.activities.AddExpense;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import vedant.tiwari.expense_management.room_database.Repo.ExpenseRepo;
import vedant.tiwari.expense_management.room_database.entity.Expense;

public class AddExpenseViewModel extends ViewModel {


    public void addExpense(Context context, Expense expense) {
        new ExpenseRepo().insertExpense(context,expense);
    }

    public void updateExpense(Context context,Expense expense) {
        new ExpenseRepo().updateExpense(context,expense);
    }

}
