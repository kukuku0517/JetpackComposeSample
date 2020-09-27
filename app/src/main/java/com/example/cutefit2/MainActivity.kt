package com.example.cutefit2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.cutefit2.ui.target.TargetActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView()
        }
        startActivity(Intent(this, TargetActivity::class.java))

    }

    @Composable
    private fun setContentView() {
           MaterialTheme {
               Column(
                   modifier = Modifier.padding(4.dp)
               ) {
                   Text("Hello world")
                   Text("Hello world")
               }
           }
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        setContentView()
    }

}