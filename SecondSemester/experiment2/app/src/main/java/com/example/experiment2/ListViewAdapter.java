package com.example.experiment2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.experiment2.Bean.Student;
import com.example.experiment2.Bean.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewAdapter extends BaseAdapter {
    private List<Map<String, Object>> list;
    private LayoutInflater layoutInflater;

    public ListViewAdapter(Context context) {
        this.layoutInflater = LayoutInflater.from(context);
    }

    public List<Map<String, Object>> getData(Student[] students) {
        list = new ArrayList<Map<String, Object>>();
        if (students == null){
            Map map = new HashMap<String, Object>();
            map.put("id",null);
            map.put("name", null);
            map.put("age", null);
            map.put("length", null);
            return list;
        }
        for (Student student : students) {
            Map map = new HashMap<String, Object>();
            map.put("id",student.getId());
            map.put("name", student.getName());
            map.put("age", student.getAge());
            map.put("length", student.getLength());
            list.add(map);
        }
        return list;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        } else {
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.textview, null);
            viewHolder = new ViewHolder((TextView) convertView.findViewById(R.id.Tid),(TextView) convertView.findViewById(R.id.Tname), (TextView) convertView.findViewById(R.id.Tage), (TextView) convertView.findViewById(R.id.Tlength));
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.getTid().setText(String.valueOf(list.get(position).get("id")) );
        viewHolder.getTname().setText((String) list.get(position).get("name"));
        viewHolder.getTage().setText(String.valueOf(list.get(position).get("age")));
        viewHolder.getTlength().setText(String.valueOf(list.get(position).get("length")));
        return convertView;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

}
