package com.muradnajafli.notesapp.di

import android.app.Application
import androidx.room.Room
import com.muradnajafli.notesapp.data.room.NoteDao
import com.muradnajafli.notesapp.data.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            "fav_movie_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFavMovieDao(db: NoteDatabase): NoteDao {
        return db.noteDao()
    }

}