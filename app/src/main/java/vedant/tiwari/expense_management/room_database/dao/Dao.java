package vedant.tiwari.expense_management.room_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import vedant.tiwari.expense_management.room_database.entity.Expense;

@androidx.room.Dao
public interface Dao {

    @Insert
    void insertExpense(Expense expense);

    @Query("DELETE FROM expense_management WHERE id=:id")
    void deleteExpense(int id);

    @Update
    void updateExpense(Expense expense);

    @Query("SELECT * FROM expense_management")
    LiveData<List<Expense>> getAllExpense();

    @Query("SELECT * FROM expense_management WHERE category = :categoryType")
    LiveData<List<Expense>> getExpensesByCategory(String categoryType);

    @Query("SELECT * FROM expense_management WHERE expense_date BETWEEN :startDate AND :endDate")
    LiveData<List<Expense>> getExpensesByDuration(String startDate, String endDate);

    @Query("SELECT * FROM expense_management WHERE category = :category ORDER BY expense_date DESC")
    LiveData<List<Expense>> sortByCategory(String category);

    @Query("SELECT * FROM expense_management ORDER BY expense_date DESC")
    LiveData<List<Expense>> sortByDuration();



}
