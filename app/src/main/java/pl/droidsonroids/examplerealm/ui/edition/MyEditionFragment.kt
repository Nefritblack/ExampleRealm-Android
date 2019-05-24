package pl.droidsonroids.examplerealm.ui.edition

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.Bind
import butterknife.ButterKnife
import butterknife.OnClick
import io.realm.Realm
import io.realm.RealmResults
import pl.droidsonroids.examplerealm.R
import pl.droidsonroids.examplerealm.model.MyBook
import java.security.AccessController.getContext

class MyEditionFragment : Fragment() {

   // @Bind(R.id.edit_title)
    internal var mEditTitle: EditText? = null

    private var mRealm: Realm? = null

    private val trimmedTitle: String
        get() = mEditTitle!!.text.toString().trim { it <= ' ' }

    fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        mRealm = Realm.getInstance(getContext())
    }

    @Nullable
    fun onCreateView(inflater: LayoutInflater, container: ViewGroup, savedInstanceState: Bundle): View {
        val view = inflater.inflate(R.layout.fragment_edition, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    fun onDestroy() {
      //  super.onDestroy()
        mRealm!!.close()
    }

    @OnClick(R.id.button_add)
    fun onAddClick() {
        mRealm!!.beginTransaction()
        val book = mRealm!!.createObject(MyBook::class.java)
        book.title = trimmedTitle
        mRealm!!.commitTransaction()
    }

    @OnClick(R.id.button_remove)
    fun onRemoveClick() {
        mRealm!!.beginTransaction()
        val books = mRealm!!.where(MyBook::class.java).equalTo("title", trimmedTitle).findAll()
        if (!books.isEmpty()) {
            for (i in books.indices.reversed()) {
                books[i].removeFromRealm()
            }
        }
        mRealm!!.commitTransaction()
    }
}
