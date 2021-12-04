package co.com.ceiba.mobile.androidtestceiba.data.remote.dto

import co.com.ceiba.mobile.androidtestceiba.domain.models.PostsEntity

/**
 * Modelo de datos que llegan del servicio de tipo post
 */
data class PostDto(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)

/**
 * Función que transforma el objeto tipo lista de posts provenientes del api en un objeto de tipo posts entity
 */
fun List<PostDto>.getPostsEntity(): List<PostsEntity> {
    return map { postDto ->
        PostsEntity(
            postId = postDto.id,
            userId = postDto.userId,
            postTitle = postDto.title,
            postBody = postDto.body
        )
    }
}