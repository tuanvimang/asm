package com.example.asm_tkgd.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_tkgd.fragment.KhoanFragment;
import com.example.asm_tkgd.fragment.LoaiFragment;
import com.example.asm_tkgd.models.Khoan;

public class viewPager2Adapter extends FragmentStateAdapter {
    private int trangThai;
    public viewPager2Adapter(@NonNull FragmentActivity fragmentActivity, int trangThai) {
        super(fragmentActivity);
        this.trangThai = trangThai;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("trangThai",trangThai);
        if(position == 0){
            LoaiFragment loaiFragment = new LoaiFragment();

            loaiFragment.setArguments(bundle);
            return loaiFragment;

        }

        KhoanFragment khoanFragment = new KhoanFragment();
        khoanFragment.setArguments(bundle);
        return khoanFragment  ;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
