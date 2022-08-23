package com.example.multiplatform.koru.androidApp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.multiplatform.koru.shared.viewmodel.CountdownViewModel
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val tv = findViewById<TextView>(R.id.text_view)

        val viewModel = ViewModelProvider(this)[CountdownViewModel::class.java]
        viewModel.viewModelScope.launch {
            viewModel.countdown.collect {
                tv.text = it
            }
        }
    }
}
