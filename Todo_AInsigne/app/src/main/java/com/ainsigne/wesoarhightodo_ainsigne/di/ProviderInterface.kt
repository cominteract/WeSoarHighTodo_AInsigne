package com.ainsigne.wesoarhightodo_ainsigne.di

interface Provider<T> {
    fun get(): T
}