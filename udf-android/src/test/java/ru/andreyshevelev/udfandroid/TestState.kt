package ru.andreyshevelev.udfandroid

data class TestState(
    val testAction: TestAction = TestAction.Init,
    val inc: Int = 0
)
