package `in`.webxstudio.android.quoteiko

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import kotlinx.android.synthetic.main.sample_quote_image.view.*

class QuoteImageSample : Fragment() {
    lateinit var rootView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.sample_quote_image,container,false)
        return rootView
    }

    fun changeImageSource(){
        //TODO implement it
    }

    fun changeQuoteTextSize(){
        //TODO implement it
    }

    fun changeQuoteTextFormat(){
        //TODO implement it
    }

    fun getColorForText(): Palette {
        val imageBitmap = rootView.QuoteViewImage.drawable.toBitmap()
        val paletteBuilder = Palette.from(imageBitmap)
        val palette = paletteBuilder.generate()
        return palette
    }
}