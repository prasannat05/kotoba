package com.example.kotoba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotoba.data.AlphabetData
import com.example.kotoba.data.KanaType
import com.example.kotoba.ui.MainViewModel
import com.example.kotoba.ui.screens.*
import com.example.kotoba.ui.theme.KotobaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotobaTheme {
                KotobaApp()
            }
        }
    }
}

@Composable
fun KotobaApp() {
    val navController = rememberNavController()
    val viewModel: MainViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("dashboard") {
                DashboardScreen(
                    viewModel = viewModel,
                    onStartLearning = { navController.navigate("alphabet_choice") },
                    onProfileClick = { navController.navigate("profile") },
                    onContinueLearning = { chapterId ->
                        val chapter = AlphabetData.allChapters.find { it.id == chapterId }
                        if (chapter != null) {
                            viewModel.selectChapter(chapter)
                            navController.navigate("lesson/${chapter.id}")
                        }
                    }
                )
            }
            composable("alphabet_choice") {
                AlphabetChoiceScreen(
                    onTypeSelected = { type ->
                        navController.navigate("chapters/${type.name}")
                    },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(
                "chapters/{kanaType}",
                arguments = listOf(navArgument("kanaType") { type = NavType.StringType })
            ) { backStackEntry ->
                val typeName = backStackEntry.arguments?.getString("kanaType")
                val type = KanaType.valueOf(typeName ?: "HIRAGANA")
                ChapterListScreen(
                    viewModel = viewModel,
                    kanaType = type,
                    onChapterSelected = { chapter ->
                        viewModel.selectChapter(chapter)
                        navController.navigate("lesson/${chapter.id}")
                    },
                    onBack = { navController.popBackStack() }
                )
            }
            composable(
                "lesson/{chapterId}",
                arguments = listOf(navArgument("chapterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 0
                val chapter = AlphabetData.allChapters.find { it.id == chapterId }
                if (chapter != null) {
                    LessonScreen(
                        viewModel = viewModel,
                        chapter = chapter,
                        onQuizStart = { navController.navigate("quiz/${chapter.id}") },
                        onWritingPractice = { navController.navigate("writing/${chapter.id}") },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
            composable(
                "quiz/{chapterId}",
                arguments = listOf(navArgument("chapterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 0
                val chapter = AlphabetData.allChapters.find { it.id == chapterId }
                if (chapter != null) {
                    QuizScreen(
                        chapter = chapter,
                        onQuizComplete = { score, total ->
                            viewModel.completeChapter(chapter.id, score, total)
                            navController.popBackStack("dashboard", inclusive = false)
                        },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
            composable(
                "writing/{chapterId}",
                arguments = listOf(navArgument("chapterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 0
                val chapter = AlphabetData.allChapters.find { it.id == chapterId }
                if (chapter != null) {
                    WritingPracticeScreen(
                        chapter = chapter,
                        onBack = { navController.popBackStack() }
                    )
                }
            }
            composable("profile") {
                ProfileScreen(
                    viewModel = viewModel,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
