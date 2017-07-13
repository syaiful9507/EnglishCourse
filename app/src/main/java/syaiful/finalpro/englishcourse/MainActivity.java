package syaiful.finalpro.englishcourse;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import syaiful.finalpro.englishcourse.fragments.ListCourseFragments;
import syaiful.finalpro.englishcourse.fragments0.ListContentFragment;
import syaiful.finalpro.englishcourse.fragments0.TileContentFragment;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adding toolbar to main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting viewpager for each tabs
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewpager(viewPager);

        //set tabs inside toolbar
        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(
                viewPager);
    }

    //add fragments to tabs;

    private void setupViewpager(ViewPager viewPager){

        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new TileContentFragment(), "Tense");
        adapter.addFragment(new ListCourseFragments(), "Course");
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mfragmentTitleList = new ArrayList<>();

        public Adapter (FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position){
            return mFragmentList.get(position);
        }

        @Override
        public int getCount(){
            return  mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mfragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position){
            return mfragmentTitleList.get(position);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.tile, parent, false));
        }
    }

}
