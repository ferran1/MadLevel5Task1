package com.example.madlevel5task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task1.R
import com.example.madlevel5task1.viewmodel.NoteViewModel
import kotlinx.android.synthetic.main.fragment_add_note.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddNoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_save.setOnClickListener {
            saveNote()
        }

        observeNote()
    }

    private fun observeNote() {
        //fill the text fields with the current text and title from the viewmodel
        viewModel.note.observe(viewLifecycleOwner, { note ->
            note?.let {
                til_note_title.editText?.setText(it.title)
                til_note_text.editText?.setText(it.text)
            }

        })

        viewModel.error.observe(viewLifecycleOwner, { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        })

        viewModel.success.observe(viewLifecycleOwner, { success ->
            //"pop" the backstack, this means we destroy this fragment and go back to the RemindersFragment
            findNavController().popBackStack()
        })
    }

    private fun saveNote() {
        viewModel.updateNote(
            til_note_title.editText?.text.toString(),
            til_note_text.editText?.text.toString()
        )
    }

}