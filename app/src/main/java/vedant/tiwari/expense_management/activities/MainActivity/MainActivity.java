package vedant.tiwari.expense_management.activities.MainActivity;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import vedant.tiwari.expense_management.R;
import vedant.tiwari.expense_management.activities.AddExpense.AddExpense;
import vedant.tiwari.expense_management.adapter.Adapter;
import vedant.tiwari.expense_management.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    MainActivityViewModel viewModel;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        binding.category.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this,
                    R.style.AppBottomSheetDialogTheme);

            View view =
                    LayoutInflater.from(MainActivity.this).inflate(R.layout.food_bottom_sheet,
                            (LinearLayout) findViewById(R.id.design_bottom_sheet));
            dialog.setContentView(view);


            dialog.show();
        });

        binding.duration.setOnClickListener(v -> {
            BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this,
                    R.style.AppBottomSheetDialogTheme);

            View view =
                    LayoutInflater.from(MainActivity.this).inflate(R.layout.duration_bottom_sheet,
                            (LinearLayout) findViewById(R.id.design_bottom_sheet));
            dialog.setContentView(view);

            dialog.show();
        });

        binding.addExpense.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AddExpense.class)));

        viewModel.getAllExpenses(this).observe(this, expenses -> {
            if(expenses != null){

                adapter = new Adapter(expenses,viewModel);
                binding.mainRv.setAdapter(adapter);
            }
        });
    }
}