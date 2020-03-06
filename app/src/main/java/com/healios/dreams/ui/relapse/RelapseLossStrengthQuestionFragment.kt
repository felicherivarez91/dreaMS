package com.healios.dreams.ui.relapse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.healios.dreams.R
import kotlinx.android.synthetic.main.fragment_relapse_loss_strength_question.*

class RelapseLossStrengthQuestionFragment : Fragment() {

    companion object {
        fun newInstance() = RelapseReportFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).setSupportActionBar(toolbar_top)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.back_arrow -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_relapse_loss_strength_question, container, false)
    }

}
