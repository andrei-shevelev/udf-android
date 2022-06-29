package ru.andreyshevelev.udfandroid

import kotlinx.coroutines.*
import org.junit.Test
import ru.andreyshevelev.udf.ErrorHandler

class UdfTest {

    private val errorHandler =
        object : ErrorHandler {
            override fun handle(t: Throwable) {
            }
        }

    private fun store() = TestStore(
        TestState(),
        TestReducer(),
        errorHandler,
        bootstrapAction = TestAction.Init,
        actionSources = listOf(
            TestActionSource1(),
            TestActionSource2()
        ),
        actionHandlers = listOf(
            TestActionHandler1(),
            TestActionHandler2()
        ),
        sideEffects = listOf(
            TestSideEffect1(),
            TestSideEffect2(),
//            TestSideEffect3()
        )
    )

    @Test
    fun test() = runBlocking {
        val states = mutableListOf<String>()
        val store = store()
        val job = CoroutineScope(Dispatchers.IO).launch {
            store.subscribe().collect { storeResult ->
                states.add(storeResult.state.testAction::class.java.name)
                println(storeResult.state.testAction::class.java.name)
            }
        }
        delay(1000)
        delay(1000)
        delay(55000)
        job.cancel()
        store.dispose()
    }
}