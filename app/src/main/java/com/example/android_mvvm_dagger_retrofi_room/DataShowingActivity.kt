package com.example.android_mvvm_dagger_retrofi_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_mvvm_dagger_retrofi_room.databinding.ActivityDataShowingBinding
import com.example.android_mvvm_dagger_retrofi_room.models.AqiInfo
import com.example.android_mvvm_dagger_retrofi_room.models.Station
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.AppViewModelFactory
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class DataShowingActivity : AppCompatActivity() {

    lateinit var mainViewModel : MainViewModel
    private lateinit var binding : ActivityDataShowingBinding

    @Inject
    lateinit var appViewModelFactory : AppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_data_showing)

        (application as ProjectApplication).applicationComponent.injectDataShowingActivity(this)

        mainViewModel = ViewModelProvider(this, appViewModelFactory)[MainViewModel::class.java]

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        val aqi = intent.getSerializableExtra("aqi") as AqiInfo?

        val myList = intent.extras?.getParcelableArrayList<Station>("station")
        binding.s.text = aqi!!.category
        binding.s.text = myList?.get(0)?.updatedAt
        Log.d("SHIMUL DATASHOWING", aqi.category)
        Log.d("SHIMUL DATASHOWING", myList?.get(0)?.updatedAt.toString())
        mainViewModel.aqiData.observe(this,{
            Log.d("SHIMUL DATASHOWING", it.category)
        })

    }
}