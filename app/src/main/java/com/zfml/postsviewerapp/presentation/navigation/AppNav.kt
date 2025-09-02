package com.zfml.postsviewerapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zfml.postsviewerapp.presentation.post_detail.PostDetailScreen
import com.zfml.postsviewerapp.presentation.post_list.PostListScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Posts) {
        composable<Posts> {
            PostListScreen(onPostClick = {
                navController.navigate(PostDetail(it))
            })
        }
        composable<PostDetail>{ entry ->
            val postId = entry.toRoute<PostDetail>().id
            PostDetailScreen(postId = postId, onBack = { navController.popBackStack() })
        }
    }
}

@Serializable
object Posts

@Serializable
data class PostDetail(val id: Int)