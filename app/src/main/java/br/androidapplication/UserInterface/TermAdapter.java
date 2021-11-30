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

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
//        private final TextView termItemView2;
//        private final TextView termItemView3;
        //Constructor
        private TermViewHolder(View itemView){
            super(itemView);


            //This maps to the id = textView on term_list_item.xml
            termItemView = itemView.findViewById(R.id.textView);
//            termItemView2 = itemView.findViewById(R.id.textView2);
//            termItemView3 = itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final TermEntity current =mTerm.get(position);
                    Intent intent = new Intent(context,TermActivity.class);
                    intent.putExtra("termID", current.getTermID());
                    intent.putExtra("termTitle", current.getTermTitle());
                    intent.putExtra("termStart", current.getTermStart());
                    intent.putExtra("termEnd", current.getTermEnd());
                    context.startActivity(intent);
                }
            });
        }
    }

    private List<TermEntity> mTerm;
    private final Context context;
    private final LayoutInflater mInflater;

    //Layout inflater connects to list item layout
    public TermAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);

        return new TermViewHolder(itemView);
    }

    //Set the data
    //Call this should be in TermActivity but is right now in MainActivity
    @Override
    public void onBindViewHolder(TermViewHolder holder, int position) {
        if(mTerm!=null){
            final TermEntity current = mTerm.get(position);
            holder.termItemView.setText(current.getTermTitle());
//            holder.termItemView2.setText(Integer.toString(current.getTermID()));
//            holder.termItemView3.setText(current.getTermStart());
        }
        else{
            holder.termItemView.setText("No Term Title Available");
//            holder.termItemView2.setText("No Term ID Available.");
        }
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
