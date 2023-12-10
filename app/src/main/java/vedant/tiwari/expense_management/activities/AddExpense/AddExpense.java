package vedant.tiwari.expense_management.activities.AddExpense;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;
import java.util.Objects;

import vedant.tiwari.expense_management.databinding.AddExpenseBinding;
import vedant.tiwari.expense_management.room_database.entity.Expense;

public class AddExpense extends AppCompatActivity {

    private AddExpenseBinding binding;
    private AddExpenseViewModel viewModel;
    String expense_date = "",
            expense_description = "",
            expense_amount = "", location = "";

    int expense_id;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddExpenseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AddExpenseViewModel.class);

        expense_id = getIntent().getIntExtra("expense_id", -1);
        expense_date = getIntent().getStringExtra("expense_date");
        expense_amount = getIntent().getStringExtra("expense_amount");
        location = getIntent().getStringExtra("location");
        expense_description = getIntent().getStringExtra("expense_description");

        binding.calendar.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    AddExpense.this,
                    (view, year1, monthOfYear, dayOfMonth) ->
                            binding.datepicker.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year1), year, month, day);
            datePickerDialog.show();
        });

        if (expense_id < 0) {
            binding.submit.setOnClickListener(v -> {
                createExpense();
                finish();
            });

        } else {
            binding.submit.setOnClickListener(v -> {
                updateExpense();
                finish();
            });

        }


    }

    public void createExpense() {

        Expense expense = new Expense();
        expense.expenseDate = binding.datepicker.getText().toString();
        expense.amount = Objects.requireNonNull(binding.amount.getText()).toString();
        expense.description = Objects.requireNonNull(binding.description.getText()).toString();

        viewModel.addExpense(this, expense);

    }

    public void updateExpense() {

        Expense expense = new Expense();
        expense.id = expense_id;
        expense.expenseDate = binding.datepicker.getText().toString();
        expense.amount = Objects.requireNonNull(binding.amount.getText()).toString();
        expense.description = Objects.requireNonNull(binding.description.getText()).toString();

        viewModel.updateExpense(this, expense);

    }
}