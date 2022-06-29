package ru.andreyshevelev.udfandroid

import ru.andreyshevelev.udf.BaseActionHandler

class TestActionHandler1 : BaseActionHandler<TestState, TestAction>() {
    override fun checkReadiness(action: TestAction) = action is TestAction.TestSideEffect1

    override suspend fun invoke(state: TestState, action: TestAction) {
    }
}