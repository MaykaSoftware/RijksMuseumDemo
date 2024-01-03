package nl.qbus.beleef.onboarding.presentation

import androidx.annotation.DrawableRes
import com.feature.onboarding.ui.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages = listOf<Page>(
    Page(
        title = "Lorum Ipsum Dummy 1",
        description = "Lorum Ipsum Dummy more text to fit a description but it hasn't any value at all",
        image = R.drawable.page1
    ),
    Page(
        title = "Lorum Ipsum Dummy 2",
        description = "Lorum Ipsum Dummy more text to fit a description but it hasn't any value at all",
        image = R.drawable.page2
    ),
    Page(
        title = "Lorum Ipsum Dummy 3",
        description = "Lorum Ipsum Dummy more text to fit a description but it hasn't any value at all",
        image = R.drawable.page1
    )
)