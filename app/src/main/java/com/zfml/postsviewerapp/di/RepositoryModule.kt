package com.zfml.postsviewerapp.di

import com.zfml.postsviewerapp.data.local.PostDao
import com.zfml.postsviewerapp.data.remote.PostApiService
import com.zfml.postsviewerapp.data.repository.PostsRepositoryImpl
import com.zfml.postsviewerapp.domain.repository.PostsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providePostsRepository(api: PostApiService, dao: PostDao): PostsRepository =
        PostsRepositoryImpl(api, dao)
}