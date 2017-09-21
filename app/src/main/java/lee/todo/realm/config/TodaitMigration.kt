package com.todait.android.application.nextodait.models.realm.config

import io.realm.DynamicRealm
import io.realm.RealmMigration
import io.realm.annotations.RealmModule
import lee.todo.realm.model.TaskModel

@RealmModule(classes = arrayOf(TaskModel::class))
object TodaitMigration : RealmMigration {

    const val SCHEMA_VERSION: Long = 1

    override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) = when (oldVersion) {
        1L -> Unit
        else -> Unit
    }
}
