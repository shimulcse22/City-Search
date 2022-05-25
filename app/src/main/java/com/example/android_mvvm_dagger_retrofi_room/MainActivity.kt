package com.example.android_mvvm_dagger_retrofi_room

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.provider.ContactsContract
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.android_mvvm_dagger_retrofi_room.databinding.ActivityMainBinding
import com.example.android_mvvm_dagger_retrofi_room.models.Station
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.AppViewModelFactory
import com.example.android_mvvm_dagger_retrofi_room.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    lateinit var mainViewModel : MainViewModel

    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        (application as ProjectApplication).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, appViewModelFactory)[MainViewModel::class.java]

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        mainViewModel.cityLiveData.observe(this, Observer {

        })


        setAdapter()

        binding.autoCompleteText.setOnItemClickListener { adapterView, _, i, _ ->
            mainViewModel.searchData.value = adapterView.getItemAtPosition(i).toString()

            lifecycleScope.launch {
                Log.d("SHIMUL","mainViewModel.stationAndAqiData[0].station.placeName")
                mainViewModel.getStationAndAqiInfo()
                mainViewModel.getAqiInfo()
                moveToOtherActivity()
            }
        }

        binding.image.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.getCityFromAPi()
            }

            moveToOtherActivity()
        }
    }

    companion object{
        const val  pass = "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"
    }

    private fun setAdapter(){
        val arrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,mainViewModel.cityLiveDataFromDataBase)
        binding.autoCompleteText.setAdapter(arrayAdapter)
        binding.autoCompleteText.threshold = 1
        binding.autoCompleteText.setAdapter(arrayAdapter)
    }
    private fun moveToOtherActivity(){
        Log.d("SHIMUL FINASLLLLLL", mainViewModel.aqiData.value.toString())

        val intent  = Intent(applicationContext,DataShowingActivity::class.java)
        intent.putExtra("station",mainViewModel.stationData as ArrayList)
        intent.putExtra("aqi",  mainViewModel.aqiData.value)
        startActivity(intent)
    }
}