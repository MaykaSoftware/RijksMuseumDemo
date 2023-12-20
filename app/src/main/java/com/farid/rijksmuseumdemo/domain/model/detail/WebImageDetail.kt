package com.farid.rijksmuseumdemo.domain.model.detail

data class WebImageDetail(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)