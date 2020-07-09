package `in`.webxstudio.android.quoteiko

import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeFontStyleFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeImageFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeQuoteFragment
import android.content.Context
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.util.Log

class MainActivity : AppCompatActivity(),
        ChangeQuoteFragment.onQuoteDetailsFilled,
        ChangeFontStyleFragment.onFontStyleChanged,
        ChangeImageFragment.onImageChanged,
        OptionsHolderFragment.onOptionSelected
{

    private val TAG = "MainActivity"
    val manager = supportFragmentManager
    val optionsHolderFragment = OptionsHolderFragment()
    val changeQuoteFragment =
        ChangeQuoteFragment()
    val changeFontStyleFragment =
        ChangeFontStyleFragment()
    val changeImageFragment =
        ChangeImageFragment()

    enum class Options{
        CHANGE_QUOTE,
        CHANGE_STYLE,
        CHANGE_IMAGE
    }

    override fun onOptionSelected(option: Options) {
        Log.d(TAG,"One option selected")
        when(option){
            Options.CHANGE_IMAGE->{
                showChangeImageFragment()
            }
            Options.CHANGE_QUOTE->{
                showChangeQuoteFragment()
            }
            Options.CHANGE_STYLE->{
                showChangeFontStyleFragment()
            }
        }
    }

    override fun onFontStyleChanged(
        fontFormat: ChangeQuoteFragment.TextFormat,
        fontDirection: ChangeQuoteFragment.TextDirection?,
        fontColor: String
    ) {

        super.onFontStyleChanged(fontFormat, fontDirection, fontColor)
    }

    override fun onImageChanged(imagePath: String) {
        //sample_image_quote.QuoteViewImage.setImageBitmap()
        super.onImageChanged(imagePath)
    }

    override fun onBackPressed() {
        manager.popBackStack()
    }

    override fun onQuoteDetailsReceived(quoteString: String, quoteAuthorName: String) {

        super.onQuoteDetailsReceived(quoteString, quoteAuthorName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val imageProperties = ImageProperties()
        // set the normal options on the main activity
        showChangeOptionsFragment()
    }

    fun showChangeOptionsFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,optionsHolderFragment)
            .addToBackStack(OPTIONS_FRAGMENT)
        transactionManager.commit()
    }

    fun showChangeQuoteFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeQuoteFragment)
            .addToBackStack(CHANGE_QUOTE_FRAGMENT)
        transactionManager.commit()
    }

    fun showChangeFontStyleFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeFontStyleFragment)
            .addToBackStack(CHANGE_FONT_STYLE_FRAGMENT)
        transactionManager.commit()
    }

    fun showChangeImageFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeImageFragment)
            .addToBackStack(CHANGE_IMAGE_FRAGMENT)
        transactionManager.commit()
    }

    data class ImageProperties(
        var imagePath: Int = R.drawable.adrien_olichon,
        var quoteString: String,
        var authorString: String,
        var alignmentText: String,
        var textSize: Int,
        var textType: String)

}