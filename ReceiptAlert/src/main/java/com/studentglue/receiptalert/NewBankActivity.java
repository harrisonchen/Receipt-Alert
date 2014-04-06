package com.studentglue.receiptalert;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.EditText;

import java.util.HashMap;

public class NewBankActivity extends ActionBarActivity {

    DBTools dbtools = new DBTools(this);

    EditText bank_name_edittext, cycle_date_edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bank);

        bank_name_edittext = (EditText) findViewById(R.id.bank_name_edittext);
        cycle_date_edittext = (EditText) findViewById(R.id.cycle_date_edittext);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    public void addBank(View view) {

        String bank_name, cycle_date;

        bank_name = bank_name_edittext.getText().toString();
        cycle_date = cycle_date_edittext.getText().toString();

        if(!blank_or_empty(bank_name) && !blank_or_empty(cycle_date)) {
            HashMap<String, String> queryValues = new HashMap<String, String>();

            queryValues.put("bank_name", bank_name.toUpperCase());
            queryValues.put("cycle_date", cycle_date);

            dbtools.addBank(queryValues);

            setResult(Activity.RESULT_OK);

            finish();
        }
    }

    public boolean blank_or_empty(String s) {
        return (s == null || s.isEmpty());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_bank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        /*@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_bank, container, false);
            return rootView;
        }*/
    }

}
