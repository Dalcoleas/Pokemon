package com.example.tareaclase.Models

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(val id: Int,val name: String) : Parcelable{

    constructor(parcel: Parcel) : this(
        id = parcel.readInt(),
        name = parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon {
            return Pokemon(parcel)
        }

        override fun newArray(size: Int): Array<Pokemon?> {
            return arrayOfNulls(size)
        }
    }
}