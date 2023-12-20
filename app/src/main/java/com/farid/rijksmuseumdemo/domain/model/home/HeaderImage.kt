package com.farid.rijksmuseumdemo.domain.model.home

data class HeaderImage(
    val guid: String,
    val height: Int,
    val offsetPercentageX: Int,
    val offsetPercentageY: Int,
    val url: String,
    val width: Int
)