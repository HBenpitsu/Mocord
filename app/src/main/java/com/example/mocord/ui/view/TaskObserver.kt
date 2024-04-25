package com.example.mocord.ui.view

class TaskObserver {
    private val taskList = mutableListOf<Int>()
    var onFirstTaskBeganHandler: () -> Unit = {}
    var onSomeTaskBeganHandler: () -> Unit = {}
    var onSomeTaskDoneHandler: () -> Unit = {}
    var onLastTaskDoneHandler: () -> Unit = {}

    fun begin(task: Any, variant: Int = 0) {
        if (!taskList.contains(task.hashCode()+variant)) {
            taskList.add(task.hashCode()+variant)
            onSomeTaskBeganHandler()
            if (taskList.size == 1) {
                onFirstTaskBeganHandler()
            }
        }
    }

    fun done(task: Any, variant: Int = 0) {
        if(taskList.remove(task.hashCode()+variant)){
            onSomeTaskDoneHandler()
            if (taskList.isEmpty()) {
                onLastTaskDoneHandler()
            }
        }
    }

    fun isSomethingInProgress(): Boolean {
        return taskList.isNotEmpty()
    }

    fun isEverythingDone(): Boolean {
        return taskList.isEmpty()
    }
}