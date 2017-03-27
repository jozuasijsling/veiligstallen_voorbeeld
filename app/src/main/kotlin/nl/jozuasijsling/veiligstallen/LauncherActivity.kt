package nl.jozuasijsling.veiligstallen

import android.app.Activity
import android.os.Bundle


class LauncherActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_screen)
    }

    override fun onBackPressed() {

        super.onBackPressed()
    }
}
