package com.example.notatnik;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText hdr;
    EditText content;
    ListView list;
    Button btn;
    List<Map<String, String>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        hdr = findViewById(R.id.header);
        content = findViewById(R.id.body);
        list =findViewById(R.id.list);
        btn = findViewById(R.id.btn);

        Map<String, String> item1 = new HashMap<>();
        item1.put("main", "Deadlock Major Patch");
        item1.put("sub", "big things happening in the cursed apple");
        data.add(item1);

        String[] from = {"main", "sub"};
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                data,
                android.R.layout.simple_list_item_2,
                from,
                to
        );

        list.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Map<String, String> temp = new HashMap<>();
                temp.put("main", String.valueOf(hdr.getText()));
                temp.put("sub", String.valueOf(content.getText()));

                data.add(temp);
                adapter.notifyDataSetChanged();
            }
        });

    }
}