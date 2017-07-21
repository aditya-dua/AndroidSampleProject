package com.adityadua.fragmentsdemo;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by AdityaDua on 20/07/17.
 */
// TextView
public class ArticleFragment extends Fragment {
    final static String ARG_POSITION="position";

    int mCurrentposition=-1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(R.layout.article_view, container, savedInstanceState);
        if(savedInstanceState !=null){
            mCurrentposition =savedInstanceState.getInt(ARG_POSITION);
        }

        return inflater.inflate(R.layout.article_view,container,false);

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle args=getArguments();
        if(args !=null){
            updateArticleView(args.getInt(ARG_POSITION));
            //
        }else if(mCurrentposition != -1){
            updateArticleView(mCurrentposition);
        }

    }

    public void updateArticleView(int position){
        TextView article = (TextView) getActivity().findViewById(R.id.article);

        article.setText(Ipsum.articles[position]);
        mCurrentposition=position;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ARG_POSITION,mCurrentposition);

    }
}
