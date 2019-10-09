package com.example.findme.network.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "user")
@Parcelize
data class UserResponse(
    @Expose @SerializedName("id") @PrimaryKey @ColumnInfo(name = "user_id") val id: Int,
    @Expose @SerializedName("name") @ColumnInfo(name = "name") val name: String,
    @Expose @SerializedName("username") @ColumnInfo(name = "username") val userName: String,
    @ColumnInfo(name = "is_fav") var isFav: Int = 0,
    @Expose @SerializedName("email") @ColumnInfo(name = "email") val email: String,
    @Expose @SerializedName("phone") @ColumnInfo(name = "phone") val phone: String,
    @Expose @SerializedName("website") @ColumnInfo(name = "website") val website: String,
    @Expose @SerializedName("address") @Embedded(prefix = "addr_") val address: Address,
    @Expose @SerializedName("company") @Embedded(prefix = "company_") val company: Company
) : Parcelable

@Parcelize
data class Address(
    @Expose @SerializedName("street") @ColumnInfo(name = "street") val street: String,
    @Expose @SerializedName("suite") @ColumnInfo(name = "suite") val suite: String,
    @Expose @SerializedName("city") @ColumnInfo(name = "city") val city: String,
    @Expose @SerializedName("zipcode") @ColumnInfo(name = "zipcode") val zipCode: String,
    @Expose @SerializedName("geo") @Embedded(prefix = "geo_") val location: Location
) : Parcelable

@Parcelize
data class Location(
    @Expose @SerializedName("lat") @ColumnInfo(name = "latitude") val latitude: String,
    @Expose @SerializedName("lng") @ColumnInfo(name = "longitude") val longitude: String
) : Parcelable

@Parcelize
data class Company(
    @Expose @SerializedName("name") @ColumnInfo(name = "name") val CompanyName: String,
    @Expose @SerializedName("catchPhrase") @ColumnInfo(name = "catch_Phrase") val catchPhrase: String,
    @Expose @SerializedName("bs") @ColumnInfo(name = "bs") val bs: String
) : Parcelable

