package syaiful.finalpro.englishcourse.adapter;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;

/**
 * Created by syaiful9508 on 23/07/17.
 */

public class AdapterDetailCourse extends RecyclerView.Adapter<AdapterDetailCourse.ViewHolder>{


    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;
    private Config config = new Config();


    public AdapterDetailCourse(Context context, ArrayList<HashMap<String , String >> list_data) {
        this.context = context;
        this.list_data = list_data;

    }
    @Override
    public AdapterDetailCourse.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contentcourse, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final AdapterDetailCourse.ViewHolder mViewHolder = new AdapterDetailCourse.ViewHolder(view);

        //07-07-2017
        return new AdapterDetailCourse.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AdapterDetailCourse.ViewHolder holder, int position) {


        holder.title.setText(list_data.get(position).get(config.TAG_SUBTITLE));
        holder.content.setText(list_data.get(position).get(config.TAG_CONTENT));


    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public void setClickListener(CustomItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);

            title   = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.txtContent);
            Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
            title.setTypeface(typeface);

            Typeface contentt = Typeface.createFromAsset(context.getAssets(), "fonts/SFCartoonistHand.ttf");
            content.setTypeface(contentt);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onItemClick(view, getAdapterPosition());
        }


        //Logic for OnItemClick
        //Date jum, jul 7


        //END OF CLASS VIEWHOLDER
    }
}
