package com.inter.testalef.extensions

import com.inter.testalef.models.entities.model.ImageModel
import com.inter.testalef.models.entities.remote.ImageModelRemote
import timber.log.Timber
import java.lang.StringBuilder


fun ImageModelRemote.toImageModel(): List<ImageModel> =
    this.map {
        ImageModel(it.addCharAtIndex(char = 's', index = 4))
    }

fun String.addCharAtIndex(char: Char, index: Int) =
    StringBuilder(this).apply { insert(index, char) }.toString()
