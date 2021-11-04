package com.inter.testalef.di.modules

import androidx.fragment.app.Fragment
import com.inter.testalef.di.utils.FragmentKey
import com.inter.testalef.ui.fragments.ImageFragment
import com.inter.testalef.ui.fragments.MainFragment
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface FragmentModule {

    @Binds
    @IntoMap
    @FragmentKey(MainFragment::class)
    fun bindMainFragment(fragment: MainFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(ImageFragment::class)
    fun bindImageFragment(fragment: ImageFragment): Fragment
}