package media.taylor.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_brush_size.*

class MainActivity : AppCompatActivity() {

    private var mImageButtonCurrentPaint : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        //I KNOW I SHOULDNT HAVE CODE IN MY MAINACTIVITY PLS FORGIVE ME :'(
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView.setSizeforBrush(20.toFloat())

        mImageButtonCurrentPaint = colorPaletteLayout[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.palette_pressed)
        )

        brushIcon.setOnClickListener {
            showBrushSizeChooserDialog()
        }
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")

        val smallBtn = brushDialog.ib_small_brush
        smallBtn.setOnClickListener {
            drawingView.setSizeforBrush(10.toFloat())
            brushDialog.dismiss()
        }

        val medBtn = brushDialog.ib_medium_brush
        medBtn.setOnClickListener {
            drawingView.setSizeforBrush(20.toFloat())
            brushDialog.dismiss()
        }

        val lrgBtn = brushDialog.ib_large_brush
        lrgBtn.setOnClickListener {
            drawingView.setSizeforBrush(30.toFloat())
            brushDialog.dismiss()
        }


        brushDialog.show()
    }

    fun paintClicked(view: View) {
        if (view != mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton

            val colorTag = imageButton.tag.toString()
            drawingView.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.palette_pressed)
            )
            mImageButtonCurrentPaint!!.setImageDrawable(ContextCompat.getDrawable(
                this, R.drawable.palette_normal))

            mImageButtonCurrentPaint = view
        }
    }
}