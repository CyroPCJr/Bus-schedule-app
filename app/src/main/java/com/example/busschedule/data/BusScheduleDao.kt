package com.example.busschedule.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BusScheduleDao {

    @Query("SELECT * from Schedule ORDER BY arrival_time ASC")
    fun getAllSchedules(): Flow<List<BusSchedule>>

    @Query("SELECT * from schedule WHERE stop_name = :stopName ORDER BY arrival_time ASC")
    fun getSchedule(stopName: String): Flow<List<BusSchedule>>
}