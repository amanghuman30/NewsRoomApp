package com.newsroom.app.data.di

import com.newsroom.app.ui.fragments.ArticleFragment
import dagger.Component
import dagger.Subcomponent

@Subcomponent
@FeatureScope
interface FeatureComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create() : FeatureComponent
    }

    fun inject(frag : ArticleFragment)

}