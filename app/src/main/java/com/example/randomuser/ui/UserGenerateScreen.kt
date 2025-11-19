package com.example.randomuser.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.randomuser.ui.components.DropdownSelector
import com.example.randomuser.ui.theme.DarkBlue


enum class Gender() {
    MALE,
    FEMALE
}

enum class Nationality(val code: String) {
    AUSTRALIA("AU"),
    BRAZIL("BR"),
    CANADA("CA"),
    SWITZERLAND("CH"),
    GERMANY("DE"),
    DENMARK("DK"),
    SPAIN("ES"),
    FINLAND("FI"),
    FRANCE("FR"),
    IRELAND("IE"),
    INDIA("IN"),
    IRAN("IR"),
    NORWAY("NO"),
    SERBIA("RS"),
    TURKEY("TR"),
}

@Composable
fun UserGenerateScreen(
    modifier: Modifier = Modifier,
    goback: () -> Unit,
    viewModel: UserViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    var selectedGender by remember { mutableStateOf<Gender?>(null) }
    var selectedNationality by remember { mutableStateOf<Nationality?>(null) }
    val isButtonEnabled = selectedGender != null && selectedNationality != null

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            when (uiState) {
                is UiState.Loading -> {
                    Text(
                        text = "Select Gender:",
                        fontSize = 24.sp,
                        color = DarkBlue,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    DropdownSelector(
                        items = Gender.entries,
                        selectedItem = selectedGender,
                        itemToString = { it.name },
                        onItemSelected = { selectedGender = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Select Nationality:",
                        fontSize = 24.sp,
                        color = DarkBlue,
                        modifier = Modifier.padding(top = 50.dp, bottom = 8.dp)
                    )
                    DropdownSelector(
                        items = Nationality.entries,
                        selectedItem = selectedNationality,
                        itemToString = { it.name },
                        onItemSelected = { selectedNationality = it },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                is UiState.Error -> {
                    Text(
                        text = (uiState as UiState.Error).message,
                        color = Color.Red
                    )
                }

                else -> {}
            }

        }

        Button(
            onClick = {
                viewModel.getRandomUser(
                    selectedGender!!.name.lowercase(),
                    selectedNationality!!.code.lowercase()
                )
            },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Generate")
        }
        LaunchedEffect(uiState) {
            when (uiState) {
                is UiState.Success -> goback()
                else -> {}
            }
        }
    }
}