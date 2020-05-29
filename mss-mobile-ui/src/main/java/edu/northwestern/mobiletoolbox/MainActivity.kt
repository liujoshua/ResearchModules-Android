/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import kotlinx.android.synthetic.main.activity_container.crf_task_container
import org.sagebionetworks.assessmentmodel.presentation.AssessmentActivity
import org.sagebionetworks.assessmentmodel.presentation.AssessmentFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        addAssessment("Flanker - Blue", "flanker_inhibitory_control", "flanker_inhibitory_control", "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
        addAssessment("MFS", "mfs_pilot_1a", "mfs_pilot_1a", "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
        addAssessment("Number Match", "number_match", "number_match", "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)
        addAssessment("DCCS", "dimensional_change_card_sort", "dimensional_change_card_sort", "edu.northwestern.mobiletoolbox", R.style.BlueberryTheme)

    }

    private fun addAssessment(title: String, assessmentId: String, resourceName: String, packageName: String, theme: Int = -1) {
        val layout = layoutInflater.inflate(R.layout.assessment_row, null)
        layout?.findViewById<TextView>(R.id.task_name)?.text = title
        layout?.findViewById<Button>(R.id.button_start_task)?.setOnClickListener {
            val intent = Intent(this, MtbAssessmentActivity::class.java)
            intent.putExtra(AssessmentFragment.ARG_ASSESSMENT_ID_KEY, assessmentId)
            intent.putExtra(AssessmentFragment.ARG_RESOURCE_NAME, resourceName)
            intent.putExtra(AssessmentFragment.ARG_PACKAGE_NAME, this.packageName)
            if (theme > 0) {
                intent.putExtra(AssessmentActivity.ARG_THEME, theme)
            }
            startActivityForResult(intent, REQUEST_CODE_ASSESSMENT)
        }
        crf_task_container.addView(layout)
    }

    companion object {
        const val REQUEST_CODE_ASSESSMENT = 1;
    }
}
