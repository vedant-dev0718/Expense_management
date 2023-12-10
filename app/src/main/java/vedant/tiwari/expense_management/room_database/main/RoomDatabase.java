package vedant.tiwari.expense_management.room_database.main;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import vedant.tiwari.expense_management.room_database.dao.Dao;
import vedant.tiwari.expense_management.room_database.entity.Expense;

@Database(entities = {Expense.class},version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {


    public abstract Dao expenseDao();
    public static RoomDatabase INSTANCE;

    public static RoomDatabase getDatabaseInstance(Context context) {
        if(INSTANCE==null)
        {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),RoomDatabase.class
                    ,"expense_management")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
