package ru.andreyshevelev.udfandroid

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.andreyshevelev.udf.BaseSideEffect

class TestSideEffect3 : BaseSideEffect<TestState, TestAction>() {
    override fun checkReadiness(action: TestAction) = action is TestAction.TestActionSource3

    override suspend fun invoke(state: TestState, action: TestAction): Flow<List<TestAction>> {
        return flow {
            emit(listOf(TestAction.TestSideEffect3))
            while (true) {
                kotlinx.coroutines.delay(1000)
                emit(listOf(TestAction.TestSideEffect3))
            }
        }
    }

    override suspend fun invoke(t: Throwable) = flow { emit(listOf(TestAction.Error)) }
}