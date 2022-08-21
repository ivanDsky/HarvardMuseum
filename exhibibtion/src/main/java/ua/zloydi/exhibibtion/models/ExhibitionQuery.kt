package ua.zloydi.exhibibtion.models

import com.google.gson.annotations.SerializedName

internal data class ExhibitionQuery(
	val info: Info,
	val records: List<ExhibitionItem>
)

internal data class Info(
	val pages: Int,
	val page: Int,
	@SerializedName("totalrecords")
	val totalRecords: Int
)

