package com.example.asm_tkgd.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asm_tkgd.fragment.MeFragment;
import com.example.asm_tkgd.fragment.ThayFragment;

public class ViewPager2GTAdapter extends FragmentStateAdapter {
    public ViewPager2GTAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            MeFragment meFragment = new MeFragment();
            return meFragment;
        }
        ThayFragment thayFragment = new ThayFragment();
        return thayFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
