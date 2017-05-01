package com.example.tylerpelaez.didit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by tylerpelaez on 4/30/17.
 */

public class CreateHabitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_habit_view);

        if (savedInstanceState == null) {

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.create_habit_container, new CreateHabitFragment(), "CreateHabitFragment")
                    .commit();
        }

    }
}

