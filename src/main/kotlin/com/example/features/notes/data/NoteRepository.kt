package com.example.features.notes.data

import com.example.database.user.UserDataSource
import com.example.model.Note

class NoteRepository(
    private val userDataSource: UserDataSource
) {

    suspend fun insertOrUpdateNotes(email: String, notes: List<Note>) {
        val user = userDataSource.getUser(email)
        notes.forEach { note ->
            when (val index = user.notes.indexOf(note)) {
                -1 -> user.notes.add(note)
                else -> user.notes[index] = note
            }
        }
        userDataSource.updateUser(user)
    }

    suspend fun getNoteById(email: String, noteId: String): Note? {
        val user = userDataSource.getUser(email)
        return user.notes.firstOrNull { it.id == noteId }
    }

    suspend fun getAllNotes(email: String): List<Note> {
        val user = userDataSource.getUser(email)
        return user.notes
    }

    suspend fun deleteNotes(email: String, noteIds: List<String>) {
        val user = userDataSource.getUser(email)
        user.notes.removeIf { noteIds.contains(it.id) }
        userDataSource.updateUser(user)
    }
}