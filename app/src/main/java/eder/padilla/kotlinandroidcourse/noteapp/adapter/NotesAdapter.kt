package eder.padilla.kotlinandroidcourse.noteapp.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


import eder.padilla.kotlinandroidcourse.R
import eder.padilla.kotlinandroidcourse.noteapp.model.Note
import kotlinx.android.synthetic.main.ticket.view.*


/**
 * Created by ederpadilla on 09/01/17.
 */

class NotesAdapter(private val notesList: List<Note>, private var context: Context?, private val onNoteSelected: OnNoteSelected) : RecyclerView.Adapter<NotesAdapter.ViewHolderAdapter>(), View.OnClickListener {

    private val listener: View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderAdapter {

        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.ticket, parent, false)
        val viewholder = ViewHolderAdapter(view)
        return viewholder
    }

    fun setOnClickListener(listener: View.OnClickListener) {}
    override fun onClick(view: View) {
        listener?.onClick(view)
    }

    override fun onBindViewHolder(holder: ViewHolderAdapter, position: Int) {
        val item = notesList[position]
        holder.bindTexts(item)
        holder.rootView.setOnClickListener { view -> onNoteSelected.onNoteSelected(notesList[position]) }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    inner class ViewHolderAdapter(internal var rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bindTexts(note: Note) {
            itemView?.let{
                with(itemView) {
                    tvTitle?.text = note.nodeName
                    tvDes?.text = (note.nodeDes)
                }
            }
        }
    }

}
