package com.example.myapplicationkotlin
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplicationkotlin.ui.theme.MyApplicationKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationKotlinTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names : List<String> = List(25) {"$it"}){
    Surface(color = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(vertical = 8.dp)){
            LazyColumn{
                items(names){cont->
                    Greeting(cont)
                }
            }
        }
    }
}

@Composable
private fun Greeting(cont: String) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState (
        targetValue = if (expanded.value) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column (
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = extraPadding)) {
                Text(text = "Noticia no.$cont")
            }
            OutlinedButton(onClick = {expanded.value =!expanded.value}) {
                Text(if(expanded.value) "Leer menos" else "Leer mas")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: ()-> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("¡Bienvenido (a) a mi app!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("INGRESAR")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyApplicationKotlinTheme {
        OnboardingScreen(onContinueClicked={})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    MyApplicationKotlinTheme{
        MyApp()
    }
}