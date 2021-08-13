package com.hsicen.migration.plantdetail

import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.hsicen.migration.R
import com.hsicen.migration.data.Plant
import com.hsicen.migration.viewmodels.PlantDetailViewModel

@Composable
fun PlantDescription(desc: String) {
    val htmlDesc = remember(desc) {
        HtmlCompat.fromHtml(desc, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    AndroidView(factory = { context ->
        TextView(context).apply {
            movementMethod = LinkMovementMethod.getInstance()
        }
    }, update = {
        it.text = htmlDesc
    })
}


@Composable
fun PlantDetailDescription(viewModel: PlantDetailViewModel) {
    val plant by viewModel.plant.observeAsState()

    plant?.let {
        PlantDetailContent(plant = it)
    }

    Surface {
        Text("Hello Compose")
    }
}

@Composable
fun PlantDetailContent(plant: Plant) {
    Surface {
        Column(Modifier.padding(dimensionResource(id = R.dimen.margin_normal))) {
            PlantName(name = plant.name)
            PlantWatering(waterInterval = plant.wateringInterval)
            PlantDescription(desc = plant.description)
        }
    }
}

@Composable
fun PlantName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.margin_small))
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun PlantWatering(waterInterval: Int) {
    Column(Modifier.fillMaxWidth()) {
        val centerModifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.margin_small))
            .align(CenterHorizontally)
        val normalPadding = dimensionResource(id = R.dimen.margin_normal)

        Text(
            text = stringResource(id = R.string.watering_needs_prefix),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold,
            modifier = centerModifier.padding(top = normalPadding)
        )

        val waterText = LocalContext.current.resources.getQuantityString(
            R.plurals.watering_needs_suffix, waterInterval, waterInterval
        )

        Text(text = waterText, modifier = centerModifier.padding(bottom = normalPadding))
    }
}

@Preview
@Composable
fun PlantNamePreview() {
    MaterialTheme {
        PlantName(name = "Apple")
    }
}

@Preview
@Composable
fun WaterPreview() {
    MaterialTheme {
        PlantWatering(waterInterval = 7)
    }
}

@Preview(showSystemUi = true)
@Composable
fun PlantDescPreview() {
    MaterialTheme {
        PlantDescription(desc = "HTML<br><br>description")
    }
}