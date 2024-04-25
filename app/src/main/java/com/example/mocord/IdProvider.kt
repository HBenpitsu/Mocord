package com.example.mocord

private var currentId = 0

fun takeId(): Int {return currentId++}