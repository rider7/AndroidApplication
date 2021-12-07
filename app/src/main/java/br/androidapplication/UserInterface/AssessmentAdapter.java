package br.androidapplication.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.androidapplication.Entity.AssessmentEntity;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView AssessmentItemView;
        private final TextView AssessmentItemView2;
        private final TextView AssessmentItemView3;


        private AssessmentViewHolder(View itemView) {
            super(itemView);
            AssessmentItemView = itemView.findViewById(R.id.assessmentTextView);
            AssessmentItemView2 = itemView.findViewById(R.id.assessmentTextView2);
            AssessmentItemView3 = itemView.findViewById(R.id.assessmentTextView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final AssessmentEntity current = mAssessments.get(position);
                    Intent intent = new Intent(context, EditActivity.class);
                    intent.putExtra("assessmentID", current.getAssessmentID());
                    intent.putExtra("courseAssessmentID", current.getCourseAssessmentID());
                    intent.putExtra("AssessmentTitle", current.getAssessmentTitle());
                    intent.putExtra("AssessmentType", current.getAssessmentType());
                    intent.putExtra("AssessmentEnd", current.getAssessmentEnd());
                    context.startActivity(intent);
                }
            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<AssessmentEntity> mAssessments; // Cached copy of words

    public AssessmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public AssessmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);

        return new AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AssessmentViewHolder holder, int position) {
        if (mAssessments != null) {
            AssessmentEntity current = mAssessments.get(position);
            holder.AssessmentItemView.setText(current.getAssessmentTitle());
            holder.AssessmentItemView2.setText(Integer.toString(current.getCourseAssessmentID()));
            holder.AssessmentItemView2.setText(current.getAssessmentType());
        } else {
            // Covers the case of data not being ready yet.
            holder.AssessmentItemView.setText("No Word");
            holder.AssessmentItemView2.setText("No Word");
        }

    }

    public void setWords(List<AssessmentEntity> words) {
        mAssessments = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAssessments != null)
            return mAssessments.size();
        else return 0;
    }
}

