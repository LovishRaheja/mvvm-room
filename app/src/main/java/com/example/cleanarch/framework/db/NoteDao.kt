package com.example.cleanarch.framework.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert(onConflict = REPLACE)
    suspend fun addNoteEntity(noteEntitiy: NoteEntitiy)

    @Query("SELECT * FROM note WHERE id=:id")
    suspend fun getNoteEntity(id: Long): NoteEntitiy?

    @Query("SELECT * FROM note")
    suspend fun getAllNoteEntitites(): List<NoteEntitiy>

    @Delete
    suspend fun deleteNoteEntity(noteEntitiy: NoteEntitiy)
}