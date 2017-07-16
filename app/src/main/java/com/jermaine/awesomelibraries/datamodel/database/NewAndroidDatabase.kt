package com.jermaine.awesomelibraries.datamodel.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jermaine.awesomelibraries.datamodel.repo.Repo
import com.jermaine.awesomelibraries.datamodel.repo.RepoDao


@Database(entities = arrayOf(Repo::class), version = 1)
abstract class NewAndroidDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}