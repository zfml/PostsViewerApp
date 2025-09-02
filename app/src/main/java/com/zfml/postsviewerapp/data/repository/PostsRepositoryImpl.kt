package com.zfml.postsviewerapp.data.repository

import androidx.datastore.core.IOException
import com.zfml.postsviewerapp.data.local.PostDao
import com.zfml.postsviewerapp.data.mappers.toDomain
import com.zfml.postsviewerapp.data.mappers.toDomainList
import com.zfml.postsviewerapp.data.mappers.toEntity
import com.zfml.postsviewerapp.data.remote.PostApiService
import com.zfml.postsviewerapp.domain.model.Post
import com.zfml.postsviewerapp.domain.repository.PostsRepository
import com.zfml.postsviewerapp.domain.repository.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import okhttp3.internal.http2.ConnectionShutdownException


class PostsRepositoryImpl(
    private val api: PostApiService,
    private val dao: PostDao
) : PostsRepository {

    override val posts: Flow<List<Post>> = dao.observePosts().map { it.toDomainList() }

    override fun post(id: Int): Flow<Post?> = dao.observePost(id).map { it?.toDomain() }

    override suspend fun refresh(): ResultState<Unit> = withContext(Dispatchers.IO) {
        try {
            val remote = api.getPosts()
            val entities = remote.map { it.toEntity() }
            dao.clear()
            dao.insertPosts(entities)
            ResultState(data = Unit)
        } catch (e: Exception) {
            val message = when (e) {
                is IOException -> "Network error. Showing cached posts."
                is ConnectionShutdownException -> "Connection interrupted."
                else -> e.localizedMessage ?: "Unknown error"
            }
            ResultState(error = message)
        }
    }
}