package com.example.database

import com.example.model.User
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

private const val MONGODB_URI =
    "mongodb+srv://admin:PKgpCMz3ikI8XQAR@noteappcluster.pt50t.mongodb.net/?retryWrites=true&w=majority"
private const val DATABASE_NAME = "DATABASE"
const val COLLECTION_USER = "USERS"

private val mongoDbString = System.getenv("MONGODB_URI") ?: MONGODB_URI
private val client = KMongo.createClient(mongoDbString).coroutine
private val database = client.getDatabase(DATABASE_NAME)
val users = database.getCollection<User>(COLLECTION_USER)