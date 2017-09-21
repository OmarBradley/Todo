package com.todait.android.application.nextodait.models.realm.config

import io.realm.Realm
import io.realm.RealmConfiguration

const val BASE_FILE_NAME: String = "todait.realm"

val realmConfiguration: RealmConfiguration get() = RealmConfiguration.Builder()
        .name(BASE_FILE_NAME)
        .schemaVersion(TodaitMigration.SCHEMA_VERSION)
        .migration(TodaitMigration)
        .modules(TodaitMigration)
        .build()

val realmInstance: Realm get() = Realm.getInstance(realmConfiguration)
