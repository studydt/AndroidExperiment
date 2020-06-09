package com.example.experiment2;

import android.app.ListActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Bundle;

import java.util.List;
import java.util.Map;

public class MainActivity extends ListActivity {
    private Sql sql;
    private EditText name, age, length,id;
    private ListView listView;
    private Button add, delete, update, select;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =findViewById(R.id.editName);
        age = findViewById(R.id.editAge);
        length = findViewById(R.id.editLength);
        id  = findViewById(R.id.editId);

        add =findViewById(R.id.Add);
        delete = findViewById(R.id.Delete);
        update = findViewById(R.id.Update);
        select = findViewById(R.id.Select);

        listView = findViewById(android.R.id.list);
        sql = new Sql(this);

        listViewAdapter = new ListViewAdapter(this);
        listView.setAdapter(listViewAdapter);

        ClickListener clickListener = new ClickListener(this, sql, name, age, length,id,listViewAdapter);
        add.setOnClickListener(clickListener);
        delete.setOnClickListener(clickListener);
        update.setOnClickListener(clickListener);
        select.setOnClickListener(clickListener);
        sql.open();

    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(this, "选中成功", Toast.LENGTH_LONG).show();
        List<Map<String, Object>> list = listViewAdapter.getList();
        this.id.setText(String.valueOf(list.get(position).get("id")));
        name.setText((String) list.get(position).get("name"));
        age.setText(String.valueOf(list.get(position).get("age")));
        length.setText(String.valueOf(list.get(position).get("length")));
    }

}
