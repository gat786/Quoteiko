package `in`.webxstudio.android.quoteiko.ChangeStyleFragments

import `in`.webxstudio.android.quoteiko.*
import android.content.Context
import android.media.Image
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

    private lateinit var imageList : List<ImageView>
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
        setTags()
        currentImageID = arguments?.getInt(CURRENTLY_PREVIEWING)
        imageList = listOf<ImageView>(
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

        for(childIndex in 0 until list_of_cards.childCount){
            list_of_cards.getChildAt(childIndex).setOnClickListener(onCardSelected)
            list_of_cards.getChildAt(childIndex).setTag(ImageCardState.UNSELECTED)
        }

        markCurrentSelectedImage(currentImageID)
    }

    fun markCurrentSelectedImage(selectedImageResourceID:Int?,currentState: ImageCardState = ImageCardState.PREVIEWING){
        for (image in imageList){
            if(image.tag == selectedImageResourceID){
                Log.d(TAG,"YAY 🙌🏼 we found the match!")
                selectedCardView = image.parent as CardView
                selectedCardView.setTag(ImageCardState.PREVIEWING)
                Log.d(TAG,"Current tag is ${selectedCardView.tag}")
                layoutInflater.inflate(R.layout.preselected_image,selectedCardView)
            }
        }
    }

    val onCardSelected = View.OnClickListener{
        Log.d(TAG,"Current Tag is ${it.tag}")
        // whenever a card is clicked this is called
        if (it.tag == ImageCardState.PREVIEWING){
            // if the card which was clicked is preselected

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
                    if (it.tag.equals(ImageCardState.SELECTED)){
                        Log.d(TAG,"This card is currently selected nothing to do here")
                    }else{
                        Log.d(TAG,"Selecting a new Image")
                        it.setTag(ImageCardState.SELECTED)
                        it.elevation = 8f
                        layoutInflater.inflate(R.layout.currently_selected_image,it)
                    }
                }
                else->{
                    if (it.tag==ImageCardState.PREVIEWING){
                        Log.d(TAG,"Card is previewing no need to change it")
                    }else{
                        it.setTag(ImageCardState.UNSELECTED)
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


    fun setTags(){
        /*
            This adds tags to every image view so that we can recognize it later
            as we are recognizing based on the resource id's
         */
        adrien_olichon.tag = R.drawable.adrien_olichon
        haydn_golden.tag = R.drawable.haydn_golden
        huyen_nguyen.tag = R.drawable.huyen_nguyen
        jeremy_weber.tag = R.drawable.jeremy_weber
        jonathan_daniels.tag = R.drawable.jonathan_daniels
        jordan_whitt.tag = R.drawable.jordan_whitt
        joshua_chun.tag = R.drawable.joshua_chun
        matthew_pablico.tag = R.drawable.matthew_pablico
        pablo_heimplatz.tag = R.drawable.pablo_heimplatz
        s_b_vonlanthen.tag = R.drawable.s_b_vonlanthen
        will_suddreth.tag = R.drawable.will_suddreth
    }
}