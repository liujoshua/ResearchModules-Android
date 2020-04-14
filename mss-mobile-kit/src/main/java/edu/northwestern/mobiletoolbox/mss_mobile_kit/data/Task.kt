/*
 * Copyright © 2020 Northwestern University. All rights reserved.
 */

package edu.northwestern.mobiletoolbox.mss_mobile_kit.data

import android.os.Parcelable
import com.google.common.collect.ImmutableList
import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.ScreenOrientation
import edu.northwestern.mobiletoolbox.mss_mobile_kit.enums.ScreenOrientation.portrait
import kotlinx.android.parcel.Parcelize
import org.sagebionetworks.research.domain.async.AsyncActionConfiguration
import org.sagebionetworks.research.domain.step.interfaces.Step
import org.sagebionetworks.research.domain.step.ui.action.Action
import org.sagebionetworks.research.domain.task.Task as RSDTask

@Parcelize
data class Task(
        var taskOrientation: ScreenOrientation = portrait,
        var interruptionManager : InterruptionManager,
        private var identifier : String,
        private var steps : ImmutableList<Step>,
        private  var actions : ImmutableMap<String, Action>,
        private  var asyncActions : ImmutableSet<AsyncActionConfiguration>,
        private  var hiddenActions : ImmutableSet<String>,
        private  var progressMarkers : ImmutableList<String>
) : RSDTask, Parcelable {

    override fun getIdentifier(): String {
        return identifier
    }

    override fun copyWithSteps(steps: MutableList<Step>?): Task {
        return copyWithSteps(steps)
    }

    override fun getSteps(): ImmutableList<Step> {
        return steps
    }

    override fun getActions(): ImmutableMap<String, Action> {
        return actions
    }

    override fun getAsyncActions(): ImmutableSet<AsyncActionConfiguration> {
        return asyncActions
    }

    override fun getHiddenActions(): ImmutableSet<String> {
        return hiddenActions
    }

    override fun copyWithAsyncActions(asyncActions: MutableSet<AsyncActionConfiguration>?): Task {
        return copyWithAsyncActions(asyncActions)
    }

    override fun getProgressMarkers(): ImmutableList<String> {
        return progressMarkers
    }
}