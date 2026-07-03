package com.example.gioiovashvili.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.gioiovashvili.R
import com.example.gioiovashvili.domain.model.Recipe
import com.example.gioiovashvili.presentation.extension.CollectSideEffects

@Composable
fun HomeScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToAbout: () -> Unit,
    onNavigateToAddRecipe: () -> Unit,
    onNavigateToDetails: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.CollectSideEffects { sideEffect ->
        when (sideEffect) {
            is HomeSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            HomeSideEffect.NavigateToLogin -> onNavigateToLogin()
            HomeSideEffect.NavigateToAbout -> onNavigateToAbout()
            HomeSideEffect.NavigateToAddRecipe -> onNavigateToAddRecipe()
        }
    }

    HomeContent(
        state = state,
        onSearchQueryChange = viewModel::onSearchQueryChanged,
        onAboutClick = viewModel::onAboutClick,
        onAddRecipeClick = viewModel::onAddRecipeClick,
        onLogoutClick = viewModel::onLogoutClick,
        onRecipeClick = onNavigateToDetails
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeContent(
    state: HomeState,
    onSearchQueryChange: (String) -> Unit,
    onAboutClick: () -> Unit,
    onAddRecipeClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onRecipeClick: (String) -> Unit
) {
    var isMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.home_title)) },
                actions = {
                    IconButton(onClick = { isMenuExpanded = true }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.menu)
                        )
                    }
                    DropdownMenu(
                        expanded = isMenuExpanded,
                        onDismissRequest = { isMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_add_recipe)) },
                            onClick = {
                                isMenuExpanded = false
                                onAddRecipeClick()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.menu_about)) },
                            onClick = {
                                isMenuExpanded = false
                                onAboutClick()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(R.string.log_out)) },
                            onClick = {
                                isMenuExpanded = false
                                onLogoutClick()
                            }
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (state.isLoading && state.recipes.isEmpty()) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            } else if (state.error != null && state.recipes.isEmpty()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        SearchBar(
                            modifier = Modifier.fillMaxWidth(),
                            query = state.searchQuery,
                            onQueryChange = onSearchQueryChange,
                            onSearch = { },
                            active = false,
                            onActiveChange = { },
                            placeholder = { Text("Search recipes...") },
                            leadingIcon = {
                                Icon(Icons.Default.Search, contentDescription = "Search Icon")
                            },
                            trailingIcon = {
                                if (state.searchQuery.isNotEmpty()) {
                                    IconButton(onClick = { onSearchQueryChange("") }) {
                                        Icon(Icons.Default.Clear, contentDescription = "Clear Icon")
                                    }
                                }
                            },
                            windowInsets = WindowInsets(0, 0, 0, 0)
                        ) {}
                    }

                    item {
                        Text(
                            text = if (state.searchQuery.isEmpty()) "Popular Recipes 🍲" else "Search Results 🔍",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    if (state.recipes.isEmpty() && state.searchQuery.isNotEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "No recipes found 😕",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    items(state.recipes, key = { it.id }) { recipe ->
                        RecipeItem(
                            recipe = recipe,
                            onClick = { onRecipeClick(recipe.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RecipeItem(
    recipe: Recipe,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        onClick = onClick
    ) {
        Column {
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.ic_launcher_background),
                error = painterResource(R.drawable.ic_launcher_background)
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = recipe.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )

                Spacer(modifier = Modifier.height(12.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    SuggestionChip(
                        onClick = { },
                        label = { Text("⏱️ ${recipe.totalTimeMinutes} mins", maxLines = 1) }
                    )
                    SuggestionChip(
                        onClick = { },
                        label = { Text("🔥 ${recipe.difficulty}", maxLines = 1) }
                    )
                    SuggestionChip(
                        onClick = { },
                        label = {
                            Text(
                                text = "⭐ ${recipe.rating}",
                                maxLines = 1,
                                softWrap = false,
                                overflow = TextOverflow.Visible
                            )
                        }
                    )
                }
            }
        }
    }
}
