package com.example.cutefit2.data.target

import com.example.cutefit2.entity.Record
import com.example.cutefit2.entity.Target
import com.example.cutefit2.util.Time

interface TargetRepository {
    suspend fun getTarget(): Target
}

class TargetRepositoryImpl : TargetRepository {
    override suspend fun getTarget(): Target {
        var records = mutableListOf<Record>()
        (0 until 10).forEach {
            records.addAll(listOf(
                Record(
                    url = "https://i.pinimg.com/originals/10/3b/b6/103bb6490a44b0ccfd3e86e0c80e1de4.jpg",
                    message = "아침운동",
                    timestamp = Time()
                ),
                Record(
                    url = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcR2hYXoJD4vnYry5RyaxRsXnU5IkOdJIkcvJg&usqp=CAU",
                    message = "점심운동",
                    timestamp = Time() + 1
                ),
                Record(
                    url = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQaUbB3MAvF_OorWcXoD1ASCOpxDWslwf0Hmw&usqp=CAU",
                    message = "저녁운동",
                    timestamp = Time() + 2
                )
            ))
        }
        return Target.TargetTo(
            endDate = Time() + 30,
            records = records
        )
    }
}