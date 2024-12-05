import androidx.navigation.NavController

object Navigation {

    fun navigateToDetail(navController: NavController, itemId: String) {
        navController.navigate("detail/$itemId")
    }
}