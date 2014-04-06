package com.studentglue.receiptalert;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class BankEntryAdapter extends BaseAdapter{

    ArrayList<HashMap<String, String>> bankArrayList;
    Context context;
    DBTools dbtools;

    BankEntryAdapter(Context c) {
        context = c;
        dbtools = new DBTools(context);
        bankArrayList = dbtools.getAllBanks();
    }

    @Override
    public int getCount() {
        return bankArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return bankArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.bank_entry, viewGroup, false);

        TextView bank_id = (TextView) row.findViewById(R.id.bank_id);
        TextView bank_name = (TextView) row.findViewById(R.id.bank_name_textview);

        HashMap<String, String> bankMap = bankArrayList.get(i);

        bank_id.setText(bankMap.get("bank_id"));
        bank_name.setText(bankMap.get("bank_name"));

        return row;
    }
}
