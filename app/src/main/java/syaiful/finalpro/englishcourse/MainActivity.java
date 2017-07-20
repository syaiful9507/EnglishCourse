package syaiful.finalpro.englishcourse;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import syaiful.finalpro.englishcourse.chat.ui.activities.LoginActivity;
import syaiful.finalpro.englishcourse.chat.ui.activities.SplashActivity;
import syaiful.finalpro.englishcourse.chat.ui.activities.UserListingActivity;
import syaiful.finalpro.englishcourse.fragments.ListCourseFragments;
import syaiful.finalpro.englishcourse.fragments.ListTenseFragment;
import syaiful.finalpro.englishcourse.fragments0.TileContentFragment;

public class MainActivity extends AppCompatActivity {
    ImageView imgchat;
    ViewPager viewPager;
    TabLayout tabs;

    //add line chat
    private static final int SPLASH_TIME_MS = 2000;
    private Handler mHandler;
    private Runnable mRunnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgchat = (ImageView)findViewById(R.id.imagechat);
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

        imgchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler = new Handler();
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        // check if user is already logged in or not
                        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                            // if logged in redirect the user to user listing activity
                            UserListingActivity.startActivity(MainActivity.this);
                        } else {
                            // otherwise redirect the user to login activity
                            LoginActivity.startIntent(MainActivity.this);
                        }
                        finish();
                    }
                };

                mHandler.postDelayed(mRunnable, SPLASH_TIME_MS);
            }
        });
    }

    //add fragments to tabs;

    private void setupViewpager(ViewPager viewPager){

        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new ListCourseFragments(), "Course");
        adapter.addFragment(new ListTenseFragment(), "Tense");
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



    //ADD LINE

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, int flags) {
            Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(flags);
        context.startActivity(intent);
    }


}
