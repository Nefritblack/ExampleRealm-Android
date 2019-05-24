package pl.droidsonroids.examplerealm.ui.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import io.realm.RealmChangeListener
import io.realm.RealmResults
import pl.droidsonroids.examplerealm.R
import pl.droidsonroids.examplerealm.model.MyBook

class MyListAdapter(private val mBooks: RealmResults<MyBook>) : RecyclerView.Adapter<MyListAdapter.ViewHolder>(), RealmChangeListener {

    val itemCount: Int
        get() = mBooks.size

    init {
        mBooks.addChangeListener(this)
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ViewHolder(view as TextView)
    }

    fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mTextTitle.text = mBooks[position].title
    }

    override fun onChange() {
        ///notifyDataSetChanged
    }

    inner class ViewHolder(internal var mTextTitle: TextView) : RecyclerView.ViewHolder(mTextTitle)
}
