package com.test.pieview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.provider.CalendarContract
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi
import java.util.jar.Attributes
import kotlin.math.cos
import kotlin.math.sin

class PieView :View {
     private var  paint: Paint = Paint()
    private val mRadius: Float = dp2px(150f)
    private val COLORS   = listOf(Color.parseColor("#FFB6C1"),
        Color.parseColor("#7FFFAA"),
        Color.parseColor("#1E90FF"),
        Color.parseColor("#4169E1"))
    private val ANGLES = listOf(60f,100f,80f,120f)
    var startAngle =0f
    val selct = 0
    private  val OFFSET_LENGTH = 40f
    constructor(content: Context):this(content,null)

    constructor(content:Context,attrs:AttributeSet?):super(content,attrs){
        paint.strokeWidth = dp2px(1f)
        paint.color = Color.RED

    }

    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, Resources.getSystem().displayMetrics)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

                

        for ((index:Int,angle:Float)in ANGLES.withIndex()){

            if (index == selct ){
                /**
                 * 1
                 */
            /*    canvas.save()
                paint.color = COLORS[index]
                var x= cos(Math.toRadians(startAngle+angle/2.toDouble())) *OFFSET_LENGTH
                var y= sin(Math.toRadians(startAngle+angle/2.toDouble())) *OFFSET_LENGTH
                canvas.translate(x.toFloat(),y.toFloat())
                canvas.drawArc(width/2-mRadius,height/2-mRadius,width/2+mRadius,height/2+mRadius,startAngle,angle,true,paint)
                startAngle+=angle
                canvas.restore()*/

                /**
                 * 2
                 */
                paint.color = COLORS[index]
                var x= cos(Math.toRadians(startAngle+angle/2.toDouble())) *OFFSET_LENGTH
                var y= sin(Math.toRadians(startAngle+angle/2.toDouble())) *OFFSET_LENGTH
                canvas.drawArc(width/2-mRadius+x.toFloat(),height/2-mRadius+y.toFloat(),width/2+mRadius+x.toFloat(),height/2+mRadius+y.toFloat(),startAngle,angle,true,paint)
                startAngle+=angle

            }else{
                paint.color = COLORS[index]
                canvas.drawArc(width/2-mRadius,height/2-mRadius,width/2+mRadius,height/2+mRadius,startAngle,angle,true,paint)
                startAngle+=angle
            }

        }


    }



}