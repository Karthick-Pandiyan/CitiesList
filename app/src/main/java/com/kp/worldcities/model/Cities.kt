package com.kp.worldcities.model

import com.google.gson.annotations.SerializedName

class Cities {

    @SerializedName("country")
    var country: String? = null
    @SerializedName("name")
    var name: String? = null

    fun getCityAndCountryTitle(): String{
        return "'$name', '$country'."
    }
}