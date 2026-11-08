package com.focustube

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Subscriptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Ink = Color(0xFF101A18)
private val Mint = Color(0xFF67D9B3)
private val Canvas = Color(0xFF101513)
private val Panel = Color(0xFF19211F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FocusTubeApp() }
    }
}

private enum class Destination(val label: String, val icon: ImageVector) {
    Home("Home", Icons.Filled.Home),
    Subscriptions("Channels", Icons.Filled.Subscriptions),
    Library("Library", Icons.Filled.Bookmarks),
    Search("Search", Icons.Filled.Search),
    Settings("Settings", Icons.Filled.Settings),
}

@Composable
private fun FocusTubeApp() {
    var selected by rememberSaveable { mutableStateOf(Destination.Home.name) }
    MaterialTheme {
        Scaffold(
            containerColor = Canvas,
            bottomBar = {
                NavigationBar(containerColor = Panel) {
                    Destination.entries.forEach { destination ->
                        val active = selected == destination.name
                        val tint by animateColorAsState(
                            if (active) Mint else Color(0xFF89938F),
                            label = "navigation tint",
                        )
                        NavigationBarItem(
                            selected = active,
                            onClick = { selected = destination.name },
                            icon = { Icon(destination.icon, contentDescription = destination.label, tint = tint) },
                            label = { Text(destination.label, color = tint) },
                        )
                    }
                }
            },
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).padding(horizontal = 24.dp, vertical = 20.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(color = Mint, shape = RoundedCornerShape(12.dp), modifier = Modifier.size(38.dp)) {
                        Icon(Icons.Filled.Home, contentDescription = null, tint = Ink, modifier = Modifier.padding(9.dp))
                    }
                    Text("  FocusTube", color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
                }
                Spacer(Modifier.height(32.dp))
                when (Destination.valueOf(selected)) {
                    Destination.Home -> EmptyDestination("Your space,\nwithout the noise.", "Approve channels or add a video to begin.")
                    Destination.Subscriptions -> EmptyDestination("Your channels", "Only channels you choose will appear here.")
                    Destination.Library -> EmptyDestination("Your library", "Videos you save will be kept here on this device.")
                    Destination.Search -> EmptyDestination("Find something specific", "Search becomes available as your personal library grows.")
                    Destination.Settings -> EmptyDestination("Made for intentional watching", "Appearance and playback preferences will live here.")
                }
            }
        }
    }
}

@Composable
private fun EmptyDestination(title: String, message: String) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
        Text(title, color = Color(0xFFF2F6F4), fontSize = 30.sp, lineHeight = 36.sp, fontWeight = FontWeight.SemiBold)
        Spacer(Modifier.height(12.dp))
        Text(message, color = Color(0xFF9AA7A2), fontSize = 15.sp, lineHeight = 22.sp)
        Spacer(Modifier.height(28.dp))
        Surface(color = Panel, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(20.dp)) {
                Text("Nothing here yet", color = Color(0xFFE2E9E5), fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(6.dp))
                Text("FocusTube never fills your feed with recommendations.", color = Color(0xFF9AA7A2), fontSize = 14.sp)
            }
        }
    }
}
