package rs.djokafioka.composerecview

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rs.djokafioka.composerecview.data.TvShowList
import rs.djokafioka.composerecview.model.TvShow
import rs.djokafioka.composerecview.ui.theme.ComposeRecViewTheme

class InfoActivity : ComponentActivity() {
    companion object {
        private const val TV_SHOW_ID = "TV_SHOW_ID"
        fun intent(context: Context, tvShowId: Int) =
            Intent(context, InfoActivity::class.java).apply {
                putExtra(TV_SHOW_ID, tvShowId)
            }
    }

    private val tvShow: TvShow by lazy {
        TvShowList.tvShows[(intent?.getIntExtra(TV_SHOW_ID, 1) ?: 1) - 1]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewMoreInfo(tvShow = tvShow)
        }
    }
}

@Composable
fun ViewMoreInfo(
    tvShow: TvShow
) {
    val scrollState = rememberScrollState()
    Card (
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = tvShow.imageId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = tvShow.name,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = tvShow.overview,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Original release: ${tvShow.year}",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "IMDB rating: ${tvShow.rating}",
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}