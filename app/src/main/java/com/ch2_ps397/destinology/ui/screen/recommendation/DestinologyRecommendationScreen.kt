package com.ch2_ps397.destinology.ui.screen.recommendation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ch2_ps397.destinology.navigation.DestinologyScreens
import com.ch2_ps397.destinology.ui.components.button.Button
import com.ch2_ps397.destinology.ui.components.button.DestinologyFloatingButton
import com.ch2_ps397.destinology.ui.components.cards.ItineraryDayCard
import com.ch2_ps397.destinology.ui.components.cards.ItineraryPlaceCard
import com.ch2_ps397.destinology.ui.theme.DarkGray
import com.ch2_ps397.destinology.ui.theme.Gray
import com.ch2_ps397.destinology.ui.theme.VeryLightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DestinologyRecommendationScreen(navController: NavController) {
    var dayPlanState by remember {
        mutableIntStateOf(0)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Our Recommendations", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    Image(
                        contentDescription = "arrow back",
                        imageVector = Icons.Default.ArrowBack
                    )
                }
            )
        },
        floatingActionButton = {
                               DestinologyFloatingButton(
                                   content = {
                                       Text(text = "Gunakan", modifier = Modifier.padding(16.dp))
                                   }
                               ) {
                                   //TODO save this plan to ROOM
                                   navController.navigate(DestinologyScreens.DestinologyPlanScreen.name)
                               }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier.background(VeryLightGray)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            ) {
                ItineraryDayCard(isActive = true, "Day 1", "12 Des") {
                    dayPlanState = 0
                }
                ItineraryDayCard(isActive = false, "Day 2", "13 Des") {                    dayPlanState = 0
                    dayPlanState = 1
                }
                ItineraryDayCard(isActive = false, "Day 3", "14 Des") {
                    dayPlanState = 2
                }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Timeline", fontWeight = FontWeight.Bold, color = DarkGray)
                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    for (i in 1..4) {
                        item {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = "10.00 AM", color = Gray)
                                Spacer(modifier = Modifier.width(8.dp))
                                ItineraryPlaceCard(
                                    title = "Museum Ulen Sentalu",
                                    description = "Museum keren kalo mau liat patung Squidward bisa kesini.",
                                    address = "Jl. Boyong KM.25 Kaliurang, Hargobinangun, Yogyakarta"
                                )
                            }
                        }
                    }
                }


//                when (dayPlanState) {
//                    0 -> Text(text = "Plan Day ${dayPlanState + 1}")
//                    1 -> Text(text = "Plan Day ${dayPlanState + 1}")
//                    2 -> Text(text = "Plan Day ${dayPlanState + 1}")
//                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewRecommendationScreen() {
    DestinologyRecommendationScreen(navController = rememberNavController())
}