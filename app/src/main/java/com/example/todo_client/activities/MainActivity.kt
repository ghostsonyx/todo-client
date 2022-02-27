package com.example.todo_client.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo_client.adapters.NotesAdapter
import com.example.todo_client.databinding.ActivityMainBinding
import com.example.todo_client.models.Note
import com.example.todo_client.services.NoteService
import com.example.todo_client.services.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadNotes()
    }

    private fun loadNotes(): List<Note> {
        val service = ServiceBuilder.buildService(NoteService::class.java)
        val call = service.getNotes()

        var notes = listOf<Note>()

        call.enqueue(object : Callback<List<Note>> {
            override fun onResponse(call: Call<List<Note>>, response: Response<List<Note>>) {
                if (response.code() == 200) {
                    notes = response.body()!!

                    binding.recyclerView.adapter = NotesAdapter(this@MainActivity, notes)
                    binding.recyclerView.setHasFixedSize(true)
                }
            }

            override fun onFailure(call: Call<List<Note>>, t: Throwable) {
                Timber.tag("@@").e(t)
            }
        })
        return notes
    }
}