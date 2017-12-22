package com.dragon.wallpaperapp.mvp.model.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by yonglong on 2017/12/5.
 */
class Wallpaper() : Parcelable {
    var preview: String? = null
    var thumb: String? = null
    var img: String? = null
    var wp: String? = null

    constructor(parcel: Parcel) : this() {
        preview = parcel.readString()
        thumb = parcel.readString()
        img = parcel.readString()
        wp = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(preview)
        parcel.writeString(thumb)
        parcel.writeString(img)
        parcel.writeString(wp)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Wallpaper> {
        override fun createFromParcel(parcel: Parcel): Wallpaper {
            return Wallpaper(parcel)
        }

        override fun newArray(size: Int): Array<Wallpaper?> {
            return arrayOfNulls(size)
        }
    }
}