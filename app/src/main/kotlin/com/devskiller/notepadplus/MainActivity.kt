package com.devskiller.notepadplus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.devskiller.notepadplus.ui.notes.edit.EditNoteScreen
import com.devskiller.notepadplus.ui.notes.list.NoteListScreen
import com.devskiller.notepadplus.ui.theme.NotepadTheme
import com.devskiller.notepadplus.ui.viewmodel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotepadTheme { NotepadApp() }
        }
    }
}

@Composable
fun NotepadApp() {
    val navController = rememberNavController()
    val viewModel: NoteViewModel = koinViewModel()

    NavHost(
        navController = navController,
        startDestination = "noteList"
    ) {
        composable(
            "noteList",
            enterTransition = {
                when (initialState.destination.route) {
                    "editNote" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "editNote" ->
                        fadeOut(
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    "editNote" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    "editNote" ->
                        fadeOut(
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            }
        ) {
            NoteListScreen(
                onAddNoteClick = { navController.navigate("editNote/new") },
                onNoteClick = { note -> navController.navigate("editNote/${note.id}") },
                viewModel = viewModel
            )
        }
        composable(
            "editNote/{noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
                nullable = true
            }),
            enterTransition = {
                when (initialState.destination.route) {
                    "noteList" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.Start,
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    "noteList" ->
                        fadeOut(
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    "noteList" ->
                        slideIntoContainer(
                            AnimatedContentTransitionScope.SlideDirection.End,
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    "noteList" ->
                        fadeOut(
                            animationSpec = tween(350)
                        )

                    else -> null
                }
            }
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")

            EditNoteScreen(
                noteId = noteId ?: "new",
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
    }
}

