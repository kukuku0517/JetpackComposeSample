package com.example.cutefit2.ui.target

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.example.cutefit2.data.target.TargetRepositoryImpl
import com.example.cutefit2.entity.Target
import com.example.cutefit2.util.Time
import com.example.cutefit2.util.color
import java.util.*

class TargetActivity : AppCompatActivity() {
    val viewModel: TargetViewModel = TargetViewModel(TargetRepositoryImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            setContentView()
        }
        initEvent()
    }

    private fun initEvent() {
        viewModel.completeEvent.observe(this, {
            if (it) {
                finish()
            }
        })
    }

    @Composable
    private fun setContentView() {
        val state = viewModel.state.observeAsState()
        MaterialTheme {
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    TargetToButton(state.value!!)
                    TargetToCalendar()
                }
                Text(text = "또는")
                TargetFromButton(state.value!!)
                TextButton(onClick = { viewModel.confirm() }) {
                    Text(text = "완료")
                }
            }
        }
    }

    @Composable
    private fun TargetToCalendar() {
        Image(
            colorFilter = ColorFilter.tint(
                this@TargetActivity.color(R.color.black)
            ),
            modifier = Modifier.clickable(
                onClick = {
                    showTargetToCalendar()
                }
            ),
            asset = vectorResource(
                id = R.drawable.ic_baseline_calendar_today_24
            )
        )
    }

    private fun showTargetToCalendar() {
        val now = Time()
        DatePickerDialog(
            this@TargetActivity,
            { view, year, month, dayOfMonth ->
                val targetDate = now.clone().let {
                    it.set(Calendar.YEAR, year)
                    it.set(Calendar.MONTH, month)
                    it.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                }
                if (now > targetDate) {
                    Toast.makeText(
                        this@TargetActivity,
                        "이미 지난 날짜 입니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.onSelectTargetTo(targetDate)
                }
            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    @Composable
    private fun TargetToButton(state: TargetViewModel.TargetViewState) {
        when (state.target) {
            is Target.TargetFrom -> {
                TextButton(
                    backgroundColor = this@TargetActivity.color(R.color.white),
                    border = BorderStroke(1.dp, this@TargetActivity.color(R.color.purple_200)),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        showTargetToCalendar()
                    }) {
                    Text(text = "목표 날짜 설정", color = this@TargetActivity.color(R.color.purple_200))
                }
            }
            is Target.TargetTo -> {
                val dayDiff = state.target.endDate - Time()
                TextButton(
                    backgroundColor = this@TargetActivity.color(R.color.purple_200),
                    shape = RoundedCornerShape(4.dp),
                    onClick = {
                        showTargetToCalendar()
                    }) {
                    Text(text = "D-${dayDiff}", color = this@TargetActivity.color(R.color.white))
                }
            }
        }
    }

    @Composable
    private fun TargetFromButton(state: TargetViewModel.TargetViewState) {
        when (state.target) {
            is Target.TargetFrom -> {
                TextButton(
                    backgroundColor = this@TargetActivity.color(R.color.purple_200),
                    shape = RoundedCornerShape(4.dp),
                    onClick = { viewModel.onSelectTargetFrom(Time()) }
                ) {
                    Text(text = "D+0", color = this@TargetActivity.color(R.color.white))
                }
            }
            is Target.TargetTo -> {
                TextButton(backgroundColor = this@TargetActivity.color(R.color.white),
                    shape = RoundedCornerShape(4.dp),
                    border = BorderStroke(1.dp, this@TargetActivity.color(R.color.purple_200)),
                    onClick = { viewModel.onSelectTargetFrom(Time()) }
                ) {
                    Text(text = "오늘 부터 시작하기", color = this@TargetActivity.color(R.color.purple_200))
                }
            }
        }
    }
}