package com.example.waridihomes.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.waridihomes.data.model.modelresponse.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(
    private val gson: Gson
) {

    @TypeConverter
    fun fromUser(user: User): String{
        return gson.toJson(user)
    }

    @TypeConverter
    fun toUser(json: String): User{
        val type = object : TypeToken<User>() {}.type
        return  gson.fromJson(json, type)
    }
}