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

val defaultUnknownIMIdentity by lazy {
    IMIdentity(
        id = 0L,
        type = IdentityType.UNKNOWN
    )
}

class IMIdentityConverter {
    @TypeConverter
    fun stringToIMIdentity(string: String): IMIdentity {
        return JsonHelper.safeDecodeFromString(string, defaultUnknownIMIdentity)
    }

    @TypeConverter
    fun iMIdentityToString(identity: IMIdentity): String {
        return JsonHelper.safeEncodeToString(identity)
    }

    @TypeConverter
    fun stringToIMIdentities(string: String): List<IMIdentity> {
        return JsonHelper.safeDecodeFromString(string, emptyList())
    }

    @TypeConverter
    fun iMIdentitiesToString(identities: List<IMIdentity>): String {
        return JsonHelper.safeEncodeToString(identities)
    }
}