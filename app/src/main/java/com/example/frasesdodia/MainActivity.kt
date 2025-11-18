package com.example.frasesdodia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.frasesdodia.ui.theme.FrasesDoDiaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrasesDoDiaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var isLiked by remember { mutableStateOf(false) }

                    DailyQuoteScreen(
                        quoteText = "Android",
                        isLiked = isLiked,
                        onLikeClick = { isLiked = !isLiked },
                        onSaveClick = { /* TODO */ },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DailyQuoteScreen(
    quoteText: String,
    isLiked: Boolean,
    onLikeClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (quote, likeBtn, saveBtn) = createRefs()

        val buttonModifier = Modifier.size(60.dp)

        Text(
            text = quoteText,
            fontSize = 36.sp,
            fontFamily = FontFamily.Cursive,
            lineHeight = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.constrainAs(quote) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
        )

        IconButton(
            onClick = onSaveClick,
            modifier = buttonModifier.constrainAs(saveBtn) {
                bottom.linkTo(parent.bottom, margin = 96.dp)
                start.linkTo(parent.start)
                end.linkTo(likeBtn.start)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save",
                modifier = Modifier.size(40.dp)
            )
        }

        IconButton(
            onClick = onLikeClick,
            modifier = buttonModifier.constrainAs(likeBtn) {
                top.linkTo(saveBtn.top)
                start.linkTo(saveBtn.end)
                end.linkTo(parent.end)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Like",
                tint = if (isLiked) Color.Red else Color.Gray,
                modifier = Modifier.size(40.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FrasesDoDiaTheme {
        DailyQuoteScreen(
            quoteText = "Android",
            isLiked = false,
            onLikeClick = { /* TODO */ },
            onSaveClick = { /* TODO */ },
        )
    }
}