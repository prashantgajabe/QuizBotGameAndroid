package com.prashant.quizbotgame;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.prashant.quizbotgame.databinding.QuestionBinding;

import java.util.List;

public class Connector extends RecyclerView.Adapter<Connector.ConnViewHolder> {

    private List<Question> QuestionList;
    private OnItemClickListener listen;
    private Context context;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){listen=listener;}

    public static class ConnViewHolder extends RecyclerView.ViewHolder{
        private QuestionBinding questionBinding;

        public ConnViewHolder(@NonNull final QuestionBinding questionBinding, final OnItemClickListener listener){
            super(questionBinding.getRoot());
            this.questionBinding = questionBinding;

            questionBinding.QLayout.setOnClickListener(new View.OnClickListener() {
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


    public Connector(List<Question> QuestionList, Context context){this.QuestionList=QuestionList;this.context=context;}


    @NonNull
    @Override
    public Connector.ConnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        QuestionBinding binding = DataBindingUtil.inflate(inflater, R.layout.question, parent, false);

        return new ConnViewHolder(binding, listen);
    }

    @Override
    public void onBindViewHolder(@NonNull Connector.ConnViewHolder holder, int position) {
        holder.questionBinding.txtQNum.setText("Question");
        holder.questionBinding.txtQDetail.setText(QuestionList.get(position).getQuesDetail());
        if(QuestionList.get(position).getDone().equals("Y")){
            holder.questionBinding.imgqdone.setImageDrawable(context.getDrawable(R.drawable.checked));
        } else {
            holder.questionBinding.imgqdone.setImageDrawable(context.getDrawable(R.drawable.unchecked));
        }
    }

    @Override
    public int getItemCount() {
        return QuestionList.size();
    }
}