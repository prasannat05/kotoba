package com.example.kotoba.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotoba.data.Chapter
import com.example.kotoba.data.KanaCharacter
import com.example.kotoba.data.KanaType
import com.example.kotoba.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonScreen(
    viewModel: MainViewModel,
    chapter: Chapter,
    onQuizStart: () -> Unit,
    onWritingPractice: () -> Unit,
    onBack: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentCharacter = chapter.characters[currentIndex]
    val characterProgress by viewModel.characterProgress.collectAsState()
    
    val charId = "${currentCharacter.type.name}_${currentCharacter.japanese}"
    val isLearned = characterProgress.find { it.charId == charId }?.isLearned ?: false

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(chapter.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleCharacterLearned(currentCharacter) }) {
                        Icon(
                            imageVector = if (isLearned) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                            contentDescription = "Mark as learned",
                            tint = if (isLearned) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / chapter.characters.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .padding(bottom = 32.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .pointerInput(currentIndex) {
                        detectHorizontalDragGestures { change, dragAmount ->
                            change.consume()
                            if (dragAmount > 50 && currentIndex > 0) {
                                currentIndex--
                            } else if (dragAmount < -50 && currentIndex < chapter.characters.size - 1) {
                                currentIndex++
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                AnimatedFlashcard(character = currentCharacter)
            }

            Spacer(modifier = Modifier.height(32.dp))

            // English Reference always visible below the card
            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (currentCharacter.type == KanaType.HIRAGANA || currentCharacter.type == KanaType.KATAKANA) 
                            "Romaji Reference" else "English Translation",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = currentCharacter.romaji,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = "Pronunciation: ${currentCharacter.phonetic}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { if (currentIndex > 0) currentIndex-- },
                    enabled = currentIndex > 0,
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text("Previous")
                }
                
                if (currentIndex < chapter.characters.size - 1) {
                    Button(
                        onClick = { currentIndex++ },
                        modifier = Modifier.weight(1f).padding(start = 8.dp)
                    ) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = onQuizStart,
                        modifier = Modifier.weight(1f).padding(start = 8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Start Quiz")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            OutlinedButton(
                onClick = onWritingPractice,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Create, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Practice Writing")
            }
        }
    }
}

@Composable
fun AnimatedFlashcard(character: KanaCharacter) {
    var rotated by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (rotated) 180f else 0f,
        label = "FlashcardRotation"
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f) // Adjusted to make room for the persistent reference card
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable { rotated = !rotated },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (rotation <= 90f) {
                // Front Side
                Text(
                    text = character.japanese,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = if (character.japanese.length > 5) 48.sp else 120.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                // Back Side
                Column(
                    modifier = Modifier.graphicsLayer { rotationY = 180f },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = character.romaji,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Pronunciation: ${character.phonetic}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
