package com.example.cleanarch.framework

import android.content.Context
import com.example.cleanarch.framework.db.DatabaseService
import com.example.cleanarch.framework.db.NoteEntitiy
import com.example.core.data.Note
import com.example.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context) : NoteDataSource {

    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntitiy.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntitites().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntitiy.fromNote(note))
}