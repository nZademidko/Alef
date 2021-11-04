package com.inter.testalef

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.inter.testalef.di.utils.FragmentFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory

        setContentView(R.layout.activity_main)
    }
}