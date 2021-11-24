package br.androidapplication.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.androidapplication.Entity.TermEntity;
import br.androidapplication.R;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private TextView termItemView;
    private TextView termItemView2;
    class TermViewHolder(View itemView) extends RecyclerView.ViewHolder {
        //Constructor
        private TermViewHolder(View itemView){
            super(itemView);
            //This maps to the id = textView on term_list_item.xml
            termItemView = itemView.findViewById(R.id.textView);
            termItemView2 = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final TermEntity current=mTerm.get(postion);
                    Intent intent = new Intent(context,MainActivity.class);

                }
            });


        }
    }
    private List<TermEntity> mTerm;
    private final Context context;
    private final LayoutInflater mInflater;
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {

    }

    //Set the list
    public void setTerm(List<TermEntity> term){
        mTerm = term;
        notifyDataSetChanged();

    }

    //Counts if list has items
    @Override
    public int getItemCount() {
        if(mTerm != null){
        return mTerm.size();}
        else return 0;
    }
}
