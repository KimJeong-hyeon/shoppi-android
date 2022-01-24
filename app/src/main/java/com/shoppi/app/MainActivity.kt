package com.shoppi.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
// Todo 1/18일 새롭게 배운것들 ( Navigation을 통한 프래그먼트간의 화면이동, Splash화면(인트로 화면) ) -> 두가지를 사용해서 복습 해봐야함
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation_main)
        bottomNavigationView.itemIconTintList = null

        val navController = supportFragmentManager.findFragmentById(R.id.container_main)?.findNavController()
        // navController가 null이 아닐때만 전달 할수 있게끔 let을 사용
        navController?.let{
            bottomNavigationView.setupWithNavController(it)
        }
    }
}