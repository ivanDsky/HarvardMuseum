package ua.zloydi.exhibition.usecase.models

import com.google.gson.annotations.SerializedName

public data class ExhibitionQuery(
	val info: Info,
	val records: List<ExhibitionItem>
)

public data class Info(
	val pages: Int,
	val page: Int,
	@SerializedName("totalrecords")
	val totalRecords: Int
)

