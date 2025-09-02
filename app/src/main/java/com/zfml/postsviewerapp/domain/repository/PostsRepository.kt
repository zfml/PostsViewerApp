package com.zfml.postsviewerapp.domain.repository

import com.zfml.postsviewerapp.domain.model.Post
import kotlinx.coroutines.flow.Flow

data class ResultState<out T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

interface PostsRepository {
    val posts: Flow<List<Post>>
    fun post(id: Int): Flow<Post?>
    suspend fun refresh(): ResultState<Unit>
}