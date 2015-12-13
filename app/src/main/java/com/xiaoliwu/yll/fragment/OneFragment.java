package com.xiaoliwu.yll.fragment;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.xiaoliwu.yll.R;
import com.xiaoliwu.yll.activity.Hot_CommodityDetailActivity;
import com.xiaoliwu.yll.adapter.Adaptergong;
import com.xiaoliwu.yll.json.HotBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private Adaptergong adaptergong;
    private SQLiteDatabase writableDatabase;
    private List<String> list=new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout1, container, false);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.lv1);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onResume() {
        super.onResume();
//        MySqlOpenHelper mySqlOpenHelper = new MySqlOpenHelper(getActivity());
//        writableDatabase = mySqlOpenHelper.getWritableDatabase();
//        Cursor query = writableDatabase.query("yuhaibo", null, null, null, null, null, null);
//        while (query.moveToNext()) {
//            String imagepath = query.getString(query.getColumnIndex("imagepath"));
//            list.add(imagepath);
//        }
        adaptergong = new Adaptergong(OneFragment.this);
        listView.setAdapter(adaptergong);
        adaptergong.notifyDataSetChanged();
//        this.registerForContextMenu(listView);//注册上下文菜单
        Log.e("asdfsdf", "onResume");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HotBean item = (HotBean) adaptergong.getItem(position);
        Intent intent = new Intent(getActivity(), Hot_CommodityDetailActivity.class);
        String path = item.getPath();
        Log.i("test", "onItemClick ----" + path);
        Bundle bundle = new Bundle();
        bundle.putString("path", path);
        intent.putExtras(bundle);
        startActivity(intent);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        getActivity().getMenuInflater().inflate(R.menu.shoucang_delete, menu);
//        super.onCreateContextMenu(menu, v, menuInfo);
//    }
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int position=menuInfo.position;
//        String s = list.get(position);
//        switch (item.getItemId()) {
//            case R.id.item2:
//                writableDatabase.delete("yuhaibo", "imagepath=?", new String[]{s});
//                list.remove(position);
//                adaptergong.notifyDataSetChanged();
////                Toast.makeText(this, "删除了"+position, Toast.LENGTH_SHORT).show();
//                break;
//        }
//        return super.onContextItemSelected(item);
//    }


}
