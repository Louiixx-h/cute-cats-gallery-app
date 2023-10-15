package com.luishenrique.cutecatsgallery.base

interface BaseMapper<Response, UiModel> {
    fun toUiModel(response: Response?) : UiModel
    fun toResponse(uiModel: UiModel?) : Response
}