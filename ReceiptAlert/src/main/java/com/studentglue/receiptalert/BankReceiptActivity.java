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
import android.widget.ListView;

public class BankReceiptActivity extends ActionBarActivity {

    ListView receiptListView;
    ReceiptEntryAdapter receiptEntryAdapter;

    Intent intent;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_receipt);

        intent = getIntent();
        extras = intent.getExtras();

        String bank_id = extras.getString("BANK_ID");

        receiptListView = (ListView) findViewById(R.id.receipt_listview);
        receiptEntryAdapter = new ReceiptEntryAdapter(this, bank_id);

        receiptListView.setAdapter(receiptEntryAdapter);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    public void updateAdapter() {
        receiptEntryAdapter = new ReceiptEntryAdapter(this, "BANK NAME");
        receiptListView.setAdapter(receiptEntryAdapter);
        receiptEntryAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bank_receipt, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_bank_receipt, container, false);
            return rootView;
        }*/
    }

}
