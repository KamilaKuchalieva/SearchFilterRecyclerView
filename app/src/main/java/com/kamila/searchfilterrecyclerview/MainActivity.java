package com.kamila.searchfilterrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.kamila.searchfilterrecyclerview.adapters.CelebrityRecyclerViewAdapter;
import com.kamila.searchfilterrecyclerview.models.Celebrity;
import com.kamila.searchfilterrecyclerview.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private CelebrityRecyclerViewAdapter mAdapter;
    private EditText searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchBox = (EditText)findViewById(R.id.search_box);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.item_list);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));

        mAdapter = new CelebrityRecyclerViewAdapter(this, getListItemData());
        recyclerView.setAdapter(mAdapter);

        // search suggestions using the edittext widget
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private List<Celebrity> getListItemData(){
        List<Celebrity> celebrities = new ArrayList<Celebrity>();
        celebrities.add(new Celebrity("Tom Hardy", "https://www.biography.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cg_face%2Cq_80%2Cw_300/MTE5NTU2MzE2NjUyOTk2MTA3/9th-annual-ves-awards---red-carpet.jpg"));
        celebrities.add(new Celebrity("Kit Harington", "http://www.unitedagents.co.uk/sites/default/files/styles/custom_crop/public/thumbnails/image/ms12805_1.jpg?itok=SsrIVc2d"));
        celebrities.add(new Celebrity("Tom Cruise", "https://images-na.ssl-images-amazon.com/images/M/MV5BMTk1MjM3NTU5M15BMl5BanBnXkFtZTcwMTMyMjAyMg@@._V1_UY317_CR14,0,214,317_AL_.jpg"));
        celebrities.add(new Celebrity("Brad Pitt", "http://skolkolet.com/images/590e5ad27d66ac348e88c0da/brad-pitt.jpg"));
        celebrities.add(new Celebrity("Keanu Reeves", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Keanu_Reeves_2014.jpg/220px-Keanu_Reeves_2014.jpg"));
        celebrities.add(new Celebrity("Tom Hanks", "https://www.biography.com/.image/t_share/MTE1ODA0OTcxNjUxNTk3ODM3/tom-hanks-9327661-1-402.jpg"));

        return celebrities;
    }
}