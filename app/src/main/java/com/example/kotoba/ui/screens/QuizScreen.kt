package com.example.kotoba.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotoba.data.Chapter
import com.example.kotoba.data.KanaCharacter
import com.example.kotoba.data.KanaType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    chapter: Chapter,
    onQuizComplete: (Int, Int) -> Unit,
    onBack: () -> Unit
) {
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var score by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var isCorrect by remember { mutableStateOf<Boolean?>(null) }

    val currentCharacter = chapter.characters[currentQuestionIndex]
    
    // Generate options: 1 correct + 3 wrong
    val options = remember(currentQuestionIndex) {
        val correct = currentCharacter.romaji
        val others = chapter.characters
            .filter { it.romaji != correct }
            .map { it.romaji }
            .shuffled()
            .take(3)
        (others + correct).shuffled()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz: ${chapter.name}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) { padding ->
        if (showResult) {
            QuizResult(
                score = score,
                total = chapter.characters.size,
                onComplete = { onQuizComplete(score, chapter.characters.size) }
            )
        } else {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = { (currentQuestionIndex + 1).toFloat() / chapter.characters.size },
                    modifier = Modifier.fillMaxWidth().height(8.dp).padding(bottom = 32.dp)
                )

                Text(
                    text = if (chapter.type == KanaType.HIRAGANA || chapter.type == KanaType.KATAKANA)
                        "What is the Romaji for this character?"
                    else "What is the English meaning of this?",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = currentCharacter.japanese,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = if (currentCharacter.japanese.length > 3) 48.sp else 100.sp
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(48.dp))

                options.forEach { option ->
                    val color = when {
                        selectedAnswer == option && option == currentCharacter.romaji -> Color(0xFF4CAF50)
                        selectedAnswer == option && option != currentCharacter.romaji -> MaterialTheme.colorScheme.error
                        selectedAnswer != null && option == currentCharacter.romaji -> Color(0xFF4CAF50)
                        else -> MaterialTheme.colorScheme.surfaceVariant
                    }
                    
                    Button(
                        onClick = {
                            if (selectedAnswer == null) {
                                selectedAnswer = option
                                if (option == currentCharacter.romaji) {
                                    score++
                                    isCorrect = true
                                } else {
                                    isCorrect = false
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = color,
                            contentColor = if (selectedAnswer != null) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    ) {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                if (selectedAnswer != null) {
                    Button(
                        onClick = {
                            if (currentQuestionIndex < chapter.characters.size - 1) {
                                currentQuestionIndex++
                                selectedAnswer = null
                                isCorrect = null
                            } else {
                                showResult = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(56.dp)
                    ) {
                        Text(if (currentQuestionIndex < chapter.characters.size - 1) "NEXT" else "FINISH")
                    }
                }
            }
        }
    }
}

@Composable
fun QuizResult(score: Int, total: Int, onComplete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Quiz Completed!", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Your Score: $score / $total",
            style = MaterialTheme.typography.displayMedium,
            color = MaterialTheme.colorScheme.primary
        )
        
        val percentage = (score.toFloat() / total) * 100
        Text(
            text = when {
                percentage >= 90f -> "Amazing! Perfect!"
                percentage >= 70f -> "Great job!"
                percentage >= 50f -> "Good start, keep practicing!"
                else -> "Keep learning, you'll get there!"
            },
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onComplete,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("Back to Dashboard")
        }
    }
}
