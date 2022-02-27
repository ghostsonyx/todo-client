package com.example.todo_client.models

data class Note(
    val creationDate: String? = null,
    val id: String? = null,
    val list: List<Any>? = null,
    val revisionDate: String? = null,
    val title: String? = null
)