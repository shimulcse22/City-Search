package com.example.city_search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.city_search.databinding.ActivityMainBinding
import com.example.city_search.repository.Response
import com.example.city_search.viewmodel.AppViewModelFactory
import com.example.city_search.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(){
    lateinit var mainViewModel : MainViewModel

    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var appViewModelFactory: AppViewModelFactory



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        (application as ProjectApplication).applicationComponent.inject(this)
        mainViewModel = ViewModelProvider(this, appViewModelFactory)[MainViewModel::class.java]

        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this

        binding.loading.visibility = View.GONE

        mainViewModel.cityLiveData.observe(this, Observer { it ->
            when(it){
               is Response.Loading -> {
                   binding.loading.visibility = View.VISIBLE
               }
               is Response.Success -> {
                   binding.loading.visibility = View.GONE
                   moveToOtherActivity()
               }
               is Response.Error -> {
                   binding.loading.visibility = View.GONE
                   Toast.makeText(this,it.error,Toast.LENGTH_LONG).show()
               }
            }
        })

        mainViewModel.aqiData.observe(this,{
            if(it.city.isNotEmpty()){
                moveToOtherActivity()
            }
        })


        setAdapter()

        binding.autoCompleteText.setOnItemClickListener { adapterView, _, i, _ ->
            mainViewModel.searchData.value = adapterView.getItemAtPosition(i).toString()

            lifecycleScope.launch {

                moveToOtherActivity()
            }
        }

        binding.image.setOnClickListener {
            mainViewModel.cityLiveData.value = Response.Loading(null)
            lifecycleScope.launch {
                mainViewModel.getCityFromAPi()
            }
        }
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
        intent.putExtra("cityName",mainViewModel.searchData.value)
        startActivity(intent)
    }
}