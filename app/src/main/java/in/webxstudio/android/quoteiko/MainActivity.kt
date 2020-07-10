package `in`.webxstudio.android.quoteiko

import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeFontStyleFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeImageFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeQuoteFragment
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
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
    private val manager = supportFragmentManager
    private val optionsHolderFragment = OptionsHolderFragment()
    private val changeQuoteFragment =
        ChangeQuoteFragment()
    private val changeFontStyleFragment =
        ChangeFontStyleFragment()
    private val changeImageFragment =
        ChangeImageFragment()

    lateinit var defaultQuote :String
    lateinit var defaultAuthor:String
    var defaultSize: Int = 18
    var defaultColor = "#000000"
    var textAlignment = Alignments.LEFT
    var textStyle = TextStyle.NORMAL

    enum class Options{
        CHANGE_QUOTE,
        CHANGE_STYLE,
        CHANGE_IMAGE
    }

    enum class TextStyle{
        BOLD,
        ITALIC,
        NORMAL
    }

    enum class Alignments{
        LEFT,
        RIGHT,
        CENTER
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

    data class ImageProps(
        var imagePath: String,
        var imageQuote:String,
        var quoteAuthor:String,
        var textSize:Int,
        var textColor: Color
    )

    companion object {
    }

}