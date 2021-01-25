package com.test.pieview

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.RequiresApi
import com.test.pieview.R

import kotlin.math.cos
import kotlin.math.sin


class PanelView : View {
    private val mRadius: Float = dp2px(150f)
    lateinit var paint: Paint
    lateinit var path: Path
    lateinit var markPath: Path
    lateinit var pathEffect: PathDashPathEffect
    var count = 0.0
    var degreeSize = 20

    constructor(context: Context) : this(context, null)

    @SuppressLint("NewApi")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        paint = Paint()
        paint.strokeWidth = dp2px(5f)
        paint.strokeCap = Paint.Cap.BUTT
        paint.style = Paint.Style.STROKE
        paint.color = getContext().getColor(R.color.teal_200)
        path = Path()
        markPath = Path()
        markPath.addRect(0f, 0f, dp2px(2f), dp2px(10f), Path.Direction.CCW)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        path.addArc(width / 2 - mRadius, height / 2 - mRadius, width / 2 + mRadius, height / 2 + mRadius, 150f, 240f)
        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(markPath, (pathMeasure.length-dp2px(2f)) / degreeSize, 0f, PathDashPathEffect.Style.ROTATE)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


            paint.color = resources.getColor(R.color.teal_200)


        canvas.drawPath(path, paint)


        paint.pathEffect = pathEffect
        path.addArc(width / 2 - mRadius, height / 2 - mRadius, width / 2 + mRadius, height / 2 + mRadius, 150f, 240f)

        canvas.drawPath(path, paint)
        paint.setPathEffect(null)


        paint.color = Color.RED
        paint.strokeWidth = dp2px(2f)

     /*   canvas.drawLine((width / 2).toFloat(), (height / 2).toFloat(),
                dp2px(100f) * cos((150 + 240 / 20 * count) * (Math.PI / 180)).toFloat(),
                dp2px(100f) * sin((150 + 240 / 20 * count) * (Math.PI / 180)).toFloat(),
                paint)*/
        canvas.drawLine((width / 2).toFloat(), (height / 2).toFloat(),
                (width / 2).toFloat()+dp2px(150f) * cos((150 + 240 / degreeSize * count) * (Math.PI / 180)).toFloat(),
                (height / 2).toFloat()+dp2px(150f) * sin((150 + 240 / degreeSize * count) * (Math.PI / 180)).toFloat(),
                paint)

    }


    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
    }

}