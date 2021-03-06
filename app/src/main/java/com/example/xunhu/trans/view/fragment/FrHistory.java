package com.example.xunhu.trans.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.xunhu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.xunhu.trans.ActMain.manager;


public class FrHistory extends Fragment {

    @BindView(R.id.historys_searchView)
    SearchView mSearchView;


    private FrTrans frTrans;
    private FrHome frHome;
    private FrPersonal frPersonal;

    private FrHistory frHistory;
    private static String TAG = "FrHistory";
    private  String word;
    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_history, container, false);
        unbinder = ButterKnife.bind(this, view);                    //fragment中使用BUtterKnife
        initView();
        return view;
    }

    /**
     * 初始化布局
     */
    private void initView(){

        //隐藏RearchView的搜索图标
        int magId = getResources().getIdentifier("android:id/search_mag_icon",null, null);
        ImageView magImage =  mSearchView.findViewById(magId);
        magImage.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        mSearchView.setSubmitButtonEnabled(true);  //设置显示搜索按钮
        mSearchView.setIconifiedByDefault(false); //设置不自动缩小为图标，点搜索框就出现软键盘

        //reachView点击事件
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                FragmentTransaction ft1 = manager.beginTransaction();
//                hideAll(ft1);
                if (frTrans==null){                          //新建实例前先判断实例是否为null
                    frTrans=FrTrans.newInstance(query);
                }

                ft1.replace(R.id.frame_layout,frTrans);
                ft1.addToBackStack(null);
                ft1.commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        if (unbinder != null) {
            unbinder.unbind();
        }
        super.onDestroyView();
    }

    /**
     * 隐藏所有fragment
     * @param ft FragmentTransaction对象
     */
    private void hideAll(FragmentTransaction ft){
        if (ft==null){
            return;
        }
        if (frHome!=null){
            ft.hide(frHome);
        }
        if (frTrans !=null){
            ft.hide(frTrans);
        }
        if (frPersonal!=null){
            ft.hide(frPersonal);
        }if(frHistory!=null){
            ft.hide(frHistory);
        }
    }

}
