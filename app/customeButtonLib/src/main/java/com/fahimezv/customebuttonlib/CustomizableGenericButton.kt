package com.fahimezv.customebuttonlib

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.fahimezv.customebuttonlib.DefineParamsLayout.Linear.Companion.defaultParams
import com.fahimezv.customebuttonlib.DefineParamsLayout.Linear.Companion.wrapContent


class CustomizableGenericButton : LinearLayout {


    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context)
            : super(context) {
        initView(null)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }


    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?)
            : super(context, attrs) {
        initView(attrs)
    }


    //UI
    private lateinit var mLinearLayout: LinearLayout
    private lateinit var mIconImageView: AppCompatImageView
    private lateinit var mTitleTextView: AppCompatTextView
    private lateinit var mSubTitleTextView: AppCompatTextView

    //Parameter
    private var textTitle: String? = null
    private var textSubTitle: String? = null
    private var imageResource: Int? = null
    private var padding: Int = 25
    private var margin: Int = 10

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.MyCustomButtonView, 0, 0).apply {

            try {

                //Setting for parent
                orientation = HORIZONTAL
                gravity = Gravity.CENTER
                setPadding(padding, padding, padding, padding)
                minimumHeight=160
                //Set selector default android
                foreground = getSelector(context)
                //Set corner Shape and color theme
                background = RectangleShape(getColor(context, R.attr.colorPrimary), 30f)

                //Get value
                textSubTitle = getString(R.styleable.MyCustomButtonView_subTitleText)
                textTitle = getString(R.styleable.MyCustomButtonView_titleText)
                imageResource = getResourceId(R.styleable.MyCustomButtonView_imageResource, 0)

                mIconImageView = AppCompatImageView(context).apply {
                    imageResource?.let {
                        setImageResource(it)
                    }
                }
                if (imageResource != 0)
                    addView(mIconImageView, DefineParamsLayout.Linear.get(100, 100))

                // Setup inner LinearLayout
                mLinearLayout = createInnerLayout()

                addView(mLinearLayout, defaultParams())
            } finally {
                recycle()
            }
        }


    }

    private fun createInnerLayout() = LinearLayout(context).apply {
        //Setting
        orientation = VERTICAL
        gravity = Gravity.CENTER_HORIZONTAL

        //Add Title
        mTitleTextView = createTitleTextView()

        //Add SubTitle
        mSubTitleTextView = createSubTitleView()

        if (textTitle != null)
            addView(mTitleTextView, wrapContent())

        if (textSubTitle != null)
            addView(mSubTitleTextView, wrapContent())

    }


    private fun createTitleTextView() = AppCompatTextView(context).apply {
        text = textTitle
        setTextColor(Color.WHITE)
        textSize = 18F
        setTypeface(typeface, Typeface.BOLD)


    }

    private fun createSubTitleView() = AppCompatTextView(context).apply {
        text = textSubTitle
        setTextColor(Color.WHITE)
        textSize = 18F

    }

    //Get color from theme color
    @ColorInt
    private fun getColor(context: Context, resId: Int): Int {
        val theme: Resources.Theme = context.theme
        val typedValue = TypedValue()
        theme.resolveAttribute(resId, typedValue, true)
        @ColorRes val colorId = typedValue.resourceId
        return ContextCompat.getColor(context, colorId)
    }

    //Get selector from attr
     private fun getSelector(context: Context):Drawable?{
         val outValue = TypedValue()
         context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
       return  ContextCompat.getDrawable(context, outValue.resourceId)


     }

}