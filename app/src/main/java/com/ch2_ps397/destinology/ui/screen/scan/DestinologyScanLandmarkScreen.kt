package com.ch2_ps397.destinology.ui.screen.scan

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ch2_ps397.destinology.ui.components.imagery.ImageBackground
import com.ch2_ps397.destinology.ui.theme.Black
import com.ch2_ps397.destinology.ui.theme.Gray
import com.ch2_ps397.destinology.ui.theme.White

@Composable
fun DestinologyScanLandmarkScreen(
    navController: NavController,
    nama: String?,
    desc: String?,
    fact: String?
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        ImageBackground("https://cdn1.katadata.co.id/media/images/thumb/2021/06/03/2021_06_03-14_48_13_40aeeb85ada2de344eac382eee02f576_620x413_thumb.jpg")
        Box(modifier = Modifier.padding(16.dp)) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = White
                    ),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = nama!!,
                            fontWeight = FontWeight.Bold,
                            color = Black
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column {
                            Text(text = "Deskripsi", color = Gray, fontSize = 12.sp)
                            Text(text = desc!!, color = Black)
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Column {
                            Text(text = "Fun fact:", color = Gray, fontSize = 12.sp)
                            Text(text = fact!!, color = Black)
                        }
                    }
                }
            }
        }
    }
}
