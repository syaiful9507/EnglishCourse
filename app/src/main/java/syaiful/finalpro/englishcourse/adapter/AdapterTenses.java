package syaiful.finalpro.englishcourse.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;

import syaiful.finalpro.englishcourse.R;
import syaiful.finalpro.englishcourse.config.Config;
import syaiful.finalpro.englishcourse.custom.CustomItemClickListener;

/**
 * Created by syaiful9508 on 19/07/17.
 */

public class AdapterTenses extends RecyclerView.Adapter<AdapterTenses.ViewHolder>{

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    CustomItemClickListener listener;
    private Config config = new Config();


    public AdapterTenses(Context context, ArrayList<HashMap<String , String >> list_data) {
        this.context = context;
        this.list_data = list_data;

    }
    @Override
    public AdapterTenses.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_card_tenses, parent, false);
        //AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //07-07-2017
        final AdapterTenses.ViewHolder mViewHolder = new AdapterTenses.ViewHolder(view);

        //07-07-2017
        return new AdapterTenses.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterTenses.ViewHolder holder, int position) {

        holder.textView.setText(list_data.get(position).get(config.TAG_TITLE));
        Glide.with(context)
                .load(config.IMAGE + list_data.get(position).get(config.TAG_IMAGE))
                .placeholder(R.drawable.enlish)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    //custom onitemclick
    public void setClickListener(CustomItemClickListener itemClickListener) {
        this.listener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView, tex;

        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.text);
            imageView = (ImageView) itemView.findViewById(R.id.image);

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
