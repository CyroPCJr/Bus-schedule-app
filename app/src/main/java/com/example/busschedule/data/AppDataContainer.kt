package com.example.busschedule.data

import android.content.Context

interface AppContainer {
    val busScheduleRepository: BusScheduleRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    /**
     *  Implementation of [BusScheduleRepository]
     */
    override val busScheduleRepository: BusScheduleRepository by lazy {
        OfflineBusScheduleRepository(BusScheduleDatabase.getDatabase(context).BusScheduleDao())
    }

}