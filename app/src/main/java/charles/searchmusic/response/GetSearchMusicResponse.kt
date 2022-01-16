package charles.searchmusic.response

import com.google.gson.annotations.SerializedName

data class GetSearchMusicResponse(
    @SerializedName("resultCount") val resultCount: Int,
    @SerializedName("results") val results: MutableList<Album>
) {
    data class Album(
        @SerializedName("wrapperType") val wrapperType: String? = null,
        @SerializedName("kind") val kind: String? = null,
        @SerializedName("artistId") val artistId: Int,
        @SerializedName("collectionId") val collectionId: Int,
        @SerializedName("trackId") val trackId: Int,
        @SerializedName("artistName") val artistName: String? = null,
        @SerializedName("collectionName") val collectionName: String? = null,
        @SerializedName("trackName") val trackName: String? = null,
        @SerializedName("collectionCensoredName") val collectionCensoredName: String? = null,
        @SerializedName("trackCensoredName") val trackCensoredName: String? = null,
        @SerializedName("artistViewUrl") val artistViewUrl: String? = null,
        @SerializedName("collectionViewUrl") val collectionViewUrl: String? = null,
        @SerializedName("trackViewUrl") val trackViewUrl: String? = null,
        @SerializedName("previewUrl") val previewUrl: String? = null,
        @SerializedName("artworkUrl30") val artworkUrl30: String? = null,
        @SerializedName("artworkUrl60") val artworkUrl60: String? = null,
        @SerializedName("artworkUrl100") val artworkUrl100: String? = null,
        @SerializedName("collectionPrice") val collectionPrice: Float? = null,
        @SerializedName("trackPrice") val trackPrice: Float? = null,
        @SerializedName("releaseDate") val releaseDate: String? = null,
        @SerializedName("collectionExplicitness") val collectionExplicitness: String? = null,
        @SerializedName("trackExplicitness") val trackExplicitness: String? = null,
        @SerializedName("discCount") val discCount: Int,
        @SerializedName("discNumber") val discNumber: Int,
        @SerializedName("trackCount") val trackCount: Int,
        @SerializedName("trackNumber") val trackNumber: Int,
        @SerializedName("trackTimeMillis") val trackTimeMillis: Int,
        @SerializedName("country") val country: String? = null,
        @SerializedName("currency") val currency: String? = null,
        @SerializedName("primaryGenreName") val primaryGenreName: String? = null,
        @SerializedName("isStreamable") val isStreamable: Boolean? = false

    ) {

    }
}