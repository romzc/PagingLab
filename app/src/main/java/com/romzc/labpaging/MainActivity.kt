package com.romzc.labpaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.danp4.database.AdapterCountryPaging
import com.romzc.labpaging.database.CountryEntity
import com.romzc.labpaging.database.CountryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        val adapterCountry = AdapterCountryPaging()
        val rv = findViewById<RecyclerView>(R.id.rv_activityMain_browse)
        rv.adapter = adapterCountry
        //val countryEntity = CountryEntity(0,"","","","","","","","","","","")
        //viewModel.insertCountry(countryEntity)
        GlobalScope.launch(Dispatchers.IO){
            viewModel.pagingSource().collect(){
                adapterCountry.submitData(it)
            }
        }
    }
}