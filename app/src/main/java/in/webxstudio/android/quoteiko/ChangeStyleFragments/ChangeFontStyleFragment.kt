package `in`.webxstudio.android.quoteiko.ChangeStyleFragments

import `in`.webxstudio.android.quoteiko.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ChangeFontStyleFragment : Fragment() {
    interface onFontStyleChanged{
        fun onFontStyleChanged(fontFormat: ChangeQuoteFragment.TextFormat, fontDirection: ChangeQuoteFragment.TextDirection?=null, fontColor:String){}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_font_style, container, false)
    }
}