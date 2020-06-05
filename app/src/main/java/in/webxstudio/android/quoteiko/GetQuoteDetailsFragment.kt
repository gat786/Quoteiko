package `in`.webxstudio.android.quoteiko

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val QUOTE_IMAGE_PATH = "quoteImagePath"
private const val QUOTE_STRING = "quoteString"
private const val QUOTE_AUTHOR_NAME = "quoteAuthorName"

/**
 * A simple [Fragment] subclass.
 * Use the [GetQuoteDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


private const val defaultQuoteString = "This is a default quote string and it is applied if " +
        "you dont have any text supplied to the constructor while calling it"

private const val defaultImagePath = ""

private const val defaultQuoteAuthor = "Mr, John Doe"


class GetQuoteDetailsFragment : Fragment() {
    lateinit var quoteImagePath: String
    lateinit var quoteString: String
    lateinit var quoteAuthorName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            quoteString = it.getString(QUOTE_STRING).toString()
            quoteAuthorName = it.getString(QUOTE_AUTHOR_NAME).toString()
            quoteImagePath = it.getString(QUOTE_IMAGE_PATH).toString()
        }
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

    var textSizeForQuote: Int = 8

    var viewDimension:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_quote_details, container, false)
    }

}