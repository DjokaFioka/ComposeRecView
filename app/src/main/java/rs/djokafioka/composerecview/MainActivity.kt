package rs.djokafioka.composerecview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.djokafioka.composerecview.compose.TvShowListItem
import rs.djokafioka.composerecview.data.TvShowList
import rs.djokafioka.composerecview.model.TvShow
import rs.djokafioka.composerecview.ui.theme.ComposeRecViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeRecViewTheme {
                //ScrollableColumnDemo()
                //LazyColumnDemo()
//                LazyColumnDemoWithClick { selectedItem ->
//                    Toast.makeText(applicationContext, selectedItem, Toast.LENGTH_SHORT).show()
//                }

                DisplayTvShows {
                    startActivity(InfoActivity.intent(this, it.id))
                }
            }
        }
    }
}

@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        for (i in 1..100) {
            Text(
                text = "User Name $i",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
            )
            Divider(
                color = Color.Black,
                thickness = 4.dp
            )
        }
    }
}

@Composable
fun LazyColumnDemo() {
    LazyColumn {
        items(count = 100) {
            Text(
                text = "User Name $it",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
            )
            Divider(
                color = Color.Black,
                thickness = 4.dp
            )
        }
    }
}

@Composable
fun LazyColumnDemoWithClick(
    selectedItem: (String) -> (Unit)
) {
    LazyColumn {
        items(count = 100) {
            Text(
                text = "User Name $it",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedItem("$it is selected") }
            )
            Divider(
                color = Color.Black,
                thickness = 4.dp
            )
        }
    }
}

@Composable
fun DisplayTvShows(
    selectedItem: (TvShow) -> (Unit)
) {
    val tvShows = remember {
        TvShowList.tvShows
    }

    LazyColumn(
        contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 4.dp
        )
    ) {
        items(
            count = tvShows.count()
        ) {
            TvShowListItem(
                tvShow = tvShows[it],
                selectedItem
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DisplayTvShowsPreview() {
    ComposeRecViewTheme {
        DisplayTvShows({})
    }
}