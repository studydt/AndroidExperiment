package com.example.experiment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.experiment2.Bean.Student;

public class Sql {
    private final Context context;
    SQLiteDatabase db;
    private DBHelper dbHelper;

    private final static String DB_TABLE="studentinfo";
    private final static String DB_NAME="student.db";
    private final static int DB_VERSION=1;

    private final static String KEY_ID="id";
    private final static String KEY_NAME="Name";
    private final static String KEY_AGE="Age";
    private final static String KEY_LENGTH="Length";

    public Sql(Context context) {
        this.context = context;
    }
    //关闭数据库的连接
    public void close() {
        if(db!=null) {
            db.close();
            db=null;
        }
    }
    public void open() throws SQLiteException {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        try{
            db = dbHelper.getWritableDatabase();
        }catch(Exception e) {
            db  = dbHelper.getReadableDatabase();
        }
    }

    //插入数据
    public long insert(Student s) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,s.getName() );
        cv.put(KEY_AGE, s.getAge());
        cv.put(KEY_LENGTH, s.getLength());
        return db.insert(DB_TABLE, null, cv);
    }

    public long deleteAllStudent() {
        return db.delete(DB_TABLE, null, null);
    }
    //这种方发比较好能够防止sql注入
    public long deleteById(String Id) {
        return db.delete(DB_TABLE, "Id=?", new String[]{Id});
    }

    public long updateOneStudentById(String Id, Student s) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME,s.getName() );
        cv.put(KEY_AGE, s.getAge());
        cv.put(KEY_LENGTH, s.getLength());

        return db.update(DB_TABLE, cv, "Id="+Id, null);
    }
    //查询所有的数据
    public Student[] findAllStudent() {
        Cursor c = db.query(DB_TABLE, null, null, null, null,null, null);
        return  convertToStudent(c);
    }
    public Student[] findOneStudentById(String id) {
        Cursor c = db.query(DB_TABLE, null, "Id="+id, null, null, null, null);
        return convertToStudent(c);
    }
    //转换函数
    public Student[] convertToStudent(Cursor c) {
        int resultsCount = c.getCount();
        if(resultsCount==0||!c.moveToFirst()) {
            return null;
        }
        Student []stu = new Student[resultsCount];
        for (int i = 0; i < stu.length; i++) {
            stu[i] = new Student();
            String Name  = c.getString(c.getColumnIndex("Name"));
            int Age = c.getInt(c.getColumnIndex("Age"));
            float Length = c.getFloat(c.getColumnIndex("Length"));
            stu[i].id=c.getInt(0);
            stu[i].setName(Name);
            stu[i].setAge(Age);
            stu[i].setLength(Length);
            //切记不可少了这一句，这个是循环的条件
            c.moveToNext();
        }
        return stu;
    }

    //内部的静态类用于创建数据库和建立数据库链接
    private static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            // TODO Auto-generated constructor stub
        }

        private static final String SQL="CREATE TABLE studentinfo ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "Name TEXT,"
                + "Age INT,"
                + "Length FLOAT"
                + ")";

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL(SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
            onCreate(db);
        }
    }
}

