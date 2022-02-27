package com.example.todo_client.services

import com.example.todo_client.models.Note
import retrofit2.Call
import retrofit2.http.GET

interface NoteService {

    @GET("/v1/notes")
    fun getNotes() : Call<List<Note>>
}