package vedant.tiwari.expense_management.activities.MainActivity;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import vedant.tiwari.expense_management.room_database.Repo.ExpenseRepo;
import vedant.tiwari.expense_management.room_database.entity.Expense;

public class MainActivityViewModel extends ViewModel {

    public LiveData<List<Expense>> getAllExpenses(Context context) {
        return new ExpenseRepo().getAllExpenses(context);
    }

    public LiveData<List<Expense>> getExpensesByCategory(String category, Context context) {
        return new ExpenseRepo().getExpensesByCategory(context, category);
    }

    public LiveData<List<Expense>> getExpensesByDuration(Context context, String startDate,
                                                         String endDate) {
        return new ExpenseRepo().getExpensesByDuration(context, startDate, endDate);
    }

    public void deleteExpense(Context context, int expenseId) {
        new ExpenseRepo().deleteExpense(context, expenseId);
    }
}
