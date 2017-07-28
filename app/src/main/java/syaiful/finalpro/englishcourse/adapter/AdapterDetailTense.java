package syaiful.finalpro.englishcourse.adapter;

import android.content.Context;
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
 * Created by syaiful9508 on 25/07/17.
 */

public class AdapterDetailTense extends RecyclerView.Adapter<AdapterDetailTense.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;
    private Config config = new Config();


    public AdapterDetailTense(Context context, ArrayList<HashMap<String , String >> list_data) {
        this.context = context;
        this.list_data = list_data;

    }

    @Override
    public AdapterDetailTense.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_contentcourse, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final AdapterDetailTense.ViewHolder mViewHolder = new AdapterDetailTense.ViewHolder(view);

        //07-07-2017
        return new AdapterDetailTense.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDetailTense.ViewHolder holder, int position) {

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
        TextView title,ID;
        TextView content;

        public ViewHolder(View itemView) {
            super(itemView);

            title   = (TextView) itemView.findViewById(R.id.title);
            content = (TextView) itemView.findViewById(R.id.txtContent);

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
