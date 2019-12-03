package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    List<String>listDataHeader;
    HashMap<String,List<String>>listDataChild;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandablelistviewId);

        //String er data gulo ana holo
        prepareListData();

        customAdapter = new CustomAdapter(this,listDataHeader,listDataChild);
        expandableListView.setAdapter(customAdapter);
    }

    private void prepareListData() {
        String [] headerData = getResources().getStringArray(R.array.abbreviation_list_hader);
        String [] childData = getResources().getStringArray(R.array.abbreviation_list_child);

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();

        for (int i=0;i<headerData.length;i++){
            //add header data
            listDataHeader.add(headerData[i]);

            //add child data
            List<String> child = new ArrayList<>();
            child.add(childData[i]);

            listDataChild.put(listDataHeader.get(i),child);

        }
    }
}
