package `in`.webxstudio.android.quoteiko.ChangeStyleFragments

import `in`.webxstudio.android.quoteiko.*
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.view.children
import kotlinx.android.synthetic.main.fragment_change_image.*

class ChangeImageFragment : Fragment() {

    lateinit var fragmentView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var imageViewList : List<ImageView>
    private lateinit var imageResourceList: List<Int>
    private lateinit var selectedCardView: CardView

    private var currentImageID :Int? = null
    val TAG = "ChangeImageFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_change_image, container, false)



        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currentImageID = arguments?.getInt(CURRENTLY_PREVIEWING)
        imageViewList = listOf<ImageView>(
            adrien_olichon,
            haydn_golden,
            huyen_nguyen,
            jeremy_weber,
            jonathan_daniels,
            jordan_whitt,
            joshua_chun,
            matthew_pablico,
            pablo_heimplatz,
            s_b_vonlanthen,
            will_suddreth
        )

        imageResourceList = listOf(
            R.drawable.adrien_olichon,
            R.drawable.haydn_golden,
            R.drawable.huyen_nguyen,
            R.drawable.jeremy_weber,
            R.drawable.jonathan_daniels,
            R.drawable.jordan_whitt,
            R.drawable.joshua_chun,
            R.drawable.matthew_pablico,
            R.drawable.pablo_heimplatz,
            R.drawable.s_b_vonlanthen,
            R.drawable.will_suddreth
        )

        setTagsAndListener()
        markCurrentSelectedImage(currentImageID)
    }

    fun markCurrentSelectedImage(selectedImageResourceID:Int?,currentState: ImageCardState = ImageCardState.PREVIEWING){
        for (image in imageViewList){
            if(image.getTag(R.string.resource_id) == selectedImageResourceID){
                Log.d(TAG,"YAY 🙌🏼 we found the match!")
                selectedCardView = image.parent as CardView
                selectedCardView.setTag(R.string.card_state,ImageCardState.PREVIEWING)
                layoutInflater.inflate(R.layout.preselected_image,selectedCardView)
            }
        }
    }

    val onCardSelected = View.OnClickListener{
        Log.d(TAG,"Current Tag is ${it.tag}")
        // whenever a card is clicked this is called
        if (it.getTag(R.string.card_state) == ImageCardState.PREVIEWING){
            // if the card which was clicked is preselected
            Log.d(TAG,"Card is preselected moving forward without doing anything")
        }
        else{
            // a new imageCard was selected time to update app-state
            highlightCard(it as CardView)
        }
    }

    fun highlightCard(viewToHighlight:CardView){
        list_of_cards.children.forEach {
            when(it as CardView){
                viewToHighlight ->{
                    // do the steps to highlight the card
                    if (it.getTag(R.string.card_state) == ImageCardState.SELECTED){
                        Log.d(TAG,"This card is currently selected nothing to do here")
                    }else{
                        Log.d(TAG,"Selecting a new Image")
                        it.setTag(R.string.card_state,ImageCardState.SELECTED)
                        it.elevation = 8f
                        layoutInflater.inflate(R.layout.currently_selected_image,it)
                    }
                }
                else->{
                    if (it.getTag(R.string.card_state)==ImageCardState.PREVIEWING){
                        Log.d(TAG,"Card is previewing no need to change it")
                    }else{
                        it.setTag(R.string.card_state,ImageCardState.UNSELECTED)
                        it.cardElevation = 1f
                        if(it.childCount > 1){
                            it.removeViewAt(1)
                        }
                    }
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        currentImageID = arguments?.getInt(CURRENTLY_PREVIEWING)
        super.onAttach(context)
    }

    interface onImageChanged{
        fun onImageChanged(resourceInt:Int){}
    }


    fun setTagsAndListener(){
        /*
            This adds tags to every image view so that we can recognize it later
            as we are recognizing based on the resource id's
         */

        for (imageView in imageViewList.withIndex()){
            // on using BitmapFactory it uses almost 10 times the memory and
            // causes the app to lag use resource updates to make it lightweight
            //imageView.value.setImageBitmap(BitmapFactory.decodeResource(
            // resources,imageResourceList[imageView.index]))
            imageView.value.setImageResource(imageResourceList[imageView.index])

            imageView.value.setTag(R.string.resource_id,
                imageResourceList[imageView.index]
            )

            imageView.value.setTag(R.string.card_state,
                ImageCardState.UNSELECTED
            )

            (imageView.value.parent as CardView).setOnClickListener(
                onCardSelected
            )
        }
    }
}