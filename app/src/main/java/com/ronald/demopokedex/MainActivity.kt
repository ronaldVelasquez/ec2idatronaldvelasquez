package com.ronald.demopokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ronald.demopokedex.ui.theme.DemoPokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoPokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "pokemon_list_screen"
                    ) {
                        composable("pokemon_list_screen/{dominantColor}/{pokemonName}") {

                        }
                        composable(
                            "pokemon_detail_screen",
                            arguments = listOf(
                                navArgument("dominatColor") {
                                    type = NavType.IntType
                                },
                                navArgument("pokemonName") {
                                    type = NavType.StringType
                                }
                            )
                        ) {
                            val dominantColor = remember {
                                val color = it.arguments?.getInt("dominantColor")
                                color?.let { Color(it) } ?: Color.White
                            }
                            val pokemonName = remember {
                                it.arguments?.getString("pokemonName")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoPokedexTheme {
        Greeting("Android")
    }
}