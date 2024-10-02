package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDao) :
    BusScheduleRepository {

    override fun getAllSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override fun getSchedule(stopName: String): Flow<List<BusSchedule>> =
        busScheduleDao.getSchedule(stopName)
}