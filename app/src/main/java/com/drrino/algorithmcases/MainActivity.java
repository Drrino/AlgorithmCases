package com.drrino.algorithmcases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] a = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 1 };
        SortUtils.ChooseSort(a);
    }
}
