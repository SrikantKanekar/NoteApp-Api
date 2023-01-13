package com.example.di

import com.example.features.auth.data.AuthRepository
import com.example.features.labels.data.LabelRepository
import com.example.features.notes.data.NoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
    single { NoteRepository(get()) }
    single { LabelRepository(get()) }
}