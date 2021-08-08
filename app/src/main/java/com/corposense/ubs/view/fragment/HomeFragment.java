/*
 * Copyright (c) 2017. http://hiteshsahu.com- All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * If you use or distribute this project then you MUST ADD A COPY OF LICENCE
 * along with the project.
 *  Written by Hitesh Sahu <hiteshkrsahu@Gmail.com>, 2017.
 */

package com.corposense.ubs.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.corposense.ubs.R;
import com.corposense.ubs.domain.api.ProductCategoryLoaderTask;
import com.corposense.ubs.util.Utils;
import com.corposense.ubs.util.Utils.AnimationType;
import com.corposense.ubs.view.activities.ECartHomeActivity;

public class HomeFragment extends Fragment {
    int mutedColor = R.attr.colorPrimary;
    //private CollapsingToolbarLayout collapsingToolbar;
    private RecyclerView recyclerView;
    /**
     * The double back to exit pressed once.
     */
    private boolean doubleBackToExitPressedOnce;
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };
    private Handler mHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_product_category, container, false);

        view.findViewById(R.id.search_item).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Utils.switchFragmentWithAnimation(R.id.frag_container,
                                new SearchProductFragment(),
                                ((ECartHomeActivity) getActivity()), null,
                                AnimationType.SLIDE_UP);

                    }
                });

        final Toolbar toolbar = (Toolbar) view.findViewById(R.id.anim_toolbar);
        ((ECartHomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((ECartHomeActivity) getActivity()).getSupportActionBar()
                .setDisplayHomeAsUpEnabled(true);
        ((ECartHomeActivity) getActivity()).getSupportActionBar()
                .setTitle(getResources().getText(R.string.toolbar_categories_title));
        toolbar.setNavigationIcon(R.drawable.ic_drawer);
        toolbar.setNavigationOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ECartHomeActivity) getActivity()).getmDrawerLayout()
                        .openDrawer(GravityCompat.START);
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.scrollableview);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        new ProductCategoryLoaderTask(recyclerView, getActivity()).execute();

//
//		if (simpleRecyclerAdapter == null) {
//			simpleRecyclerAdapter = new CategoryListAdapter(getActivity());
//			recyclerView.setAdapter(simpleRecyclerAdapter);
//
//			simpleRecyclerAdapter
//					.SetOnItemClickListener(new OnItemClickListener() {
//
//						@Override
//						public void onItemClick(View view, int position) {
//
//							if (position == 0) {
//								CenterRepository.getCenterRepository()
//										.getAllElectronics();
//							} else if (position == 1) {
//								CenterRepository.getCenterRepository()
//										.getAllFurnitures();
//							}
//							Utils.switchFragmentWithAnimation(
//									R.id.frag_container,
//									new ProductOverviewFragment(),
//									((ECartHomeActivity) getActivity()), null,
//									AnimationType.SLIDE_LEFT);
//
//						}
//					});
//		}

        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP
                        && keyCode == KeyEvent.KEYCODE_BACK) {

                    if (doubleBackToExitPressedOnce) {
                        // super.onBackPressed();

                        if (mHandler != null) {
                            mHandler.removeCallbacks(mRunnable);
                        }

                        getActivity().finish();

                        return true;
                    }

                    doubleBackToExitPressedOnce = true;
                    Toast.makeText(getActivity(),
                            "Please click BACK again to exit",
                            Toast.LENGTH_SHORT).show();

                    mHandler.postDelayed(mRunnable, 2000);

                }
                return true;
            }
        });

        return view;

    }

}
