package com.example.android_mvvm_dagger_retrofi_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.android_mvvm_dagger_retrofi_room.databinding.ActivityDataShowingBinding
import com.example.android_mvvm_dagger_retrofi_room.models.AqiInfo
import com.example.android_mvvm_dagger_retrofi_room.models.Station
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.AppViewModelFactory
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
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

        val cityName = intent.getStringExtra("cityName")

        mainViewModel.searchData.value = cityName

        if(!cityName.isNullOrBlank()){
            lifecycleScope.launch {
                mainViewModel.getStationAndAqiInfo()
                mainViewModel.getAqiInfo()
            }
        }

        mainViewModel.aqiData.observe(this, Observer {
//            binding.s.text = it?.category
//            Log.d("LALALLALALA",it.category)
//            binding.s1.text = mainViewModel.stationData[0].updatedAt
            mainViewModel.placeName.value = mainViewModel.stationData[0].placeName

        })


    }
}