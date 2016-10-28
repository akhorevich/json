package com.example.nortti.txt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    ArrayList<Item> items;
    ListView ls;
    ArrayAdapter<String> adapter;
    Adapter mAdapter;
    Item item;
    private final static String FILENAME = "sample.txt"; // имя файла
    private EditText mEditText, surEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        item  = new Item();
        setContentView(R.layout.activity_main);

        ls = (ListView) findViewById(R.id.ls);
        mEditText = (EditText) findViewById(R.id.nameEd);
        surEditText = (EditText) findViewById(R.id.surEd);
        items = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem it) {
        switch (it.getItemId()) {
            case R.id.action_open:
                Toast.makeText(this, readFromFile(), Toast.LENGTH_SHORT).show();
                String[] separated = readFromFile().split("_");
                item.setName(separated[0]);
                item.setSurname(separated[1]);
                items.add(item);
                mAdapter = new Adapter(this,items);
                ls.setAdapter(mAdapter);

                return true;
            case R.id.action_save:
                writeToFile(mEditText.getText().toString(),surEditText.getText().toString());
                mEditText.setText("");
                surEditText.setText("");
                return true;
            default:
                return true;
        }
    }

    private void writeToFile(String name, String surname) {
        try {
            String data = name +"_"+surname;
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("config.txt", Context.MODE_APPEND));
            outputStreamWriter.write(data+"\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();

            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

 /*   // Метод для открытия файла
    private void openFile(String fileName) {
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                    //items.add(builder.toString());
                }

                inputStream.close();
               // mEditText.setText(builder.toString());
                mAdapter = new Adapter(this,items);
                ls.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }

    // Метод для сохранения файла
    private void saveFile(String fileName) {
        Item item = new Item();
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            item.setName(mEditText.getText().toString());

            item.setSurname(surEditText.getText().toString());

            items.add(item);
            osw.write(mEditText.getText().toString());
            osw.close();
            mEditText.setText("");
            surEditText.setText("");
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }*/
}
