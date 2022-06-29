package ru.andreyshevelev.udfandroid

import ru.andreyshevelev.udf.BaseReducer

class TestReducer : BaseReducer<TestState, TestAction, TestNews>() {
    override fun reduce(state: TestState, action: TestAction): TestState {
        return state.copy(testAction = action, inc = state.inc + 1)
    }
}