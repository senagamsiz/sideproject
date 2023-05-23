package nl.sena.sideproject.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Numbers(
    @SerialName("numbers")
    val numbers: List<Number>
){
    @Serializable
    data class Number(
        @SerialName("id")
        val id: String,
        @SerialName("value")
        val value: String
    )
}
