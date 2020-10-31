package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import com.example.madlevel5task1.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_notepad.*
import java.util.Observer

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NotepadFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notepad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeAddNoteResult()

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_notepadFragment_to_addNoteFragment)
        }
    }

    // Responsible for initializing the viewmodels and setting up the observer
    private fun observeAddNoteResult() {
        // The "Note" data object inside the viewModel is observed and whenever it changes the title last updated and text will be updated
        viewModel.note.observe(viewLifecycleOwner, { note ->
            note?.let {
                card_note_title.text = it.title
                tv_last_updated.text = getString(R.string.last_updated_date, it.lastUpdated.toString())
                card_note_text.text = it.text
            }
        })
    }

}