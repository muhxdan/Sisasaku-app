package com.salt.apps.feature.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.salt.apps.core.data.State
import com.salt.apps.feature.R
import io.github.jan.supabase.compose.auth.composable.rememberLoginWithGoogle
import io.github.jan.supabase.compose.auth.composeAuth

@Composable
fun LoginScreen(
    navigateToHome: () -> Unit,
    loginVm: LoginViewModel = hiltViewModel(),
) {
    val loginState by loginVm.loginState.collectAsState()
    val client = loginVm.getSupabaseClient()

    val action = client.composeAuth.rememberLoginWithGoogle(
        onResult = { result ->
            loginVm.signInWithGoogle(result)
        },
        fallback = {},
    )

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.login_animation))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(id = R.string.welcome_title_login),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            textAlign = TextAlign.Center,
        )
        Image(
            modifier = Modifier.height(30.dp),
            painter = painterResource(id = R.drawable.ic_app_blue),
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .padding(vertical = 30.dp)
                .height(280.dp)
        ) {
            LottieAnimation(
                composition = composition,
                iterations = Int.MAX_VALUE,
                contentScale = ContentScale.Crop,
                speed = 1.5f,
            )
        }
        Text(
            stringResource(id = R.string.welcome_description_login),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.weight(1f))
        SsButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            onClick = {
                action.startFlow()
            },
            contentPadding = PaddingValues(vertical = 13.dp),
        ) {

            if (loginState is State.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(10.dp)
                        .height(10.dp),
                    color = Color.White,
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    stringResource(id = R.string.sign_in),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Medium,
                    ),
                )
            }
        }
    }

    when (loginState) {
        is State.Loading -> {
            Log.i("LoginScreen", "Loading...")
        }

        is State.Success -> {
            navigateToHome()
            val successData = (loginState as State.Success).data
        }

        is State.Error -> {
            val errorMessage = (loginState as State.Error).message
            Log.i("LoginScreen", "errorMessage: $errorMessage")
        }

        else -> {}
    }

}

@Composable
fun SsButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    color: Color = MaterialTheme.colorScheme.primary,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}