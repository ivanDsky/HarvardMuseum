package ua.zloydi.exhibibtion.models

import com.google.gson.annotations.SerializedName

data class ExhibitionItem(
	@SerializedName("exhibitionid")
	val exhibitionId: Int,
	@SerializedName("title")
	val title: String,
	@SerializedName("begindate")
	val beginDate: String,
	@SerializedName("enddate")
	val endDate: String,
	@SerializedName("primaryimageurl")
	val primaryImageUrl: String?,
)