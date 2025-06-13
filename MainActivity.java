package com.example.navigationdrawer;

import static android.view.Gravity.START;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    ImageView headerimage;
    TextView headerTitle;
    View headerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        drawerLayout=findViewById(R.id.drawerLayout);
        materialToolbar=findViewById(R.id.materialToolbar);
        frameLayout=findViewById(R.id.frameLayout);
        navigationView=findViewById(R.id.navigationView);

        //header এর জিনিসগুলো পরিচয় : যেহেতু Title text তাই TextView headerTitle; নিলাম । এটা যেহেতু আলাদা layout এ রাখতে হয় ।
        //তাই findViewById করার way আলাদা । প্রথমে comment এর উপর navigationView কে findViewById করলাম এরপর View headerview; ধরতে হবে ।
        //এবার navigationView এর নিচে  navigationView কে getHeaderView করে index নম্বর দিলাম তারপর headerview ভিতর রাখলাম।
        //এবার normal ভাবেই headerTitle কে headerview এর মাধ্যমে পরিচয় করাই দিলাম ।

        headerview=navigationView.getHeaderView(0);
//0 ধরছি কারণ ০ থেকে শুরু
        headerTitle=headerview.findViewById(R.id.headerTitle);
        headerimage=headerview.findViewById(R.id.headerimage);






        //drawerlayout , MaterialToolbar , NavigationView এই তিনটায় connection করে ActionBarDrawerToggle
        //R.string create করার জন্য values থেকে string.xml এ
        // এইরকম <string name="drawer_close">close Drawer</string> lind add করবো।
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,drawerLayout,materialToolbar,R.string.drawer_close,R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId()==R.id.share){
                    Toast.makeText(MainActivity.this,"share",Toast.LENGTH_LONG).show();
                }

                return false;
            }
        });

        FirstFragment.WEB_url="https://www.google.com/";

        FragmentManager fManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout,new FirstFragment() );//conteinerViewId মানে host এর id ,fragment (যেটাকে Transaction করাবো )
        fragmentTransaction.commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home){

                    FirstFragment.WEB_url="https://home.google.com/welcome/";
                    drawerLayout.closeDrawer(GravityCompat.START);

                    FragmentManager fManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =fManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout,new FirstFragment() );//conteinerViewId মানে host এর id ,fragment (যেটাকে Transaction করাবো )
                    fragmentTransaction.commit();

                }

                else if(item.getItemId()==R.id.profile){

                    FirstFragment.WEB_url="https://www.profilesw.com/";
                    drawerLayout.closeDrawer(GravityCompat.START);

                    FragmentManager fManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =fManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout,new FirstFragment() );//conteinerViewId মানে host এর id ,fragment (যেটাকে Transaction করাবো )
                    fragmentTransaction.commit();

                }

                else if(item.getItemId()==R.id.notification){
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if(item.getItemId()==R.id.setting){
                    FirstFragment.WEB_url="https://support.google.com/accounts/answer/3118621?hl=en";
                    drawerLayout.closeDrawer(GravityCompat.START);

                    FragmentManager fManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction =fManager.beginTransaction();
                    fragmentTransaction.add(R.id.frameLayout,new FirstFragment() );//conteinerViewId মানে host এর id ,fragment (যেটাকে Transaction করাবো )
                    fragmentTransaction.commit();
                }


                return true; //false থাকলে কোন কিছু select করলে ঐটা থেকে বের হলে আর ঐ selected থাকে না । মানে আবার শুরু হয় । এইজন্য true use করি।
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //------------------------on create end
}
