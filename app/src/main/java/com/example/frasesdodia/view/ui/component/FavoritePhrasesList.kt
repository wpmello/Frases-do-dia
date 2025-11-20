package com.example.frasesdodia.view.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.frasesdodia.model.domain.FavoritePhrase

@Composable
fun FavoritePhrasesList(
    phrases: List<FavoritePhrase>,
    onPhraseClick: (FavoritePhrase) -> Unit,
    modifier: Modifier = Modifier
) {
    if (phrases.isEmpty()) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Você ainda não favoritou nenhuma frase.\nQue tal salvar uma que te inspire?",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    } else {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(phrases) { phrase ->

                FavoritePhraseItem(
                    phrase = phrase,
                    onClick = { onPhraseClick(phrase) }
                )
            }
        }
    }
}

@Composable
fun FavoritePhraseItem(
    phrase: FavoritePhrase,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor = remember {
        randomSoftColor()
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 90.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text = phrase.text.take(80) + if (phrase.text.length > 80) "..." else "",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF1A1A1A)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = phrase.author,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                color = Color(0xFF444444)
            )
        }
    }
}

fun randomSoftColor(): Color {
    val colors = listOf(
        Color(0xFFFFF3C7),
        Color(0xFFE3F2FD),
        Color(0xFFE8F5E9),
        Color(0xFFFFEBEE),
        Color(0xFFF3E5F5),
        Color(0xFFFFF8E1),
        Color(0xFFE0F7FA),
        Color(0xFFEDE7F6)
    )
    return colors.random()
}