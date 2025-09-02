package com.zfml.postsviewerapp.data.mappers

import com.zfml.postsviewerapp.data.local.PostEntity
import com.zfml.postsviewerapp.data.remote.PostDto
import com.zfml.postsviewerapp.domain.model.Post

fun PostDto.toEntity() = PostEntity(id = id, title = title, body = body, userId = userId)
fun PostEntity.toDomain() = Post(id = id, title = title, body = body, userId = userId)
fun List<PostEntity>.toDomainList() = map { it.toDomain() }


