package com.coding.exercise.receipesearch.presentation.ui.base.component

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.coding.exercise.receipesearch.R
import com.coding.exercise.receipesearch.common.Utils

@Composable
fun NoNetworkErrorComponent(context: Context) {
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if (!Utils.isNetworkAvailable(context)) {
            showDialog = true
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(LocalContext.current.getString(R.string.no_network_connection)) },
            text = { Text(LocalContext.current.getString(R.string.no_network_connection_detail)) },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                              },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(LocalContext.current.getString(R.string.dialog_ok_btn))
                }
            }
        )
    }
}