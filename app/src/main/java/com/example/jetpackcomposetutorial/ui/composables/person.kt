package com.example.jetpackcomposetutorial.ui.composables

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.jetpackcomposetutorial.R
import com.example.jetpackcomposetutorial.TextBox
import com.example.jetpackcomposetutorial.models.SampleMultiUserDetailsProvider
import com.example.jetpackcomposetutorial.models.SampleUserDetailsProvider
import com.example.jetpackcomposetutorial.models.UserDetails
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun UserListComponents(@PreviewParameter(SampleMultiUserDetailsProvider::class) users: List<UserDetails>){
    LazyColumn(modifier = Modifier.fillMaxWidth()){
        items(users){
            UserCard(user = it)
        }
    }
}

@Preview
@Composable
fun UserCard(@PreviewParameter(SampleUserDetailsProvider::class) user: UserDetails){
    val context = LocalContext.current

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(5.dp, Color.Magenta)
            .clickable {
                Toast
                    .makeText(
                        context,
                        "${user.name} clicked",
                        Toast.LENGTH_LONG
                    ).show()

            }
    ) {
        val (image, name, address, dobAndMobileNumber) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Image of ${user.name}",
            modifier = Modifier
                .clip(CircleShape)
                .size(36.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 8.dp)
                    bottom.linkTo(parent.bottom, 16.dp)
                }
        )

        Text(
            text = user.name,
            modifier = Modifier.constrainAs(name){
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(image.end, margin = 16.dp)
            }
        )

        Row(modifier = Modifier.constrainAs(dobAndMobileNumber){
            top.linkTo(name.bottom, 16.dp)
            start.linkTo(image.end, 16.dp)
        }){
            Text(text = user.dob)
            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
            Text(text = user.mobileNumber)
        }

        Text(
            text = user.address,
            modifier = Modifier.constrainAs(address){
                top.linkTo(dobAndMobileNumber.bottom, 16.dp)
                bottom.linkTo(parent.bottom, 16.dp)
                start.linkTo(image.end, 16.dp)
            }
        )
    }
}