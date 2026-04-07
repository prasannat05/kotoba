package com.example.kotoba.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextOverflow
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
                title = { Text(chapter.name, maxLines = 1, overflow = TextOverflow.Ellipsis) },
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / chapter.characters.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .padding(bottom = 24.dp)
            )

            Box(
                modifier = Modifier
                    .height(300.dp) // Fixed height for the card container
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

            Spacer(modifier = Modifier.height(24.dp))

            // Reference Card
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
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = currentCharacter.romaji,
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontSize = if (currentCharacter.romaji.length > 20) 18.sp else 24.sp,
                            fontWeight = FontWeight.ExtraBold,
                            lineHeight = 28.sp
                        ),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pronunciation: ${currentCharacter.phonetic}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                        textAlign = TextAlign.Center
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
                    modifier = Modifier.weight(1f).padding(end = 8.dp).height(48.dp)
                ) {
                    Text("Previous")
                }
                
                if (currentIndex < chapter.characters.size - 1) {
                    Button(
                        onClick = { currentIndex++ },
                        modifier = Modifier.weight(1f).padding(start = 8.dp).height(48.dp)
                    ) {
                        Text("Next")
                    }
                } else {
                    Button(
                        onClick = onQuizStart,
                        modifier = Modifier.weight(1f).padding(start = 8.dp).height(48.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Quiz")
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            OutlinedButton(
                onClick = onWritingPractice,
                modifier = Modifier.fillMaxWidth().height(48.dp)
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
            .height(280.dp)
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
            }
            .clickable { rotated = !rotated },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
            if (rotation <= 90f) {
                // Front Side - Fixed Font Sizes and centered
                Text(
                    text = character.japanese,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = when {
                            character.japanese.length >= 15 -> 20.sp
                            character.japanese.length >= 10 -> 24.sp
                            character.japanese.length >= 7 -> 32.sp
                            character.japanese.length >= 5 -> 42.sp
                            character.japanese.length >= 3 -> 56.sp
                            character.japanese.length == 2 -> 80.sp
                            else -> 100.sp
                        },
                        lineHeight = when {
                            character.japanese.length >= 7 -> 32.sp
                            character.japanese.length >= 4 -> 48.sp
                            else -> 80.sp
                        }
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    softWrap = true,
                    overflow = TextOverflow.Visible
                )
            } else {
                // Back Side
                Column(
                    modifier = Modifier.graphicsLayer { rotationY = 180f },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = character.romaji,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontSize = if (character.romaji.length > 15) 22.sp else 30.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = character.phonetic,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
