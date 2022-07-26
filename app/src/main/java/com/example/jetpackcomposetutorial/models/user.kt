package com.example.jetpackcomposetutorial.models

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

data class UserDetails(
    val name: String,
    val address: String,
    val dob: String,
    val mobileNumber: String
)

class SampleMultiUserDetailsProvider: PreviewParameterProvider<List<UserDetails>>{
    override val values: Sequence<List<UserDetails>>
        get() = sequenceOf(listOf(
            UserDetails(
            "Dummy name",
            "Dummy Address",
            "22-07-2000",
            "724-xxxx-467"
            ),
            UserDetails(
                "Dummy name",
                "Dummy Address",
                "22-07-2000",
                "724-xxxx-467"
            )
        ))
}

class SampleUserDetailsProvider: PreviewParameterProvider<UserDetails>{
    override val values: Sequence<UserDetails>
        get() = sequenceOf(
            UserDetails(
                "Dummy name",
                "Dummy Address",
                "22-07-2000",
                "724-xxxx-467"
            )
        )
}