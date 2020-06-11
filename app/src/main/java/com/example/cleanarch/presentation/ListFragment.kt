package com.example.cleanarch.presentation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.cleanarch.R
import com.example.cleanarch.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val notesListAdapter = NotesListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = notesListAdapter
        }

        addNote.setOnClickListener {
            goToNoteDetails()
        }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        observerViewModel()
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(notesListView).navigate(action)
    }

    private fun observerViewModel() {
        viewModel.notes.observe(this, Observer { notesList ->
            loadingView.visibility = View.GONE
            notesListView.visibility = View.VISIBLE
            notesListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime
            })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }


}
