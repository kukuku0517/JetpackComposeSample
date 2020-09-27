package com.example.cutefit2.ui.target

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.example.cutefit2.R
import com.example.cutefit2.util.color
import java.util.*

class TargetActivity : AppCompatActivity() {
    val viewModel: TargetViewModel = TargetViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView()
        }
    }

    @Preview
    @Composable
    private fun setContentView() {
        val state = viewModel.state.observeAsState()
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    TextButton(
                        backgroundColor = this@TargetActivity.color(R.color.purple_200),
                        shape = RoundedCornerShape(4.dp),
                        onClick = {
                            viewModel.onSelectTargetTo(Date())
                        }) {
                        Text(text = "D-0", color = this@TargetActivity.color(R.color.white))
                    }
                    Image(
                        colorFilter = ColorFilter.tint(
                            this@TargetActivity.color(R.color.black)
                        ),
                        modifier = Modifier.clickable(
                            onClick = {
                            }
                        ),
                        asset = vectorResource(
                            id = R.drawable.ic_baseline_calendar_today_24
                        )
                    )

                }
                Text(text = "or")
                TextButton(backgroundColor = this@TargetActivity.color(R.color.purple_200),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        viewModel.onSelectTargetFrom(Date())
                    }) {
                    Text(text = "D+", color = this@TargetActivity.color(R.color.white))
                }
            }
        }
    }
}