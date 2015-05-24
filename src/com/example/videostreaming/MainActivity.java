package com.example.videostreaming;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    private NavigationDrawerFragment mNavigationDrawerFragment;
    private FragmentManager fragManager;
    private ArrayList<Fragment> listFragment;
    private FragmentOne frag1;
    private FragmentTwo frag2;
    private FragmentThree frag3;
    private FragmentFour frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) { 	
//    	Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_SHORT).show();
    	if (listFragment == null) {
//        	Toast.makeText(getApplicationContext(), "Fragment Manager NULL", Toast.LENGTH_SHORT).show();
    		fragManager = getFragmentManager();
            
            listFragment = new ArrayList<Fragment>();
            frag1 = new FragmentOne();
            frag2 = new FragmentTwo();
            frag3 = new FragmentThree();
            frag4 = new FragmentFour();
            
            listFragment.add(frag1);
            listFragment.add(frag2);
            listFragment.add(frag3);
            listFragment.add(frag4);
            
            for (Fragment fragment : listFragment) {
            	fragManager.beginTransaction().add(R.id.container, fragment).commit();
            	fragManager.beginTransaction().detach(fragment).commit();
    		}
            
            fragManager.beginTransaction().attach(listFragment.get(position)).commit();
    	} else {
        	for (Fragment fragment : listFragment) {
    			if (fragment == listFragment.get(position)) {
    				fragManager.beginTransaction().attach(fragment).commit();
    			} else {
    				fragManager.beginTransaction().detach(fragment).commit();
    			}
    		}
    	}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
