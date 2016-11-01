package com.example.nortti.txt;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText name;
    EditText surname;
    Button addButton;
    ListView listView;
    ArrayList<JSONObject> jsonArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonArrayList = new ArrayList<>();
         listView = (ListView) findViewById(R.id.ls);

        name = (EditText) findViewById(R.id.nameEd);
        surname = (EditText) findViewById(R.id.surEd);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(this);

/*        if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(surname.getText().toString())){
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }*/




    }

    private JSONArray createJSON() throws JSONException {


        JSONObject object;


        /*** Ряд 1 ***/
        object = new JSONObject();
        object.put("Name", name.getText());
        object.put("Surname", surname.getText());
        jsonArrayList.add(object);


        jsonArrayList.add(object);

        JSONArray jsonArray = new JSONArray(jsonArrayList);
        return jsonArray;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addButton:
                try {

                    JSONArray data = createJSON();

                    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
                    HashMap<String, String> hashMap;

                    for(int i = 0; i < data.length(); i++){
                        JSONObject jsonObject = data.getJSONObject(i);
                        hashMap = new HashMap<>();
                        hashMap.put("Name", jsonObject.getString("Name"));
                        hashMap.put("Surname", jsonObject.getString("Surname"));
                        arrayList.add(hashMap);
                    }

                    SimpleAdapter simpleAdapter;
                    simpleAdapter = new SimpleAdapter(this, arrayList,
                            R.layout.item, new String[]{
                            "Name", "Surname"}, new int[]{R.id.name,
                            R.id.surname});
                    listView.setAdapter(simpleAdapter);
                    name.setText("");
                    surname.setText("");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}