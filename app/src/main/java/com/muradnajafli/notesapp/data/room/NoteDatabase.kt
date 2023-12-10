package com.muradnajafli.notesapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.muradnajafli.notesapp.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

}