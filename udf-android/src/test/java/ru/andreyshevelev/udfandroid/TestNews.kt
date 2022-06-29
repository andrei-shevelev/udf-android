package ru.andreyshevelev.udfandroid

sealed class TestNews {
    object TestSideEffect1: TestNews()
    object TestSideEffect2: TestNews()
    object TestActionSource1: TestNews()
    object TestActionSource2: TestNews()
    object TestActionHandler1: TestNews()
    object TestActionHandler2: TestNews()
}