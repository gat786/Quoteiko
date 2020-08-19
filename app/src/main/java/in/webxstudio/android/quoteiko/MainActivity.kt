package `in`.webxstudio.android.quoteiko

import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeFontStyleFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeImageFragment
import `in`.webxstudio.android.quoteiko.ChangeStyleFragments.ChangeQuoteFragment
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),
        ChangeQuoteFragment.onQuoteDetailsFilled,
        ChangeFontStyleFragment.onFontStyleChanged,
        ChangeImageFragment.OnImageChangedListener,
        OptionsHolderFragment.OptionSelectedListener
{

    private val TAG = "MainActivity"
    private val manager = supportFragmentManager
    private var currentImageResourceID : Int = R.drawable.jeremy_weber

    private lateinit var defaultImageBitmap : Bitmap
    private lateinit var defaultQuoteString :String
    private lateinit var defaultAuthorName :String

    lateinit var props :ImageProps

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
        when (option) {
            Options.CHANGE_IMAGE -> {
                val bundle = Bundle()
                bundle.putInt(CURRENTLY_PREVIEWING,currentImageResourceID)
                showChangeImageFragment(bundle)
            }
            Options.CHANGE_QUOTE -> {
                showChangeQuoteFragment()

            }
            Options.CHANGE_STYLE -> {
                showChangeFontStyleFragment()
            }
        }
    }

    fun setImageProperties(props: ImageProps){
        QuoteViewImage.setImageBitmap(props.imageBitmap)
        quote_field.text = props.imageQuote
        quote_author_field.text = props.quoteAuthor
        quote_field.textSize = props.textSize.toFloat()
        quote_field.setTextColor(props.textColor)
        when(props.textAlignment){
            Alignments.LEFT ->{
                quote_field.gravity = Gravity.START
            }
            Alignments.CENTER ->{
                quote_field.gravity = Gravity.CENTER
            }
            Alignments.RIGHT -> {
                quote_field.gravity = Gravity.END
            }
        }
        when(props.textStyle){
            TextStyle.NORMAL->{
                quote_field.setTypeface(quote_field.typeface,Typeface.NORMAL)
            }
            TextStyle.ITALIC->{
                quote_field.setTypeface(quote_field.typeface,Typeface.ITALIC)
            }
            TextStyle.BOLD ->{
                quote_field.setTypeface(quote_field.typeface,Typeface.BOLD)
            }
        }
    }

    override fun onFontStyleChanged(
        fontFormat: ChangeQuoteFragment.TextFormat,
        fontDirection: ChangeQuoteFragment.TextDirection?,
        fontColor: String
    ) {
        println("Method overridden successfully")
        super.onFontStyleChanged(fontFormat, fontDirection, fontColor)
    }

    override fun onImageChanged(resourceInt: Int) {
        //sample_image_quote.QuoteViewImage.setImageBitmap()
        Log.d(TAG,"Changing image")

        val bitmap = BitmapFactory.decodeResource(resources,resourceInt)
        props.imageBitmap = bitmap
        setImageProperties(props)
        currentImageResourceID = resourceInt
        QuoteViewImage.setImageResource(resourceInt)
        manager.popBackStack()

        super.onImageChanged(resourceInt)
    }

    override fun onBackPressed() {
        if(manager.backStackEntryCount > 1) {
            manager.popBackStack()
        }
    }

    override fun onQuoteDetailsReceived(quoteString: String,
                                        quoteAuthorName: String){
        super.onQuoteDetailsReceived(quoteString, quoteAuthorName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defaultImageBitmap = BitmapFactory.decodeResource(resources,currentImageResourceID)
        defaultQuoteString =  resources.getString(R.string.quotes_string)
        defaultAuthorName = resources.getString(R.string.default_quotes_author)

        props = ImageProps(
            imageBitmap = defaultImageBitmap,
            imageQuote = defaultQuoteString,
            quoteAuthor = defaultAuthorName,
            textStyle = TextStyle.ITALIC
        )


        setImageProperties(props)

        showChangeOptionsFragment()
    }

    private fun showChangeOptionsFragment(){

        val transactionManager = manager.beginTransaction()
        val optionsHolderFragment = OptionsHolderFragment()

        transactionManager.replace(
            R.id.root_fragment_holder,
            optionsHolderFragment
        ).addToBackStack(OPTIONS_FRAGMENT)

        transactionManager.commit()
    }

    private fun showChangeQuoteFragment(){

        val transactionManager = manager.beginTransaction()
        val changeQuoteFragment = ChangeQuoteFragment()

        transactionManager.replace(
            R.id.root_fragment_holder,
            changeQuoteFragment
        ).addToBackStack(CHANGE_QUOTE_FRAGMENT)

        transactionManager.commit()
    }

    private fun showChangeFontStyleFragment(){

        val transactionManager = manager.beginTransaction()
        val changeFontStyleFragment = ChangeFontStyleFragment()

        transactionManager.replace(
            R.id.root_fragment_holder,
            changeFontStyleFragment
        ).addToBackStack(CHANGE_FONT_STYLE_FRAGMENT)

        transactionManager.commit()
    }

    private fun showChangeImageFragment(bundle: Bundle? = null){
        val transactionManager = manager.beginTransaction()
        val changeImageFragment = ChangeImageFragment()
        if(bundle!=null){
            changeImageFragment.arguments = bundle
        }
        transactionManager.replace(
            R.id.root_fragment_holder,
            changeImageFragment
        )
            .addToBackStack(CHANGE_IMAGE_FRAGMENT)
        transactionManager.commit()
    }
}