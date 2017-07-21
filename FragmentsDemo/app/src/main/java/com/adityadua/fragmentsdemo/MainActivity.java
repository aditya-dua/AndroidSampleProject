package com.adityadua.fragmentsdemo;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity
        implements HeadlineFragment.OnHeadlineSelectedListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_article);

        // This code is to set the Headline Fragment &
        // once user clicks on the Headline , it will activate the listener &
        // onArtcileSelected will be called
        if(findViewById(R.id.fragment_container) !=null){

            if(savedInstanceState !=null){
                return;
            }
            HeadlineFragment firstFragment = new HeadlineFragment();
            firstFragment.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container,firstFragment).commit();
        }
    }

    @Override
    public void onArticleSelected(int position) {


        ArticleFragment articleFrag = (ArticleFragment)
               getSupportFragmentManager().findFragmentById(R.id.article_fragment);
        if(articleFrag !=null){
            articleFrag.updateArticleView(position);
        }else{
            ArticleFragment newFragment = new ArticleFragment();
            Bundle args=new Bundle();
            args.putInt(ArticleFragment.ARG_POSITION,position);
            newFragment.setArguments(args);

            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container,newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }


    }
}
