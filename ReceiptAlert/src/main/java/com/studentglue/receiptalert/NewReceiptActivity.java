package com.studentglue.receiptalert;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
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
import android.widget.ImageView;

import java.io.File;
import java.util.HashMap;

public class NewReceiptActivity extends ActionBarActivity {

    Intent new_receipt_intent;
    Bundle extras;

    private static String absolutePath, receipt_label, receipt_date, receipt_bank;

    ImageView receipt_imageview;
    EditText receipt_label_edittext, receipt_date_edittext, receipt_bank_edittext;

    DBTools dbtools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_receipt);

        dbtools = new DBTools(this);

        receipt_imageview = (ImageView) findViewById(R.id.receipt_imageview);
        receipt_label_edittext = (EditText) findViewById(R.id.receipt_label_edittext);
        receipt_date_edittext = (EditText) findViewById(R.id.receipt_date_edittext);
        receipt_bank_edittext = (EditText) findViewById(R.id.receipt_bank_edittext);

        new_receipt_intent = getIntent();
        extras = new_receipt_intent.getExtras();

        absolutePath = extras.getString("IMAGE_PATH");

        File receipt_file = new File(absolutePath);
        Uri receipt_uri = Uri.fromFile(receipt_file);

        Bitmap receipt_bitmap = BitmapFactory.decodeFile(absolutePath);
        Bitmap receipt_bt = Bitmap.createScaledBitmap(receipt_bitmap, 300, 300, false);

        receipt_imageview.setImageBitmap(receipt_bt);

        //Temporary ... requires API > 11
        receipt_imageview.setRotation(rotateImageBy(absolutePath));

        /*Matrix matrix = new Matrix();
        receipt_imageview.setScaleType(ImageView.ScaleType.MATRIX);
        matrix.postRotate((float) rotateImageBy(absolutePath), 100, 100);
        receipt_imageview.setImageMatrix(matrix);*/

        //receipt_imageview.setImageURI(receipt_uri);

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
    }

    public int rotateImageBy(String absolutePath) {

        int rotate = 0;

        try {
            ExifInterface exif = new ExifInterface(absolutePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {

                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return rotate;
    }

    public void saveReceipt(View view) {

        receipt_label = receipt_label_edittext.getText().toString();
        receipt_date = receipt_date_edittext.getText().toString();
        receipt_bank = receipt_bank_edittext.getText().toString();

        HashMap<String, String> receiptMap = new HashMap<String, String>();

        receiptMap.put("receipt_label", receipt_label);
        receiptMap.put("receipt_date", receipt_date);
        receiptMap.put("receipt_bank", "1");
        receiptMap.put("image_value", absolutePath);

        dbtools.addReceipt(receiptMap);

        setResult(Activity.RESULT_OK);

        finish();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_receipt, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_new_receipt, container, false);
            return rootView;
        }*/
    }

}
