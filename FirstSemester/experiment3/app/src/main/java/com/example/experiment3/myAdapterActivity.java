package com.example.experiment3;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class myAdapterActivity extends ListActivity {
    //ListView 的底层数据对象变量
    private List<Map<String,Object>> list;
    private MyAdapter adapter;
    private List<Integer> selectedItem;
    private static final int ITEM1=Menu.FIRST;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=getData();
        adapter=new MyAdapter(this);
        setListAdapter(adapter);
        registerForContextMenu(getListView());
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("操作");
        menu.add(0,ITEM1,0,"删除所选项");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case ITEM1:
                if (selectedItem != null){
                    for(Integer i:selectedItem){
                        list.remove(list.get(i));
                    }
                    adapter.notifyDataSetChanged();
                    selectedItem.clear();
                    Toast.makeText(myAdapterActivity.this, "删除", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(myAdapterActivity.this, "未选择", Toast.LENGTH_SHORT).show();
                }
            return true;
        }
        return false;
    }
    private List<Map<String,Object>> getData(){
        //List对象
        list=new ArrayList<Map<String,Object>>();
        //List中存放的Map对象,由多个<键,值>对构成--一个Map对象对应ListView中的一行
        Map map;
        map=new HashMap<String,Object>();
        map.put("title", "雨幕");
        map.put("info", "在这潇潇的雨幕里");
        map.put("img", R.drawable.a);
        list.add(map);
        return list;
    }

    /**Android系统更新ListView时需要调用相关的Adapter的方法:
     *      1)更新前首先调用getCount()获取需要更新的行数,然后更新过程逐行进行
     *      2)更新每行时,需要调用getView()获取当前行对应的View对象，
     *            Adapter需要在getView()方法中适时创建View对象，并对View对象填充需要显示的内容
     * */
    public final class MyAdapter extends BaseAdapter{
        //实例化布局对象--用于实例化每行的布局->View对象
        private LayoutInflater mInflater;
        public MyAdapter(Context context) {
            this.mInflater=LayoutInflater.from(context);
        }
        //获取item的个数
        @Override
        public int getCount() {
            return list.size();
        }
        @Override
        public Object getItem(int i) {
            return null;
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }

        //ListView中一行对应的对象组合--容器类
        //使用ViewHolder可以减少findViewById()的使用频率,方便数据访问
        public final class ViewHolder{
            public ImageView img;
            public TextView title;
            public TextView info;
            public Button viewBtn;
            public CheckBox checkBox;
        }
        //每绘制一行调用一次getView方法
        //获取指定的一行所对应的View对象--不存在的话则创建之
        // position--当前要显示的数据的位置(行号)
        // convertView--可利用的以前的View对象(上下滚动时,利用旧View对象显示新内容),
        //              如果此项为空,则需要动态创建新的View对象
        // parent--父控件(上层的ListView)
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //本行对应的容器对象
            ViewHolder holder = null;
            //如果该行的View为空, 则动态创建新的View
            //利用已有的View显示新数据,可以减少内存占用,优化响应速度
            if (convertView == null) {
                //先创建容器对象,以便后来向其中填充当前行中的控件对象
                holder=new ViewHolder();
                //实例化ListView的一行, root参数为空说明此View的父控件默认为为上层的ListView
                convertView = mInflater.inflate(R.layout.myitem, null);
                //获取内部的各个控件对象, 保存到容器对象中, 以后直接取来用即可--每个子控件对象只用一次findViewById()
                holder.img = (ImageView)convertView.findViewById(R.id.img);
                holder.title = (TextView)convertView.findViewById(R.id.title);
                holder.info = (TextView)convertView.findViewById(R.id.info);
                holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
                holder.checkBox=(CheckBox) convertView.findViewById(R.id.checkbox1);
                //设置容器对象为ListView当前行的Tag--建立容器类对象与ListView当前行的联系
                convertView.setTag(holder);
            }
            else {   //如果该行的View已经存在,则通过标记获取该行对应的对象
                holder = (ViewHolder)convertView.getTag();
            }
            //设置该行内的控件对象对应的属性，Adapter的作用（List<--->ListView）--- 如果不用ViewHolder则需要频繁使用findViewByID
            holder.img.setBackgroundResource((Integer)list.get(position).get("img"));
            holder.title.setText((String)list.get(position).get("title"));
            holder.info.setText((String)list.get(position).get("info"));
            //绑定该行中的Button对象的监听器
            //创建监听器对象时, 用参数传递当前的行号
            //每行中的Button建一个监听器对象,不同对象的position值不同
            holder.viewBtn.setOnClickListener(new viewButtonClickListener(position)) ;
            holder.checkBox.setOnClickListener(new checkboxClickListener(position));
            if(selectedItem==null||!selectedItem.contains(position)){
                holder.checkBox.setChecked(false);
            }else if(selectedItem.contains(position)){
                holder.checkBox.setChecked(true);
            }
            return convertView;//返回当前行对应的View对象
        }
    }
    //重写此方法---点击ListView一行时的回调函数--参数含义同前
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String s=list.get(position).get("title").toString(); //获取该行的Map对象的指定属性的数据内容
        Toast.makeText(myAdapterActivity.this, s, Toast.LENGTH_SHORT).show();
    }
    class viewButtonClickListener implements View.OnClickListener{
        int position;
        public viewButtonClickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View view) {
            //获取该行的具体列的内容，并显示之
            String info=list.get(position).get("info").toString();
            Toast.makeText(myAdapterActivity.this,info,Toast.LENGTH_SHORT).show();
        }
    }
    class checkboxClickListener implements View.OnClickListener{
        int position;
        public checkboxClickListener(int position) {
            this.position = position;
        }
        @Override
        public void onClick(View view) {
            if(selectedItem==null){
                selectedItem= new ArrayList<>();
            }
            selectedItem.add(position);
            Toast.makeText(myAdapterActivity.this, "选中了第"+String.valueOf(position)+"行", Toast.LENGTH_SHORT).show();
        }
    }
}