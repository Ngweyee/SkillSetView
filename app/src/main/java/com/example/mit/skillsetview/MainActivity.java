package com.example.mit.skillsetview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.Collection;

public class MainActivity extends AppCompatActivity
        implements TagsEditText.TagsEditListener {

    private static final String TAG = "MainActivity";
    private TagsEditText mTagsEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTagsEditText = (TagsEditText) findViewById(R.id.tagsEditText);
        mTagsEditText.setHint("Enter names of fruits");
        mTagsEditText.setTagsBackground(R.drawable.oval);
        mTagsEditText.setTagsListener(this);
        mTagsEditText.setTagsWithSpacesEnabled(true);
        mTagsEditText.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, getResources().getStringArray(R.array.fruits)));
        mTagsEditText.setThreshold(1);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            mTagsEditText.showDropDown();
        }
    }


    @Override
    public void onTagsChanged(Collection<String> tags) {
        Log.d(TAG, "Tags changed: ");
        Log.d(TAG, Arrays.toString(tags.toArray()));
    }

    @Override
    public void onEditingFinished() {
        Log.d(TAG, "OnEditing finished");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mTagsEditText.getWindowToken(), 0);
        mTagsEditText.clearFocus();
    }


}
