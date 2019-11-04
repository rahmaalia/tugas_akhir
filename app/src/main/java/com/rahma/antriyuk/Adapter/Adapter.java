package com.rahma.antriyuk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rahma.antriyuk.R;
import com.rahma.antriyuk.modelVp;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<modelVp> model;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<modelVp> model, Context context){
        this.model = model;
        this.context =  context;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = layoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item,container,false);

        ImageView imageView;

        imageView = view.findViewById(R.id.iv_klinik_anak);

        imageView.setImageResource(model.get(position).getImage());
        container.addView(view,0);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
