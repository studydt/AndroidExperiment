package com.example.experiment2;

import android.view.View;
import android.widget.EditText;

import android.widget.Toast;
import com.example.experiment2.Bean.Student;

public class ClickListener implements View.OnClickListener {
    private EditText name, age, length, id;
    private ListViewAdapter listView;
    private Sql sql;
    private MainActivity mainActivity;

    ClickListener(MainActivity mainActivity, Sql sql, EditText name, EditText age, EditText length, EditText id, ListViewAdapter listView) {
        this.mainActivity = mainActivity;
        this.sql = sql;
        this.name = name;
        this.age = age;
        this.length = length;
        this.id = id;
        this.listView = listView;
    }

    public Student GetStudent() {
        Student ss = new Student();
        int Id =0;
        if(!id.getText().toString().equals("")) {
            Id = Integer.parseInt(id.getText().toString());
        }
        String Name = name.getText().toString();
        int Age = Integer.parseInt(age.getText().toString());
        float Length = Float.parseFloat(length.getText().toString());
        ss.setId(Id);
        ss.setName(Name);
        ss.setAge(Age);
        ss.setLength(Length);

        //清空上一次的文本框输入的数据
        id.setText("");
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
        }
        listView.getData(s);
        listView.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        String _id = id.getText().toString();
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
                if (_id.equals("")) {
                    SetStudent();
                } else {
                    Student[] s = sql.findOneStudentById(_id);
                    if (s == null) {
                        Toast.makeText(mainActivity, "数据库中不存在数据", Toast.LENGTH_LONG).show();
                    } else {
                        for (Student student : s) {
                            Toast.makeText(mainActivity, student.getId() + " " + student.getName() + " " + student.getAge() + " " + student.getLength(), Toast.LENGTH_LONG).show();
                        }
                        listView.getData(s);
                        listView.notifyDataSetChanged();
                    }
                    break;
                }
                break;
            }
            //删除数据库中的所有数据操作
            case R.id.Delete: {
                if (_id.equals("")) {
                    sql.deleteAllStudent();
                } else {
                    sql.deleteById(_id);
                }
                SetStudent();
                break;
            }
            case R.id.Update: {
                if ("".equals(_id)) {
                    Toast.makeText(mainActivity, "id不能为空", Toast.LENGTH_LONG).show();
                    break;
                }
                sql.updateOneStudentById(_id, GetStudent());
                SetStudent();
                break;
            }
            default:
                break;
        }
    }
}
