package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {

    fun getAllSchedules(): Flow<List<BusSchedule>>

    fun getSchedule(id: Int): Flow<BusSchedule>

    suspend fun insert(busSchedule: BusSchedule)

    suspend fun update(busSchedule: BusSchedule)

    suspend fun delete(busSchedule: BusSchedule)
}