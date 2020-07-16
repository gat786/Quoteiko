package `in`.webxstudio.android.quoteiko.ChangeStyleFragments

import `in`.webxstudio.android.quoteiko.R
import `in`.webxstudio.android.quoteiko.SELECTED_IMAGE
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
import kotlinx.android.synthetic.main.fragment_change_image.*

class ChangeImageFragment : Fragment() {

    lateinit var fragmentView:View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var imageList : List<ImageView>

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
        currentImageID = arguments?.getInt(SELECTED_IMAGE)
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
        markCurrentSelectedImage(currentImageID)
    }

    fun markCurrentSelectedImage(selectedImageResourceID:Int?){
        for (image in imageList){
            if(image.tag == selectedImageResourceID){
                val cardView = image.parent as CardView
                layoutInflater.inflate(R.layout.selected_image,cardView)
            }
        }
    }

    override fun onAttach(context: Context) {
        currentImageID = arguments?.getInt(SELECTED_IMAGE)
        super.onAttach(context)
    }

    interface onImageChanged{
        fun onImageChanged(resourceInt:Int){}
    }

    fun setTags(){
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