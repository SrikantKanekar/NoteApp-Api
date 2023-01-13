package com.example.features.labels.data

import com.example.database.user.UserDataSource
import com.example.model.Label

class LabelRepository(
    private val userDataSource: UserDataSource
) {

    suspend fun insertOrUpdateLabels(email: String, labels: List<Label>) {
        val user = userDataSource.getUser(email)
        labels.forEach { label ->
            when (val index = user.labels.indexOf(label)) {
                -1 -> user.labels.add(label)
                else -> user.labels[index] = label
            }
        }
        userDataSource.updateUser(user)
    }

    suspend fun getLabelById(email: String, labelId: String): Label? {
        val user = userDataSource.getUser(email)
        return user.labels.firstOrNull { it.id == labelId }
    }

    suspend fun getAllLabels(email: String): List<Label> {
        val user = userDataSource.getUser(email)
        return user.labels
    }

    suspend fun deleteLabels(email: String, labelIds: List<String>) {
        val user = userDataSource.getUser(email)
        user.labels.removeIf { labelIds.contains(it.id) }
        userDataSource.updateUser(user)
    }
}