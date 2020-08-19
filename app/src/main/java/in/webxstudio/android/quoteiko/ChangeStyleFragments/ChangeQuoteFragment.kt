package `in`.webxstudio.android.quoteiko.ChangeStyleFragments

import `in`.webxstudio.android.quoteiko.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class ChangeQuoteFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    interface onQuoteDetailsFilled{
        fun onQuoteDetailsReceived(quoteString:String,quoteAuthorName:String){}
    }

    enum class TextDirection{
        Left,
        Right,
        Centered
    }

    enum class TextFormat{
        Bold,
        Italic,
        Normal
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_quote_details, container, false)
    }

}