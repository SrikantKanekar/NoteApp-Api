package com.example.data.database

import com.example.data.model.Note
import com.example.data.model.User
import com.example.data.response.SimpleResponse
import com.example.security.checkHashForPassword
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.not
import org.litote.kmongo.reactivestreams.KMongo

private val client = KMongo.createClient(
    "mongodb+srv://admin:xwtAdn7goC21S6HO@noteappcluster.pt50t.mongodb.net/Database?retryWrites=true&w=majority"
).coroutine
private val database = client.getDatabase("Database")
private val users = database.getCollection<User>()


// Account
suspend fun registerUser(user: User): Boolean {
    return users.insertOne(user).wasAcknowledged()
}

suspend fun checkIfUserExists(email: String): Boolean {
    return users.findOne(User::email eq email) != null
}

suspend fun checkPasswordForEmail(email: String, passwordToCheck: String): Boolean {
    val actualPassword = users.findOne(User::email eq email)?.password ?: return false
    return checkHashForPassword(passwordToCheck, actualPassword)
}


// Notes
suspend fun insertOrUpdateNote(email: String, note: Note): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var message = ""
    val index = user.notes.indexOf(note)
    when (index) {
        -1 -> {
            user.notes.add(note)
            message = "Note Added"
        }
        else -> {
            user.notes[index] = note
            message = "Note Updated"
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, message)
}

suspend fun insertOrUpdateNotes(email: String, notes: List<Note>): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var added = 0
    var update = 0
    notes.forEach { note ->
        val index = user.notes.indexOf(note)
        when (index) {
            -1 -> {
                user.notes.add(note)
                added++
            }
            else -> {
                user.notes[index] = note
                update++
            }
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, "Added $added and updated $update")
}

suspend fun searchNote(email: String, note: Note): Note? {
    val user = users.findOne(User::email eq email)!!
    val index = user.notes.indexOf(note)
    when (index) {
        -1 -> return null
        else -> return user.notes[index]
    }
}

suspend fun getAllNotes(email: String): List<Note> {
    val user = users.findOne(User::email eq email)!!
    return user.notes
}

suspend fun deleteNote(email: String, noteId: String): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var message = ""
    var note: Note? = null
    for (it in user.notes) {
        if (it.id == noteId){
            note = it
            break
        }
    }
    when (note) {
        null -> {
            message = "Note not found"
        }
        else -> {
            user.notes.remove(note)
            message = "Note deleted"
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, message)
}

suspend fun deleteAllNotes(email: String): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    val count = user.notes.count()
    user.notes.clear()
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, "deleted $count notes")
}


// Deleted Notes
suspend fun insertDeletedNote(email: String, note: Note): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var message = ""
    val index = user.deletedNotes.indexOf(note)
    when (index) {
        -1 -> {
            user.deletedNotes.add(note)
            message = "Note added to delete folder"
        }
        else -> {
            user.deletedNotes[index] = note
            message = "note updated in delete folder"
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, message)
}

suspend fun insertDeletedNotes(email: String, notes: List<Note>): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var added = 0
    var update = 0
    notes.forEach { note ->
        val index = user.deletedNotes.indexOf(note)
        when (index) {
            -1 -> {
                user.deletedNotes.add(note)
                added++
            }
            else -> {
                user.deletedNotes[index] = note
                update++
            }
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, "Added $added and updated $update")
}

suspend fun getDeletedNotes(email: String): List<Note> {
    val user = users.findOne(User::email eq email)!!
    return user.deletedNotes
}

suspend fun deleteDeletedNote(email: String, note: Note): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    var message = ""
    val index = user.deletedNotes.indexOf(note)
    when (index) {
        -1 -> {
            message = "Note not found in delete folder"
        }
        else -> {
            user.deletedNotes.remove(note)
            message = "Note removed from delete folder"
        }
    }
    val updated = users.updateOne(User::email eq email, user).wasAcknowledged()
    return SimpleResponse(updated, message)
}
