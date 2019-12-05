package com.projects.bakota.contactapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.Contacts;
import Adapters.CustomAdapters;
import dao.DatabaseAdapter;

public class SearchResultsActivity extends AppCompatActivity {

    ListView resultList;
    TextView not_found_txt;
    DatabaseAdapter db;
    CustomAdapters mAdapter;
    ArrayList<Contacts> mContacts = new ArrayList<Contacts>();

    String search="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        handleIntent(getIntent());

        db = new DatabaseAdapter(this);
        resultList = (ListView) findViewById(R.id.result_list);
        not_found_txt = (TextView) findViewById(R.id.not_found);

        db.open();
        mContacts = db.getContacts(search);
        if(mContacts.size() == 0) not_found_txt.setVisibility(View.VISIBLE);
        else {
            mAdapter = new CustomAdapters(mContacts,this);
            resultList.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            Toast.makeText(SearchResultsActivity.this,query,Toast.LENGTH_LONG).show();
            search = query;
        }
    }
}
