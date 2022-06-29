package ru.andreyshevelev.udfandroid

sealed class TestAction {
    object Init: TestAction()
    object Error: TestAction()
    object ViewControllerSend: TestAction()
    object TestSideEffect1: TestAction()
    object TestSideEffect2: TestAction()
    object TestSideEffect3: TestAction()
    object TestActionSource1: TestAction()
    object TestActionSource2: TestAction()
    object TestActionSource3: TestAction()
    object TestActionHandler1: TestAction()
    object TestActionHandler2: TestAction()
}