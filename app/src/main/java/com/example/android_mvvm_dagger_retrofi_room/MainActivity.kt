package com.example.android_mvvm_dagger_retrofi_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.AppViewModelFactory
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel : MainViewModel

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    private val text :TextView
    get() = findViewById(R.id.text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as ProjectApplication).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, appViewModelFactory)[MainViewModel::class.java]

        mainViewModel.cityLiveData.observe(this, Observer {
            text.text = it.message.toString()
            Log.d("SHIMUL ",it.toString())
        })
    }

    companion object{
        const val  pass = "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"
    }
}