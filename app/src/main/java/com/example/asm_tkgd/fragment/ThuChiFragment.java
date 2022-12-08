package com.example.asm_tkgd.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.asm_tkgd.R;
import com.example.asm_tkgd.adapters.viewPager2Adapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThuChiFragment extends Fragment {
    private int trangThai;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi,container,false);
        Bundle bundle = getArguments();
        trangThai = bundle.getInt("trangThai");
        Toast.makeText(getContext(), ""+trangThai, Toast.LENGTH_SHORT).show();
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);
        Object viewPager2Adapter;
        viewPager2Adapter adapter = new viewPager2Adapter(getActivity(),trangThai);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0 ){
                    if(trangThai==0){
                        tab.setText("Loại thu");
                    }else{
                        tab.setText("Loại chi");
                    }
                }else {
                    if(trangThai==0){
                        tab.setText("Khoản thu");
                    }else{
                        tab.setText("Khoản chi");
                    }
                }
            }
        }).attach();

        return view;




    }
}
