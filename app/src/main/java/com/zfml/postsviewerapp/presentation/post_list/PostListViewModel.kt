package com.zfml.postsviewerapp.presentation.post_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zfml.postsviewerapp.domain.model.Post
import com.zfml.postsviewerapp.domain.repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(private val repository: PostsRepository) : ViewModel() {

    data class UiState(
        val isLoading: Boolean = false,
        val posts: List<Post> = emptyList(),
        val query: String = "",
        val error: String? = null
    )

    private val _postListUiState = MutableStateFlow(UiState(isLoading = true))
    val postListUiState: StateFlow<UiState> = _postListUiState.asStateFlow()

    private val _data = MutableStateFlow<List<Post>>(emptyList())

    init {
        viewModelScope.launch {
            repository.posts.combine(_postListUiState) { posts, postListUiState ->
                val filtered = if (postListUiState.query.isBlank()) posts else posts.filter { it.title.contains(postListUiState.query, ignoreCase = true) }
                Pair(posts, filtered)
            }.collect { (all, filtered) ->
                _data.value = all
                _postListUiState.update { it.copy(posts = filtered, isLoading = false) }
            }
        }
        refresh()
    }

    fun onQueryChange(q: String) {
        _postListUiState.update { it.copy(query = q) }
    }

    fun refresh() {
        viewModelScope.launch {
            _postListUiState.update { it.copy(isLoading = true, error = null) }
            val r = repository.refresh()
            _postListUiState.update { it.copy(isLoading = false, error = r.error) }
        }
    }
}