package `in`.webxstudio.android.quoteiko

import android.graphics.Bitmap
import android.graphics.Color


val defaultSize: Int = 16
val defaultColor = Color.WHITE
val defaultTextAlignment = MainActivity.Alignments.LEFT
val defaultTextStyle = MainActivity.TextStyle.NORMAL

data class ImageProps(
    var imageBitmap: Bitmap,
    var imageQuote:String,
    var quoteAuthor:String,
    var textSize:Int = defaultSize,
    var textAlignment: MainActivity.Alignments = defaultTextAlignment,
    var textStyle: MainActivity.TextStyle = defaultTextStyle,
    var textColor: Int = defaultColor
)