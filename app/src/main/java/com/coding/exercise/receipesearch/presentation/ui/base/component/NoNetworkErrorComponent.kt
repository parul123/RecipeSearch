package com.coding.exercise.receipesearch.presentation.ui.base.component

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coding.exercise.receipesearch.common.Utils

@Composable
fun NoNetworkErrorComponent(context: Context) {
    var showDialog by remember { mutableStateOf(false) }

    // Check for network availability
    if (!Utils.isNetworkAvailable(context)) {
        showDialog = true
    }

    // Display the dialog if no network
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("No Network Connection") },
            text = { Text("It seems that there is no network connection. Please check your internet connection.") },
            confirmButton = {
                Button(
                    onClick = { showDialog = false },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("Retry")
                }
            }
        )
    }
}