package com.ai.integrator.im.identity

import androidx.room.TypeConverter
import com.ai.integrator.core.framework.serialization.json.JsonHelper
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IMIdentity(
    @SerialName("id")
    val id: Long,

    @SerialName("type")
    val type: IdentityType
)

class IMIdentityConverter {
    @TypeConverter
    fun stringToIMIdentity(string: String?): IMIdentity? {
        return JsonHelper.safeDecodeFromString(string, null)
    }

    @TypeConverter
    fun iMIdentityToString(identity: IMIdentity?): String? {
        return identity?.let { JsonHelper.safeEncodeToString(it) }
    }
}