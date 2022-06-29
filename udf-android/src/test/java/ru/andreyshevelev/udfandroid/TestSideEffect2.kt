package ru.andreyshevelev.udfandroid

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.andreyshevelev.udf.BaseSideEffect

class TestSideEffect2 : BaseSideEffect<TestState, TestAction>() {
    override fun checkReadiness(action: TestAction) = action is TestAction.TestActionSource2

    override suspend fun invoke(state: TestState, action: TestAction): Flow<List<TestAction>> {
        return flow {
            while (true) {
                kotlinx.coroutines.delay(1000)
                emit(listOf(TestAction.TestSideEffect2))
            }
        }
    }

    override suspend fun invoke(t: Throwable) = flow { emit(listOf(TestAction.Error)) }
}