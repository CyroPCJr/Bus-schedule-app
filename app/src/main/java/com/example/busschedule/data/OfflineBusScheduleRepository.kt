package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(private val busScheduleDao: BusScheduleDao) : BusScheduleRepository {

    override fun getAllSchedules(): Flow<List<BusSchedule>> = busScheduleDao.getAllSchedules()

    override fun getSchedule(id: Int): Flow<BusSchedule> = busScheduleDao.getSchedule(id)

    override suspend fun insert(busSchedule: BusSchedule) = busScheduleDao.insert(busSchedule)

    override suspend fun update(busSchedule: BusSchedule) = busScheduleDao.update(busSchedule)

    override suspend fun delete(busSchedule: BusSchedule) = busScheduleDao.delete(busSchedule)
}