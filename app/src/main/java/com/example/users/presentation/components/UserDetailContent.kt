package com.example.users.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.users.domain.model.Location
import com.example.users.domain.model.User

@Composable
fun UserDetailContent(
    user: User,
    modifier: Modifier = Modifier,
    onEmailClick: (String) -> Unit,
    onPhoneClick: (String) -> Unit,
    onAddressClick: (Location) -> Unit
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = user.picture.large,
            contentDescription = "User photo",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 24.dp)
        )

        InfoItem(
            label = "Full Name",
            value = "${user.title}. ${user.firstName} ${user.lastName}"
        )

        InfoItem(
            label = "Email",
            value = user.email,
            onClick = { onEmailClick(user.email) }
        )

        InfoItem(
            label = "Phone",
            value = user.phone,
            onClick = { onPhoneClick(user.phone) }
        )

        InfoItem(
            label = "Cell",
            value = user.cell,
            onClick = { onPhoneClick(user.cell) }
        )

        InfoItem(
            label = "Gender",
            value = user.gender.replaceFirstChar { it.uppercase() }
        )

        InfoItem(
            label = "Address",
            value = "${user.location.street.number} ${user.location.street.name}, " +
                    "${user.location.city}, ${user.location.state}, ${user.location.country}",
            onClick = { onAddressClick(user.location) }
        )
    }
}