package com.example.city_search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.city_search.databinding.ActivityDataShowingBinding
import com.example.city_search.viewmodel.AppViewModelFactory
import com.example.city_search.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class CityActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityDataShowingBinding

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_showing)

        (application as ProjectApplication).applicationComponent.injectCityActivity(this)

        mainViewModel = ViewModelProvider(this, appViewModelFactory)[MainViewModel::class.java]

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        val cityName = intent.getStringExtra("cityName")

        mainViewModel.searchData.value = cityName

        if (!cityName.isNullOrBlank()) {
            lifecycleScope.launch {
                mainViewModel.getStationAndAqiInfo()
                mainViewModel.getAqiInfo()
            }
        }

        mainViewModel.stationData.observe(this) {
            mainViewModel.placeName.postValue(it.joinToString { x -> x.placeName })
            binding.other.text = it.joinToString { x ->
                "City     :   " + x.city + "\n\n" + "AQI     :   " + x.aQI + "\n\n" +
                        "NO2     :   " + x.nO2 + "\n\n" +
                        "OZONE   :   " + x.oZONE + "\n\n" +
                        "Dvision :   " + x.division + "\n\n"
            }
        }
    }
}