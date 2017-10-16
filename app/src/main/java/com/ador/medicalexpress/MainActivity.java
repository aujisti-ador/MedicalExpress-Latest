package com.ador.medicalexpress;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.ador.medicalexpress.R.drawable.ambulance;
import static com.ador.medicalexpress.R.drawable.blood;
import static com.ador.medicalexpress.R.drawable.hospital;
import static com.ador.medicalexpress.R.drawable.location;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    private int[] tabIcons = {
            hospital,
            location,
            ambulance,
            blood
    };

    String[] tabsTitles = {
            "Hospital",
            "Nearby",
            "Ambulance",
            "Blood"
    } ;
    //Icons


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(tabsTitles[0]);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new HospitalFragment(),"");
        viewPagerAdapter.addFragments(new NearbyFragment(),"");
        viewPagerAdapter.addFragments(new AmbulanceFragment(),"");
        viewPagerAdapter.addFragments(new BloodFragment(),"");
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                toolbar.setTitle(tabsTitles[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);

        setupTabIcons();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id==R.id.action_about)
        {
            Toast.makeText(this, "About Us!", Toast.LENGTH_SHORT).show();
        }
        else if (res_id==R.id.action_share)
        {
            Toast.makeText(this, "Share!", Toast.LENGTH_SHORT).show();
        }
        else if (res_id==R.id.action_blood)
        {


                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.request_blood_form,null);
                final EditText name = (EditText) mView.findViewById(R.id.et_name);
                final EditText blood_group = (EditText) mView.findViewById(R.id.et_bloodGroup);
                final EditText place = (EditText) mView.findViewById(R.id.et_place);
                final EditText phone_number = (EditText) mView.findViewById(R.id.et_phone);
                final EditText date = (EditText) mView.findViewById(R.id.et_date);
                Button requ = (Button) mView.findViewById(R.id.btn_requ);

                requ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!name.getText().toString().isEmpty() && !blood_group.getText().toString().isEmpty())
                        {
                            Toast.makeText(MainActivity.this, "Its Works!", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(MainActivity.this, "Not Works!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            alertBuilder.setView(mView);
            AlertDialog dialog = alertBuilder.create();
            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
    }
}
