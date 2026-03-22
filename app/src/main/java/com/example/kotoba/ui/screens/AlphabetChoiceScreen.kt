package com.example.kotoba.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotoba.data.KanaType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlphabetChoiceScreen(
    onTypeSelected: (KanaType) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose Category") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AlphabetCard(
                title = "Hiragana",
                subtitle = "あ",
                description = "Basic native characters",
                onClick = { onTypeSelected(KanaType.HIRAGANA) }
            )

            AlphabetCard(
                title = "Katakana",
                subtitle = "ア",
                description = "Foreign/loan characters",
                onClick = { onTypeSelected(KanaType.KATAKANA) }
            )

            AlphabetCard(
                title = "Vocabulary",
                subtitle = "言",
                description = "Common words & phrases",
                onClick = { onTypeSelected(KanaType.VOCABULARY) }
            )

            AlphabetCard(
                title = "Sentences",
                subtitle = "句",
                description = "Daily conversation guides",
                onClick = { onTypeSelected(KanaType.SENTENCE) }
            )
        }
    }
}

@Composable
fun AlphabetCard(
    title: String,
    subtitle: String,
    description: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 40.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                )
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
