package id.my.sendiko.fintrack.core.presentation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Translates a server category icon key into a Material Design [ImageVector].
 * Defaults to [Icons.Rounded.Category] if the key is null or unrecognized.
 */
fun String?.toCategoryImageVector(): ImageVector {
    return when (this?.lowercase()?.trim()) {
        // Food & Drink
        "restaurant" -> Icons.Rounded.Restaurant
        "local_cafe" -> Icons.Rounded.LocalCafe
        "local_bar" -> Icons.Rounded.LocalBar
        "fastfood" -> Icons.Rounded.Fastfood
        "shopping_cart" -> Icons.Rounded.ShoppingCart

        // Transportation
        "directions_car" -> Icons.Rounded.DirectionsCar
        "directions_bus" -> Icons.Rounded.DirectionsBus
        "local_taxi" -> Icons.Rounded.LocalTaxi
        "flight" -> Icons.Rounded.Flight
        "two_wheeler" -> Icons.Rounded.TwoWheeler

        // Housing & Utilities
        "home" -> Icons.Rounded.Home
        "bolt" -> Icons.Rounded.Bolt
        "water_drop" -> Icons.Rounded.WaterDrop
        "wifi" -> Icons.Rounded.Wifi
        "phone_android" -> Icons.Rounded.PhoneAndroid
        "build" -> Icons.Rounded.Build

        // Shopping & Lifestyle
        "shopping_bag" -> Icons.Rounded.ShoppingBag
        "devices" -> Icons.Rounded.Devices
        "chair" -> Icons.Rounded.Chair
        "card_giftcard" -> Icons.Rounded.CardGiftcard
        "pets" -> Icons.Rounded.Pets

        // Health & Wellness
        "medical_services" -> Icons.Rounded.MedicalServices
        "medication" -> Icons.Rounded.Medication
        "fitness_center" -> Icons.Rounded.FitnessCenter
        "spa" -> Icons.Rounded.Spa

        // Entertainment & Leisure
        "movie" -> Icons.Rounded.Movie
        "sports_esports" -> Icons.Rounded.SportsEsports
        "music_note" -> Icons.Rounded.MusicNote
        "luggage" -> Icons.Rounded.Luggage

        // Family & Education
        "school" -> Icons.Rounded.School
        "child_care" -> Icons.Rounded.ChildCare

        // Finance & Income
        "payments" -> Icons.Rounded.Payments
        "work" -> Icons.Rounded.Work
        "trending_up" -> Icons.Rounded.TrendingUp
        "savings" -> Icons.Rounded.Savings
        "account_balance" -> Icons.Rounded.AccountBalance
        "credit_card" -> Icons.Rounded.CreditCard
        "redeem" -> Icons.Rounded.Redeem
        "swap_horiz" -> Icons.Rounded.SwapHoriz

        // Fallback
        else -> Icons.Rounded.Category
    }
}

/**
 * Reusable Compose component that renders the appropriate Material Icon
 * for a given server category key.
 */
@Composable
fun CategoryIcon(
    iconKey: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = LocalContentColor.current
) {
    Icon(
        imageVector = iconKey.toCategoryImageVector(),
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}