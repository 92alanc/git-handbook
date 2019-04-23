package com.braincorp.githandbook.model

import android.os.Parcel
import android.os.Parcelable

class Parameter(
       var name: String = "",
       var description: String = "",
       var example: String = ""
) : Parcelable {

    constructor(parcel: Parcel) : this() {
        with(parcel) {
            name = readString()!!
            description = readString()!!
            example = readString()!!
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(name)
            writeString(description)
            writeString(example)
        }
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Parameter> {
        override fun createFromParcel(source: Parcel) = Parameter(source)

        override fun newArray(size: Int) = arrayOfNulls<Parameter>(size)
    }

}