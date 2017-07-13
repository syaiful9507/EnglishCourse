package syaiful.finalpro.englishcourse.fragments0;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import syaiful.finalpro.englishcourse.R;

public class ListContentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //recyclerView.setLayoutManager(new GridLayoutManager(getActivity().getApplication(), 2));
        //recyclerView = (RecyclerView)getView().findViewById(R.id.recyclerView);
        View view =inflater.inflate(R.layout.list, container, false);


        return view;

    }
}
