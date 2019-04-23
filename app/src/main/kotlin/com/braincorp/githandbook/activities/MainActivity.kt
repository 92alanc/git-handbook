package com.braincorp.githandbook.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.braincorp.githandbook.R
import com.braincorp.githandbook.util.loadAnnoyingAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ad_view.loadAnnoyingAds()
    }

}