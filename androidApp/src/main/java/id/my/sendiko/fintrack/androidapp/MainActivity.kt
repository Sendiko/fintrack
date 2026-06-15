package id.my.sendiko.fintrack.androidapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import id.my.sendiko.fintrack.core.App
import id.my.sendiko.fintrack.transaction.form.presentation.FormTransactionEvent
import id.my.sendiko.fintrack.transaction.form.presentation.ReceiptViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainActivity : ComponentActivity(), KoinComponent {

    private val receiptViewModel: ReceiptViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        handleIncomingIntent(intent)

        setContent {
            App()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIncomingIntent(intent)
    }

    private fun handleIncomingIntent(intent: Intent?) {
        if (intent?.action == Intent.ACTION_SEND && intent.type?.startsWith("image/") == true) {
            val uri = intent.getParcelableExtra<android.net.Uri>(Intent.EXTRA_STREAM)
            uri?.let { targetUri ->
                contentResolver.openInputStream(targetUri)?.use { inputStream ->
                    val imageBytes = inputStream.readBytes()
                    receiptViewModel.onEvent(FormTransactionEvent.ProcessSharedImage(imageBytes))
                }
            }
        }
    }

}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}