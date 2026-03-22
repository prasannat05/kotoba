package com.example.kotoba.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kotoba.data.AlphabetData
import com.example.kotoba.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    viewModel: MainViewModel,
    onStartLearning: () -> Unit,
    onProfileClick: () -> Unit,
    onContinueLearning: (Int) -> Unit
) {
    val userProfile by viewModel.userProfile.collectAsState()
    val allProgress by viewModel.allProgress.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Kotoba – 言葉") },
                actions = {
                    IconButton(onClick = onProfileClick) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hero section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Hello, ${userProfile?.username ?: "Learner"}!",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Ready to continue your Japanese journey?",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onStartLearning,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("START LEARNING", style = MaterialTheme.typography.titleMedium)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                "Recent Progress",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Start)
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            if (allProgress.isEmpty()) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text("No recent activity. Start learning today!", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    val recentProgress = allProgress.sortedByDescending { it.lastStudied }.take(5)
                    items(recentProgress) { progress ->
                        val chapter = AlphabetData.allChapters.find { it.id == progress.chapterId }
                        if (chapter != null) {
                            RecentChapterCard(
                                chapterName = chapter.name,
                                type = chapter.type.name,
                                score = progress.bestScore,
                                accuracy = progress.accuracy,
                                onClick = { onContinueLearning(chapter.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun RecentChapterCard(
    chapterName: String,
    type: String,
    score: Int,
    accuracy: Float,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(chapterName, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(type, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("Best Score: $score", style = MaterialTheme.typography.bodyMedium)
                LinearProgressIndicator(
                    progress = { accuracy },
                    modifier = Modifier.width(100.dp).height(8.dp).padding(top = 4.dp),
                )
            }
        }
    }
}
