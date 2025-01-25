package com.inf2007.lab01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.util.Log
import com.inf2007.lab01.ui.theme.Lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    Lab01Theme {
        var username by remember { mutableStateOf("") }
        var showGreeting by remember { mutableStateOf(false) }

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // User Input
                UserInput(
                    name = username,
                    onNameChange = {
                        username = it
                        showGreeting = false  // Reset greeting when input changes
                    }
                )

                // Submit Button
                Button(
                    onClick = {
                        if (username.isNotBlank()) {
                            showGreeting = true
                            Log.d("DEBUG", "Button clicked - showGreeting: $showGreeting, username: $username") // Debugging log
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("submitButton")
                ) {
                    Text("Submit")
                }

                // Greeting Message
                if (showGreeting) {
                    Greeting(
                        name = username,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun UserInput(name: String, onNameChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        value = name,
        onValueChange = { onNameChange(it) },
        label = { Text("Enter your Name") },
        modifier = modifier
            .fillMaxWidth()
            .testTag("nameInput")
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Log.d("DEBUG", "Greeting Composable - name: $name")  // Debugging log
    Text(
        text = "Hello $name!, Welcome to INF2007!",
        modifier = modifier
            .fillMaxWidth()
            .testTag("greetingMsg")
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}
