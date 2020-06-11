package com.example.cleanarch.framework

import com.example.core.usecase.AddNote
import com.example.core.usecase.GetAllNotes
import com.example.core.usecase.GetNote
import com.example.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    var getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)