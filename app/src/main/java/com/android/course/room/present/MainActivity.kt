package com.android.course.room.present

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.course.room.R
import com.android.course.room.present.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = MainFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.main_fragment_conteiner_view, fragment)
                .commit()
        }
    }
}