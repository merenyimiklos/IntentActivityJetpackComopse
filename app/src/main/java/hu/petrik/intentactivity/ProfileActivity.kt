package hu.petrik.intentactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val kapottNev = intent.getStringExtra("nev") ?: ""

        val prefs = getSharedPreferences(
            "diakprofil_adatok", MODE_PRIVATE
        )

        val mentettNev = prefs.getString("nev", "") ?: ""
        val sotetMod = prefs.getBoolean("sotetMod", false)
        setContent {
            MaterialTheme(
                colorScheme = if (sotetMod)
                    darkColorScheme()
                else
                    lightColorScheme()
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                            .padding(24.dp),
                        verticalArrangement =
                            Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Profilom",
                            style = MaterialTheme.typography.headlineMedium
                        )

                        Text(
                            text = "Intent segítségével kapott név:"
                        )
                        Text(
                            text = kapottNev.ifEmpty { "Nem érkezett név" },
                            style = MaterialTheme.typography.titleLarge
                        )

                        HorizontalDivider()

                        Text(
                            text = "Sharedpreferences segítségével kapott név:"
                        )
                        Text(
                            text = mentettNev.ifEmpty { "Nincs mentve név" },
                            style = MaterialTheme.typography.titleLarge
                        )

                        Text(text = "Sötét mód állapota:")

                        Text(
                            text = if (sotetMod) "Aktív" else "Inaktív",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }
            }

        }
    }
}
