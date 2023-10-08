package com.luishenrique.cutecatsgallery.home.domain.model

data class CardImage(
    val title: String,
    val username: String,
    val score: Int,
    val images: List<CardImageInfo>
)