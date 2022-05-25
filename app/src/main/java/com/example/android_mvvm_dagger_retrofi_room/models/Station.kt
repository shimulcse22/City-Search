package com.example.android_mvvm_dagger_retrofi_room.models


import android.os.Parcel
import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
@Entity
data class Station(
    @PrimaryKey
    @ColumnInfo(name = "cityName")
    val cityName: String,

    @ColumnInfo(name = "AQI")
    val aQI: Int,

    @ColumnInfo(name = "CO")
    val cO: Double,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "countryCode")
    val countryCode: String,

    @ColumnInfo(name = "division")
    val division: String,

    @ColumnInfo(name = "_id")
    val id: String,

    @ColumnInfo(name = "lat")
    val lat: Double,

    @ColumnInfo(name = "lng")
    val lng: Double,

    @ColumnInfo(name = "NO2")
    val nO2: Double,

    @ColumnInfo(name = "OZONE")
    val oZONE: Double,

    @ColumnInfo(name ="PM10")
    val pM10: Double,

    @ColumnInfo(name = "PM25")
    val pM25: Double,

    @ColumnInfo(name = "placeId")
    val placeId: String,

    @ColumnInfo(name = "placeName")
    val placeName: String,

    @ColumnInfo(name = "postalCode")
    val postalCode: String,

    @ColumnInfo(name = "SO2")
    val sO2: Double,

    @ColumnInfo(name = "state")
    val state: String,

    @ColumnInfo(name="updateAt")
    val updatedAt: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(cityName)
        parcel.writeInt(aQI)
        parcel.writeDouble(cO)
        parcel.writeString(city)
        parcel.writeString(countryCode)
        parcel.writeString(division)
        parcel.writeString(id)
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
        parcel.writeDouble(nO2)
        parcel.writeDouble(oZONE)
        parcel.writeDouble(pM10)
        parcel.writeDouble(pM25)
        parcel.writeString(placeId)
        parcel.writeString(placeName)
        parcel.writeString(postalCode)
        parcel.writeDouble(sO2)
        parcel.writeString(state)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Station> {
        override fun createFromParcel(parcel: Parcel): Station {
            return Station(parcel)
        }

        override fun newArray(size: Int): Array<Station?> {
            return arrayOfNulls(size)
        }
    }
}