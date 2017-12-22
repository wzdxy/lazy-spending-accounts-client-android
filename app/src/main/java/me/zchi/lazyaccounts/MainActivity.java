package me.zchi.lazyaccounts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigation;
    private ViewPager viewPager;

    private AddFragment addFragment = new AddFragment();
    private AccountFragment accountFragment = new AccountFragment();
    private ReportFragment reportFragment = new ReportFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            viewPager.setCurrentItem(item.getOrder());
            return true;
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.top_menu_settings:{
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.top_menu_user:{
                Intent intent = new Intent(this,UserActivity.class);
                startActivity(intent);
                return true;
            }
            case R.id.top_menu_refresh:{
                Toast.makeText(getApplicationContext(),"Dev ing",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigation = findViewById(R.id.navigation);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2);


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return addFragment;
                    case 1:
                        return accountFragment;
                    case 2:
                        return reportFragment;
                }
                return null;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                Log.e("PageSelected", String.valueOf(position));
                switch (position) {
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_account);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_report);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });



    }

}
