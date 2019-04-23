package com.braincorp.githandbook.model

import android.os.Parcel
import android.os.Parcelable

class Command(
        var name: String = "",
        var params: Array<Parameter> = arrayOf()
) : Parcelable {

    @Suppress("unchecked_cast")
    constructor(parcel: Parcel) : this() {
        with(parcel) {
            name = readString()!!
            params = readParcelableArray(Parameter::class.java.classLoader) as Array<Parameter>
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(name)
            writeParcelableArray(params, flags)
        }
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<Command> {
        override fun createFromParcel(source: Parcel) = Command(source)

        override fun newArray(size: Int) = arrayOfNulls<Command>(size)
    }

}