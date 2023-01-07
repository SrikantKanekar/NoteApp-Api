package com.example.database

import com.example.model.Note
import com.example.model.User
import kotlinx.serialization.Serializable
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

private const val MONGODB_URI =
    "mongodb+srv://admin:PKgpCMz3ikI8XQAR@noteappcluster.pt50t.mongodb.net/?retryWrites=true&w=majority"
private const val DATABASE_NAME = "DATABASE"
const val COLLECTION_USER = "USERS"

private val mongoDbString = System.getenv("MONGODB_URI") ?: MONGODB_URI
private val client = KMongo.createClient(mongoDbString).coroutine
private val database = client.getDatabase(DATABASE_NAME)
val users = database.getCollection<User>(COLLECTION_USER)

// Notes
suspend fun insertOrUpdateNote(email: String, note: Note): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    val message: String
    when (val index = user.notes.indexOf(note)) {
        -1 -> {
            user.notes.add(note)
            message = "Note Added"
        }

        else -> {
            user.notes[index] = note
            message = "Note Updated at index $index"
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
        when (val index = user.notes.indexOf(note)) {
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

suspend fun getNote(email: String, noteId: String): Note? {
    val user = users.findOne(User::email eq email)!!
    var note: Note? = null
    for (it in user.notes) {
        if (it.id == noteId) {
            note = it
            break
        }
    }
    return note
}

suspend fun getAllNotes(email: String): List<Note> {
    val user = users.findOne(User::email eq email)!!
    return user.notes
}

suspend fun deleteNote(email: String, noteId: String): SimpleResponse {
    val user = users.findOne(User::email eq email)!!
    val message: String
    var note: Note? = null
    for (it in user.notes) {
        if (it.id == noteId) {
            note = it
            break
        }
    }
    message = when (note) {
        null -> {
            "Note not found"
        }

        else -> {
            user.notes.remove(note)
            "Note deleted"
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

@Serializable
data class SimpleResponse(
    val successful: Boolean,
    val message: String
)