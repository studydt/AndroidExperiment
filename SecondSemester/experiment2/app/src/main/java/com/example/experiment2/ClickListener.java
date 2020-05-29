package com.example.experiment2;

import android.app.ListActivity;
import android.view.View;
import android.widget.EditText;

import android.widget.ListView;
import android.widget.Toast;

public class ClickListener implements View.OnClickListener {
    private EditText name, age, length;
    private ListViewAdapter listView;
    private Sql sql;
    private MainActivity mainActivity;

    ClickListener(MainActivity mainActivity, Sql sql, EditText name, EditText age, EditText length, ListViewAdapter listView) {
        this.mainActivity = mainActivity;
        this.sql = sql;
        this.name = name;
        this.age = age;
        this.length = length;
        this.listView = listView;
    }

    public Student GetStudent() {
        Student ss = new Student();
        String Name = name.getText().toString();
        int Age = Integer.parseInt(age.getText().toString());
        float Length = Float.parseFloat(length.getText().toString());
        ss.setName(Name);
        ss.setAge(Age);
        ss.setLength(Length);

        //清空上一次的文本框输入的数据
        name.setText("");
        age.setText("");
        length.setText("");
        return ss;
    }

    public void SetStudent() {
        //查询跟新的数据 显示出来
        Student[] s = sql.findAllStudent();
        if (s == null) {
            Toast.makeText(mainActivity, "数据库中不存在数据", Toast.LENGTH_LONG).show();
        } else {
            listView.getData(s);
            listView.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String _name = name.getText().toString();
        switch (v.getId()) {
            //添加数据
            case R.id.Add: {
                sql.insert(GetStudent());
                Toast.makeText(mainActivity, "添加成功", Toast.LENGTH_LONG).show();
                SetStudent();
                break;
            }
            //查询到数据库中所有的数据
            case R.id.Select: {
                if (_name.equals("")) {
                    SetStudent();
                } else {
                    Student[] s = sql.findOneStudentByName(_name);
                    if (s == null) {
                        Toast.makeText(mainActivity, "数据库中不存在数据", Toast.LENGTH_LONG).show();
                    } else {
                        for(Student student:s){
                            Toast.makeText(mainActivity, student.getId()+" "+student.getName()+" "+student.getAge()+" "+student.getLength(), Toast.LENGTH_LONG).show();
                        }
                        listView.getData(s);
                        listView.notifyDataSetChanged();
                    }
                }

                break;
            }
            //删除数据库中的所有数据操作
            case R.id.Delete: {
                if (_name.equals("")) {
                    sql.deleteAllStudent();
                } else {
                    sql.deleteByName(_name);
                }
                SetStudent();
                break;
            }
            case R.id.Update: {
                if ("".equals(_name)) {
                    Toast.makeText(mainActivity, "name不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                sql.updateOneStudentByName(_name, GetStudent());
                SetStudent();
                break;
            }
            default:
                break;
        }
    }


}
