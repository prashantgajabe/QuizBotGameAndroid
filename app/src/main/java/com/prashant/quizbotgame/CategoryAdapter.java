package com.prashant.quizbotgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prashant.quizbotgame.databinding.CategoryBinding;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<String> QuestionList;
    private CategoryAdapter.OnItemClickListener listen;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){listen=listener;}


    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CategoryBinding binding = DataBindingUtil.inflate(inflater, R.layout.category, parent, false);
        return new CategoryViewHolder(binding, listen);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        holder.categoryBinding.txtCatName.setText(QuestionList.get(position));
    }

    @Override
    public int getItemCount() {
        return QuestionList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        private CategoryBinding categoryBinding;
        public CategoryViewHolder(@NonNull final CategoryBinding categoryBinding, final OnItemClickListener listener) {
            super(categoryBinding.getRoot());
            this.categoryBinding = categoryBinding;

            categoryBinding.CLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
    public CategoryAdapter(List<String> QuestionList, Context context){
        this.QuestionList = QuestionList;
        this.context=context;
    }
}
