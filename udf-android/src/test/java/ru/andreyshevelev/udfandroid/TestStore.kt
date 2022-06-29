package ru.andreyshevelev.udfandroid

import ru.andreyshevelev.udf.*

class TestStore(
    currentState: TestState,
    reducer: TestReducer,
    errorHandler: ErrorHandler,
    sideEffects: List<BaseSideEffect<TestState, TestAction>>,
    actionSources: List<BaseActionSource<TestState, TestAction>>,
    actionHandlers: List<BaseActionHandler<TestState, TestAction>>,
    bootstrapAction: TestAction?
) : BaseStore<TestState, TestAction, TestNews>(
    currentState = currentState,
    reducer = reducer,
    errorHandler = errorHandler,
    sideEffects = sideEffects,
    actionSources = actionSources,
    actionHandlers = actionHandlers,
    bootstrapAction = bootstrapAction
)