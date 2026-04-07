package com.example.kotoba.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kotoba.data.AlphabetData
import com.example.kotoba.data.Chapter
import com.example.kotoba.data.KanaType
import com.example.kotoba.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChapterListScreen(
    viewModel: MainViewModel,
    kanaType: KanaType,
    onChapterSelected: (Chapter) -> Unit,
    onBack: () -> Unit
) {
    val allProgress by viewModel.allProgress.collectAsState()
    val chapters = when (kanaType) {
        KanaType.HIRAGANA -> AlphabetData.hiraganaChapters
        KanaType.KATAKANA -> AlphabetData.katakanaChapters
        KanaType.VOCABULARY -> AlphabetData.vocabularyChapters
        KanaType.SENTENCE -> AlphabetData.sentenceChapters
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        when(kanaType) {
                            KanaType.HIRAGANA -> "Hiragana"
                            KanaType.KATAKANA -> "Katakana"
                            KanaType.VOCABULARY -> "Vocabulary"
                            KanaType.SENTENCE -> "Sentences"
                        }
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(chapters) { chapter ->
                val progress = allProgress.find { it.chapterId == chapter.id }
                ChapterCard(
                    chapter = chapter,
                    isCompleted = progress?.isCompleted ?: false,
                    accuracy = progress?.accuracy ?: 0f,
                    onClick = { onChapterSelected(chapter) }
                )
            }
        }
    }
}

@Composable
fun ChapterCard(
    chapter: Chapter,
    isCompleted: Boolean,
    accuracy: Float,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .heightIn(min = 60.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = chapter.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${chapter.characters.size} Items",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    if (isCompleted) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            LinearProgressIndicator(
                                progress = { accuracy },
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .height(6.dp),
                                color = MaterialTheme.colorScheme.primary,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                strokeCap = androidx.compose.ui.graphics.StrokeCap.Round
                            )
                        }
                    }
                }
            }
            
            if (isCompleted) {
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Completed",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
