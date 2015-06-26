package com.myfirst.lingaraj.theholybible;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by lingaraj on 6/23/2015.
 */
public class bible_cover extends Fragment {
    public ImageView Bible_cover;
    public DrawerLayout mdrawerlayout;

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       // return super.onCreateView(inflater, container, savedInstanceState);
        View myrootview= inflater.inflate(R.layout.bible_cover_layout,container,false);
        Bible_cover=(ImageView) myrootview.findViewById(R.id.home_heaven);
        mdrawerlayout=(DrawerLayout) getActivity().findViewById(R.id.mydrawerlayout);


        Bible_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bible_home newobj = new Bible_home();
                try {
                   //  mdrawerlayout=newobj.mydrawer;


                   mdrawerlayout.openDrawer(Gravity.LEFT);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return  myrootview;
    }





}
