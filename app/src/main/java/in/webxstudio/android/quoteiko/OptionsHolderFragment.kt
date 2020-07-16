package `in`.webxstudio.android.quoteiko

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_change_quote_view.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OptionsHolderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionsHolderFragment : Fragment() {

    var optionsListener: onOptionSelected? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        optionsListener = context as? onOptionSelected
        if (optionsListener == null){
            throw ClassCastException("$context must implement OnArticleSelectedListener")
        }
        super.onAttach(context)
    }

    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_change_quote_view, container, false)

        rootView.change_quote_card.setOnClickListener{
            optionsListener?.onOptionSelected(MainActivity.Options.CHANGE_QUOTE)
        }

        rootView.change_fontstyle_card.setOnClickListener {
            optionsListener?.onOptionSelected(MainActivity.Options.CHANGE_STYLE)
        }

        rootView.change_image_card.setOnClickListener {
            optionsListener?.onOptionSelected(MainActivity.Options.CHANGE_IMAGE)
        }
        return rootView
    }


    interface onOptionSelected{
        fun onOptionSelected(option:MainActivity.Options){}
    }
}