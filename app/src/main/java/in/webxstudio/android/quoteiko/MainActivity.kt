package `in`.webxstudio.android.quoteiko

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    val changeQuoteFragment = ChangeQuoteFragment()
    val changeFontStyleFragment = ChangeFontStyleFragment()
    val changeImageFragment = ChangeImageFragment()

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
                showQuoteFragment()
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

    override fun onQuoteDetailsReceived(quoteString: String, quoteAuthorName: String) {

        super.onQuoteDetailsReceived(quoteString, quoteAuthorName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // set the normal options on the main activity
        showChangeOptionsFragment()
    }

    fun showChangeOptionsFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,optionsHolderFragment)
        transactionManager.commit()
    }

    fun showQuoteFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeQuoteFragment)
        transactionManager.commit()
    }

    fun showChangeFontStyleFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeFontStyleFragment)
        transactionManager.commit()
    }

    fun showChangeImageFragment(){
        val transactionManager = manager.beginTransaction()
        transactionManager.replace(R.id.root_fragment_holder,changeImageFragment)
        transactionManager.commit()
    }




}