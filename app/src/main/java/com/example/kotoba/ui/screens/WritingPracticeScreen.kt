package com.example.kotoba.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotoba.data.Chapter
import com.example.kotoba.data.KanaType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WritingPracticeScreen(
    chapter: Chapter,
    onBack: () -> Unit
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentCharacter = chapter.characters[currentIndex]
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf<Path?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Writing Practice") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { paths.clear() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Clear")
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
            Text(
                text = "Draw: ${currentCharacter.romaji}",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.medium),
                contentAlignment = Alignment.Center
            ) {
                // Background reference character - scaling font size more precisely
                Text(
                    text = currentCharacter.japanese,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = when {
                            currentCharacter.japanese.length >= 12 -> 24.sp
                            currentCharacter.japanese.length >= 8 -> 32.sp
                            currentCharacter.japanese.length >= 5 -> 48.sp
                            currentCharacter.japanese.length == 4 -> 64.sp
                            currentCharacter.japanese.length == 3 -> 80.sp
                            currentCharacter.japanese.length == 2 -> 110.sp
                            else -> 160.sp
                        },
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f)
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )

                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(currentIndex) { // Reset on character change
                            detectDragGestures(
                                onDragStart = { offset ->
                                    currentPath = Path().apply { moveTo(offset.x, offset.y) }
                                },
                                onDrag = { change, _ ->
                                    change.consume()
                                    currentPath?.lineTo(change.position.x, change.position.y)
                                    // Trigger recomposition
                                    val temp = currentPath
                                    currentPath = null
                                    currentPath = temp
                                },
                                onDragEnd = {
                                    currentPath?.let { paths.add(it) }
                                    currentPath = null
                                }
                            )
                        }
                ) {
                    paths.forEach { path ->
                        drawPath(
                            path = path,
                            color = Color.Black,
                            style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                    currentPath?.let { path ->
                        drawPath(
                            path = path,
                            color = Color.Black,
                            style = Stroke(width = 8.dp.toPx(), cap = StrokeCap.Round)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (currentIndex > 0) {
                            currentIndex--
                            paths.clear()
                        }
                    },
                    enabled = currentIndex > 0
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Previous")
                }

                Text(
                    text = "${currentIndex + 1} / ${chapter.characters.size}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = {
                        if (currentIndex < chapter.characters.size - 1) {
                            currentIndex++
                            paths.clear()
                        }
                    },
                    enabled = currentIndex < chapter.characters.size - 1
                ) {
                    Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = "Next")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                "Follow the character outline as closely as possible.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
