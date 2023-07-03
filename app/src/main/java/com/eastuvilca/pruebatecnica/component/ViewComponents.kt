package com.eastuvilca.pruebatecnica.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eastuvilca.pruebatecnica.R
import com.eastuvilca.pruebatecnica.ui.login.LoginViewModel

@Composable
fun TitleView(text: String){
    Spacer(modifier = Modifier.height(220.dp))
    Text(
        text = text,
        color = Color.White,
        fontSize = MaterialTheme.typography.h4.fontSize,
        fontFamily = MaterialTheme.typography.h3.fontFamily,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun CustomButtonApp(label: String, enabled: Boolean, onClick: () -> Unit){
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        enabled = enabled
    ) {
        Text(
            text = label,
            fontSize = MaterialTheme.typography.body1.fontSize
        )
    }
}

@Composable
fun PasswordFieldCustom(label: String, value: String, onValueChange: (String) -> Unit, modelBase: BaseViewModel){
    val isPasswordVisible: Boolean by modelBase.isPasswordVisible.observeAsState(false)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.White,
        ),
        label = { Text(text = label) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = if (isPasswordVisible) android.R.drawable.ic_lock_idle_lock else android.R.drawable.ic_menu_view),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
                    .clickable {
                        modelBase.onClickViewPassword()
                    },
                tint = MaterialTheme.colors.primary
            )
        },
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun CustomButtonSocialMedia(label: String, icon: Int, onClick: () -> Unit){
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = Color.DarkGray,
            contentDescription = "",
            modifier = Modifier.clip(CircleShape)
        )
        Text(
            text = label,
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun CustomText(text: String){
    Text(
        text = text,
        color = Color.White
    )
}

@Composable
fun CustomTextSmall(text: String){
    Text(
        text = text,
        color = Color.White,
        fontSize = MaterialTheme.typography.subtitle2.fontSize
    )
}

@Composable
fun CustomTextBold(text: String){
    Text(
        text = text,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun TextClickable(text: String, onClick: () -> Unit){
    Text(
        text = text,
        modifier = Modifier.clickable { onClick() },
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun TextClickableSmall(text: String, onClick: () -> Unit){
    Text(
        text = text,
        modifier = Modifier.clickable { onClick() },
        color = MaterialTheme.colors.primary,
        fontSize = MaterialTheme.typography.subtitle2.fontSize
    )
}

@Composable
fun CustomSeparator(){
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
fun DataTemplateUser(viewModel: LoginViewModel){
    Row(){
        CustomViewImageAvatar(viewModel)
        Column {
            CustomSeparator()
            CustomText(text = viewModel.getNombre())
            CustomTextBold(text = viewModel.getEmail())
        }
    }
}

@Composable
fun CustomViewImageAvatar(viewModel: LoginViewModel){
    Image(
        painter = painterResource(R.drawable.baseline_person_3_24),
        contentDescription = viewModel.getAvatar(),
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
    )
}