package com.winter.chatsystem.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.winter.chatsystem.logic.getChatMessages

@SuppressLint("SuspiciousIndentation")
@Composable
fun ChatScreen(chatId: String) {
    val messages by getChatMessages(chatId).collectAsState(emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 60.dp, bottom = 156.dp)
    ) {

        items(messages) { message ->

            if (message.senderId != FirebaseAuth.getInstance().currentUser!!.uid) {
                println(message.senderId)
                println(FirebaseAuth.getInstance().currentUser!!.uid)
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(6.dp)
                            .width(270.dp)
                            .wrapContentSize(Alignment.CenterStart),
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = RoundedCornerShape(
                            topEnd = 10.dp,
                            bottomEnd = 50.dp,
                            bottomStart = 15.dp,
                            topStart = 15.dp
                        ),
                        shadowElevation = 0.dp
                    ) {
                        Text(
                            text = message.oneMessage,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                //  .height(35.dp)
                                .wrapContentSize(),
                            textAlign = TextAlign.Start
                        )
                    }
                }

            } else {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Surface(
                        modifier = Modifier
                            .padding(6.dp)
                            .width(270.dp)
                            .wrapContentSize(Alignment.CenterEnd),

                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 50.dp,
                            topStart = 10.dp
                        ),
                        shadowElevation = 0.dp
                    ) {
                        Text(
                            text = message.oneMessage,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(7.dp)
                                //   .height(35.dp)
                                .wrapContentSize(),
                            textAlign = TextAlign.Start
                        )
                    }
                }

            }
        }
    }

}