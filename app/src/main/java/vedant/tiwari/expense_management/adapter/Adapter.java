package vedant.tiwari.expense_management.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vedant.tiwari.expense_management.activities.AddExpense.AddExpense;
import vedant.tiwari.expense_management.activities.MainActivity.MainActivityViewModel;
import vedant.tiwari.expense_management.databinding.ExpenseViewholderBinding;
import vedant.tiwari.expense_management.room_database.entity.Expense;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    static List<Expense> expenses;
    MainActivityViewModel viewModel;

    public Adapter(List<Expense> expenses, MainActivityViewModel viewModel) {
        this.expenses = expenses;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(ExpenseViewholderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.bind(expenses.get(position), viewModel);
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        ExpenseViewholderBinding binding;

        public AdapterViewHolder(ExpenseViewholderBinding viewHolderBinding) {
            super(viewHolderBinding.getRoot());
            binding = viewHolderBinding;
        }

        public void bind(Expense expense, MainActivityViewModel viewModel) {
            binding.appliedDate.setText(expense.expenseDate);
            binding.expectedDate.setText(expense.expenseDate);

            binding.approvedAmount.setText(expense.amount);
            binding.submittedAmount.setText(expense.amount);

            binding.description.setText(expense.description);

            binding.delete.setOnClickListener(v -> {
                viewModel.deleteExpense(itemView.getContext(), expense.id);
                expenses.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
                notifyItemRangeChanged(getAdapterPosition(), expenses.size() - getAdapterPosition());
            });

            binding.editButton.setOnClickListener(v -> {
                itemView.getContext().startActivity(
                        new Intent(itemView.getContext(), AddExpense.class)
                                .putExtra("location","adapter")
                                .putExtra("expense_id",expense.id)
                                .putExtra("expense_date",expense.expenseDate)
                                .putExtra("expense_description",expense.description)
                                .putExtra("expense_amount",expense.amount)
                );
            });

        }
    }
}
