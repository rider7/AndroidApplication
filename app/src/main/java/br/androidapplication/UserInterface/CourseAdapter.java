package br.androidapplication.UserInterface;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.androidapplication.Entity.CourseEntity;
import br.androidapplication.R;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView CourseItemView;
        private final TextView CourseItemView2;


        private CourseViewHolder(View itemView) {
            super(itemView);
            CourseItemView = itemView.findViewById(R.id.courseTextView);
            CourseItemView2 = itemView.findViewById(R.id.courseTextView2);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    final CourseEntity current = mCourses.get(position);
//                    Intent intent = new Intent(context, CourseDetail.class);
//                    intent.putExtra("CourseName", current.getCourseName());
//                    intent.putExtra("CoursePrice", Double.toString(current.getCoursePrice()));
//                    intent.putExtra("position",position);
//                    intent.putExtra("CourseID",current.getCourseID());
//                    intent.putExtra("productID", current.getProductID());
//                    context.startActivity(intent);
//                }
//            });
        }

    }

    private final LayoutInflater mInflater;
    private final Context context;
    private List<CourseEntity> mCourses; // Cached copy of words

    public CourseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @Override
    public CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item, parent, false);

        return new CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseViewHolder holder, int position) {
        if (mCourses != null) {
            CourseEntity current = mCourses.get(position);
            holder.CourseItemView.setText(current.getCourseTitle());
            holder.CourseItemView2.setText(Integer.toString(current.getCourseTermID()));
        } else {
            // Covers the case of data not being ready yet.
            holder.CourseItemView.setText("No Word");
            holder.CourseItemView2.setText("No Word");
        }

    }

    public void setWords(List<CourseEntity> words) {
        mCourses = words;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCourses != null)
            return mCourses.size();
        else return 0;
    }
}
