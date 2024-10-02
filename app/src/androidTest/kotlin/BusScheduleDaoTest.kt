import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.busschedule.data.BusSchedule
import com.example.busschedule.data.BusScheduleDao
import com.example.busschedule.data.BusScheduleDatabase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class BusScheduleDaoTest {

    private lateinit var busScheduleDao: BusScheduleDao
    private lateinit var busScheduleDatabase: BusScheduleDatabase
    private val bus1 = BusSchedule(1, "Stop1", 100)
    private val bus2 = BusSchedule(2, "Stop2", 500)

    @Before
    fun createDb() {
        val context: Context = ApplicationProvider.getApplicationContext()
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        busScheduleDatabase = Room.inMemoryDatabaseBuilder(context, BusScheduleDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        busScheduleDao = busScheduleDatabase.BusScheduleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        busScheduleDatabase.close()
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertsBusScheduleIntoDB() = runBlocking {
        addOneItemToDb()
        val allItems = busScheduleDao.getAllSchedules().first()
        assertEquals(allItems[0], bus1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllSchedules_returnsAllBusSchedulesFromDB() = runBlocking {
        addTwoItemsToDb()
        val allItems = busScheduleDao.getAllSchedules().first()
        assertEquals(allItems[0], bus1)
        assertEquals(allItems[1], bus2)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetSchedule_returnsItemFromDB() = runBlocking {
        addOneItemToDb()
        val item = busScheduleDao.getSchedule(1)
        assertEquals(item.first(), bus1)
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteSchedule_deletesAllItemsFromDB() = runBlocking {
        addTwoItemsToDb()
        with(busScheduleDao) {
            delete(bus1)
            delete(bus2)
        }
        val allItems = busScheduleDao.getAllSchedules().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updatesItemsInDB() = runBlocking {
        addTwoItemsToDb()
        val newBus1 = BusSchedule(1, "Stop1", 15)
        val newBus2 = BusSchedule(2, "Stop2", 20)

        with(busScheduleDao) {
            update(newBus1)
            update(newBus2)
        }

        val allItems = busScheduleDao.getAllSchedules().first()
        assertEquals(allItems[0], newBus1)
        assertEquals(allItems[1], newBus2)
    }

    private suspend fun addOneItemToDb() {
        busScheduleDao.insert(bus1)
    }

    private suspend fun addTwoItemsToDb() {
        with(busScheduleDao) {
            insert(bus1)
            insert(bus2)
        }
    }
}
