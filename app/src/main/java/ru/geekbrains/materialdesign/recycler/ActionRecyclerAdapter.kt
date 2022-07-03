package ru.geekbrains.materialdesign.recycler

fun interface ActionRecyclerAdapter {
    fun action(position: Int, data: Data)
}