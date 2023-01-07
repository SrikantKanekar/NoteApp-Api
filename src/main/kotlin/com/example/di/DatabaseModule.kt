package com.example.di

import com.example.database.COLLECTION_USER
import com.example.database.user.UserDataSource
import com.example.database.user.UserDataSourceImpl
import com.example.database.users
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {
    single(named(COLLECTION_USER)) { users }
    single<UserDataSource> { UserDataSourceImpl(get(named(COLLECTION_USER))) }
}