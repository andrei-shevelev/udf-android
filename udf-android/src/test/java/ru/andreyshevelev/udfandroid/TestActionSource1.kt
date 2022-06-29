package ru.andreyshevelev.udfandroid

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.andreyshevelev.udf.BaseActionSource
import ru.andreyshevelev.udf.BaseSideEffect

class TestActionSource1 : BaseActionSource<TestState, TestAction>() {

    override suspend fun invoke(): Flow<List<TestAction>> {
        return flow {
            emit(listOf(TestAction.TestActionSource3))
            while (true) {
                kotlinx.coroutines.delay(5000)
                emit(listOf(TestAction.TestActionSource1))
            }
        }
    }

    override suspend fun invoke(t: Throwable) = flow { emit(listOf(TestAction.Error)) }
}