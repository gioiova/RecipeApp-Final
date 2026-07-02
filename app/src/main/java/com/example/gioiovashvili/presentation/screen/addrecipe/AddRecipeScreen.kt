package com.example.gioiovashvili.presentation.screen.addrecipe

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gioiovashvili.R
import com.example.gioiovashvili.presentation.extension.CollectSideEffects

@Composable
fun AddRecipeScreen(
    onNavigateBack: () -> Unit,
    viewModel: AddRecipeViewModel = hiltViewModel()
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    viewModel.sideEffect.CollectSideEffects { sideEffect ->
        when (sideEffect) {
            is AddRecipeSideEffect.ShowToast -> {
                Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
            }

            AddRecipeSideEffect.NavigateBack -> onNavigateBack()
        }
    }

    AddRecipeContent(
        state = state,
        onBackClick = viewModel::onBackClick,
        onSaveClick = viewModel::onSaveClick,
        onTitleChange = viewModel::onTitleChanged,
        onDescriptionChange = viewModel::onDescriptionChanged,
        onImageUrlChange = viewModel::onImageUrlChanged,
        onPrepTimeChange = viewModel::onPrepTimeChanged,
        onCookTimeChange = viewModel::onCookTimeChanged,
        onDifficultyChange = viewModel::onDifficultyChanged,
        onRatingChange = viewModel::onRatingChanged,
        onIngredientsChange = viewModel::onIngredientsChanged,
        onInstructionsChange = viewModel::onInstructionsChanged
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddRecipeContent(
    state: AddRecipeState,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onImageUrlChange: (String) -> Unit,
    onPrepTimeChange: (String) -> Unit,
    onCookTimeChange: (String) -> Unit,
    onDifficultyChange: (String) -> Unit,
    onRatingChange: (String) -> Unit,
    onIngredientsChange: (String) -> Unit,
    onInstructionsChange: (String) -> Unit
) {
    var isDifficultyExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.add_recipe_title)) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = stringResource(R.string.add_recipe_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            item {
                FormSection(title = stringResource(R.string.add_recipe_section_basic)) {
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = onTitleChange,
                        label = { Text(stringResource(R.string.add_recipe_field_title)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.description,
                        onValueChange = onDescriptionChange,
                        label = { Text(stringResource(R.string.add_recipe_field_description)) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 3
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = state.imageUrl,
                        onValueChange = onImageUrlChange,
                        label = { Text(stringResource(R.string.add_recipe_field_image)) },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                }
            }

            item {
                FormSection(title = stringResource(R.string.add_recipe_section_details)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedTextField(
                            value = state.prepTimeMinutes,
                            onValueChange = onPrepTimeChange,
                            label = { Text(stringResource(R.string.add_recipe_field_prep)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = state.cookTimeMinutes,
                            onValueChange = onCookTimeChange,
                            label = { Text(stringResource(R.string.add_recipe_field_cook)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = isDifficultyExpanded,
                            onExpandedChange = { isDifficultyExpanded = it },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = state.difficulty,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(R.string.add_recipe_field_difficulty)) },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDifficultyExpanded)
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = isDifficultyExpanded,
                                onDismissRequest = { isDifficultyExpanded = false }
                            ) {
                                state.difficultyOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = { Text(option) },
                                        onClick = {
                                            onDifficultyChange(option)
                                            isDifficultyExpanded = false
                                        }
                                    )
                                }
                            }
                        }

                        OutlinedTextField(
                            value = state.rating,
                            onValueChange = onRatingChange,
                            label = { Text(stringResource(R.string.add_recipe_field_rating)) },
                            modifier = Modifier.weight(1f),
                            singleLine = true
                        )
                    }
                }
            }

            item {
                FormSection(title = stringResource(R.string.add_recipe_section_ingredients)) {
                    OutlinedTextField(
                        value = state.ingredientsText,
                        onValueChange = onIngredientsChange,
                        label = { Text(stringResource(R.string.add_recipe_field_ingredients)) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 5,
                        placeholder = { Text(stringResource(R.string.add_recipe_ingredients_hint)) }
                    )
                }
            }

            item {
                FormSection(title = stringResource(R.string.add_recipe_section_instructions)) {
                    OutlinedTextField(
                        value = state.instructionsText,
                        onValueChange = onInstructionsChange,
                        label = { Text(stringResource(R.string.add_recipe_field_instructions)) },
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 6,
                        placeholder = { Text(stringResource(R.string.add_recipe_instructions_hint)) }
                    )
                }
            }

            if (state.error != null) {
                item {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            item {
                if (state.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                } else {
                    Button(
                        onClick = onSaveClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.add_recipe_save),
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FormSection(
    title: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(12.dp))

            content()
        }
    }
}
