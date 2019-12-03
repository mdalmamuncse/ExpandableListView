package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

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

        //Hader click listener
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent,
                                        View v, int groupPosition, long id) {
                String groupName = listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this, groupName+"click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //Header collapsed listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String groupName = listDataHeader.get(groupPosition);
                Toast.makeText(MainActivity.this, groupName+"is collapsed", Toast.LENGTH_SHORT).show();

            }
        });

        //child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String childName = listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(MainActivity.this, childName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
