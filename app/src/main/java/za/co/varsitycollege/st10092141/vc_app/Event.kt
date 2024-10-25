package za.co.varsitycollege.st10092141.vc_app

import android.os.Parcel
import android.os.Parcelable

data class Event(
    val _id: String,
    val eventName: String,
    val eventDescription: String,
    val date: String,
    val time: String,
    val location: String,
    val file: File?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readParcelable(File::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(eventName)
        parcel.writeString(eventDescription)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(location)
        parcel.writeParcelable(file, flags)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}

data class File(
    val filename: String,
    val id: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(filename)
        parcel.writeString(id)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<File> {
        override fun createFromParcel(parcel: Parcel): File {
            return File(parcel)
        }

        override fun newArray(size: Int): Array<File?> {
            return arrayOfNulls(size)
        }
    }
}
