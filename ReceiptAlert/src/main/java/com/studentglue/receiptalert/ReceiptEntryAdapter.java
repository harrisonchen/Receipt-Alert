package com.studentglue.receiptalert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ReceiptEntryAdapter extends BaseAdapter {

    ArrayList<HashMap<String, String>> receiptArrayList;
    Context context;
    DBTools dbtools;

    ReceiptEntryAdapter(Context c, String bank) {
        context = c;
        dbtools = new DBTools(context);
        receiptArrayList = dbtools.getAllReceiptsFromBank(bank);
    }

    @Override
    public int getCount() {
        return receiptArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return receiptArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.receipt_entry, viewGroup, false);

        TextView receipt_id = (TextView) row.findViewById(R.id.receipt_id);
        TextView receipt_label_textview = (TextView) row.findViewById(R.id.receipt_label_textview);
        TextView receipt_date_textview = (TextView) row.findViewById(R.id.receipt_date_textview);

        HashMap<String, String> receiptMap = receiptArrayList.get(i);

        receipt_id.setText(receiptMap.get("receipt_id"));
        receipt_label_textview.setText(receiptMap.get("receipt_label"));
        receipt_date_textview.setText(receiptMap.get("receipt_date"));

        return row;
    }
}
