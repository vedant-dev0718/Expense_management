package vedant.tiwari.expense_management.room_database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "expense_management")
public class Expense {

    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "expense_date")
    public String expenseDate;
    @ColumnInfo(name = "category")
    public String category;
    @ColumnInfo(name = "category_image")
    public int category_image;
    @ColumnInfo(name = "Amount")
    public String amount;
    @ColumnInfo(name = "Description")
    public String description;


}
