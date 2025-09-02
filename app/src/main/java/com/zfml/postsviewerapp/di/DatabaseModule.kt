package com.zfml.postsviewerapp.di

import android.content.Context
import androidx.room.Room
import com.zfml.postsviewerapp.data.local.AppDatabase
import com.zfml.postsviewerapp.data.local.PostDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "posts.db").build()

    @Provides
    @Singleton
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()
}