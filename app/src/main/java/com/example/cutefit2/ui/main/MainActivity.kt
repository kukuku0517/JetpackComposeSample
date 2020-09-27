package com.example.cutefit2.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.cutefit2.ui.target.TargetActivity
import com.koduok.compose.glideimage.GlideImage
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView()
        }
//        startActivity(Intent(this, TargetActivity::class.java))
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.loadRecords()
    }

    @Composable
    private fun setContentView() {
        val state = mainViewModel.state.observeAsState()

        MaterialTheme {
            Column(
                modifier = Modifier.padding(4.dp)
            ) {
                LazyRowFor(items = state.value!!.records) {
                    Stack {
                        Box(modifier = Modifier.fillMaxWidth(0.5f).height(400.dp)) {
                            GlideImage(it.url)
                        }
                        Text(text = it.message)
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewGreeting() {
        setContentView()
    }

}