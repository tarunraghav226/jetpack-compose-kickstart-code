package com.example.jetpackcomposetutorial

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposetutorial.ui.theme.JetpackComposeTutorialTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTutorialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
//                    ScrollableComponent()
                    ShowList()
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowList(){
    val valu = listOf(1,2,3,4,5)
    LazyColumn{
        items(valu){
            Text("Hello $it")
        }
    }
}

@Preview
@Composable
fun ScrollableComponent() {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var rollNumber by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phone by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var state by remember { mutableStateOf(TextFieldValue("")) }
    var gender by remember { mutableStateOf(TextFieldValue("Male")) }
    var classOfStudent by remember { mutableStateOf(TextFieldValue("")) }

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier,
        scaffoldState = scaffoldState,
    ){
        Column(
            modifier = Modifier
                .verticalScroll(state = scrollState)
                .padding(top = 5.dp)
                .border(1.dp, Color.Gray)
                .padding(top = 3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            TextBox(label ="FORM" )
            CustomCard(
                label = getSuperScriptText("Name", "*"),
                value = name, onValueChange = { name = it }
            )
            CustomCard(
                label = getSuperScriptText("Roll No.", "*"),
                value = rollNumber,
                onValueChange = { rollNumber = it }
            )
            CustomCard(
                label = getSuperScriptText("Email", "*"),
                value = email,
                onValueChange = { email = it }
            )
            CustomCard(
                label = "Phone",
                value = phone,
                onValueChange = { phone = it }
            )
            CustomCard(
                label = "City",
                value = city,
                onValueChange = { city = it }
            )
            CustomCard(
                label = "State",
                value = state,
                onValueChange = { state = it }
            )
            CustomRadioGroupCard(
                label = "Gender",
                value = gender,
                onValueChange = { gender = it }
            )
            CustomDropDownCard(
                label = "Class",
                value = classOfStudent,
                onValueChange = { classOfStudent = it }
            )
            Button(
                onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar( "Submitting form, selected class ${classOfStudent.text} and gender is ${gender.text}")}
                }, modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .fillMaxWidth(), shape = RoundedCornerShape(22.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan,contentColor = Color.White),
                enabled = isDataValidated(name, rollNumber, email)
            ) {
                Text(text = "Submit", fontSize = 24.sp)
            }
        }
    }
}

fun getSuperScriptText(s: String, superScriptText: String): String {
    val superscript = SpanStyle(
        baselineShift = BaselineShift.Superscript,
        fontSize = 16.sp,
        color = Color.Red
    )
    return buildAnnotatedString {
        append(s)
        withStyle(superscript) {
            append(superScriptText)
        }
    }.text
}

private fun isDataValidated(
    name: TextFieldValue,
    rollNumber: TextFieldValue,
    email: TextFieldValue
): Boolean = !((name.text.isEmpty()) || (rollNumber.text.isEmpty()) || (email.text.isEmpty()))

@Composable
fun CustomColumn(
    label: String,
    value: TextFieldValue,
    onValueChange: (it: TextFieldValue) -> Unit
) {
    val keyboardOptions =
        if (label == "Phone") KeyboardOptions(keyboardType = KeyboardType.Phone)
        else KeyboardOptions(keyboardType = KeyboardType.Text)
    Column {
        Text("Hello world")
        Text(
            text = label,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(start=16.dp,top=16.dp, bottom = 4.dp)
        )
        TextField(
            value = value,
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 16.dp),
            keyboardOptions = keyboardOptions
        )
    }
}


@Composable
fun CustomColumnOutlined(
    label: String,
    value: TextFieldValue,
    onValueChange: (it: TextFieldValue) -> Unit
) {
    val keyboardOptions =
        if (label == "Phone") KeyboardOptions(keyboardType = KeyboardType.Phone)
        else KeyboardOptions(keyboardType = KeyboardType.Text)
    Column {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(start=16.dp,top=16.dp)
        )
        OutlinedTextField(
            value = value,
            label = { Text(text = label)},
            onValueChange = { onValueChange(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp, 16.dp, 16.dp),
            keyboardOptions = keyboardOptions
        )
    }
}
@Composable
fun TextBox(label: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    listOf(Color.Red, Color.Magenta)
                )
            )
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(color = Color.Green, fontSize = 50.sp,)
                ) {
                    append("J")
                }
                append("etpack")
                withStyle(
                    style = SpanStyle(color = Color.Cyan, fontSize = 50.sp,textDecoration = TextDecoration.None)
                ) {
                    append(" F")
                }
                append("orm")
            },
            modifier= Modifier
                .padding(start = 16.dp)
                .align(Alignment.Center),
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun CustomCard(label: String, value: TextFieldValue, onValueChange: (it: TextFieldValue) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color(0xFFFFA867.toInt())
    ) {
        if (label != "State")
            CustomColumn(label = label, value = value, onValueChange = onValueChange)
        else CustomColumnOutlined(label = label, value = value, onValueChange = onValueChange)
    }
}

@Composable
fun CustomRadioGroupCard(label: String, value: TextFieldValue, onValueChange: (it: TextFieldValue) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color(0xFFFFA867.toInt())
    ) {
        DisplayGenderRadioGroup(label = label, value = value, onValueChange = onValueChange)
    }
}

@Composable
fun DisplayGenderRadioGroup(label: String, value: TextFieldValue, onValueChange: (it: TextFieldValue) -> Unit) {
    Column {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 15.sp,
            modifier = Modifier.padding(start=16.dp,top=16.dp)
        )
        Row {
            RadioButton(selected = value.text == "Male",
                onClick = { onValueChange(TextFieldValue("Male")) }
            )
            Text(
                text = "Male",
                modifier = Modifier
                    .clickable(onClick = { onValueChange(TextFieldValue("Male")) })
                    .padding(start = 4.dp, top = 12.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            RadioButton(selected = value.text == "Female",
                onClick = { onValueChange(TextFieldValue("Female")) })
            Text(
                text = "Female",
                modifier = Modifier
                    .clickable(onClick = { onValueChange(TextFieldValue("Female")) })
                    .padding(start = 4.dp, top = 12.dp)
            )
        }
    }
}

@Composable
fun CustomDropDownCard(label: String, value: TextFieldValue, onValueChange: (it: TextFieldValue) -> Unit) {
    Card(
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = Color(0xFFFFA867.toInt())
    ) {
        DropDownMenu(label = label, value = value, onValueChange = onValueChange)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropDownMenu(
    label: String,
    value: TextFieldValue,
    onValueChange: (it: TextFieldValue) -> Unit
) {
    val options = listOf("IX", "X", "XI", "XII")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }
    onValueChange(TextFieldValue("IX"))
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = label,
            color = Color.Black,
            fontSize = 15.sp,
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                            onValueChange(TextFieldValue(selectionOption))
                        }
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }
}
