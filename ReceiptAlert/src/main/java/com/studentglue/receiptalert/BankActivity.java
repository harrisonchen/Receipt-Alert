package com.studentglue.receiptalert;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BankActivity extends ActionBarActivity {

    private static final int NEW_BANK_REQUEST = 1;

    ListView bankListView;
    BankEntryAdapter bankEntryAdapter;

    TextView bank_id_textview;
    String bank_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        bankListView = (ListView) findViewById(R.id.bank_listview);
        bankEntryAdapter = new BankEntryAdapter(this);

        bankListView.setAdapter(bankEntryAdapter);

        bankListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                bank_id_textview = (TextView) view.findViewById(R.id.bank_id);

                bank_id = bank_id_textview.getText().toString();

                Intent bankReceiptIntent = new Intent(getApplication(), BankReceiptActivity.class);
                Bundle extras = new Bundle();
                extras.putString("BANK_ID", bank_id);
                bankReceiptIntent.putExtras(extras);
                startActivity(bankReceiptIntent);
            }
        });

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    public void newBank(View view) {
        Intent newBankIntent = new Intent(getApplication(), NewBankActivity.class);

        startActivityForResult(newBankIntent, NEW_BANK_REQUEST);
    }

    public void updateAdapter() {
        bankEntryAdapter = new BankEntryAdapter(this);
        bankListView.setAdapter(bankEntryAdapter);
        bankEntryAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == NEW_BANK_REQUEST && resultCode == RESULT_OK)
        {
            updateAdapter();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bank, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_bank, container, false);
            return rootView;
        }*/
    }

}
