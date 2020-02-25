/*
 * BSD 3-Clause License
 *
 * Copyright 2020  Sage Bionetworks. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1.  Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * 2.  Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * 3.  Neither the name of the copyright holder(s) nor the names of any contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission. No license is granted to the trademarks of
 * the copyright holders even if such marks are included in this software.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package edu.northwestern.mobiletoolbox.flanker_app.jni;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.readdle.codegen.anotation.SwiftFunc;
import com.readdle.codegen.anotation.SwiftGetter;
import com.readdle.codegen.anotation.SwiftValue;

import java.util.ArrayList;

import edu.northwestern.mobiletoolbox.flanker_app.flanker.step.form.FlankerFormStep;

//@SwiftValue
//public class FlankerStep {
//
//    @NonNull
//    private String identifier;
//
//    @NonNull
//    @SwiftGetter("identifier")
//    public native String getIdentifier();
//
//    @Nullable
//    private String stepName;
//
//    @Nullable
//    @SwiftGetter("stepName")
//    public native String getStepName();
//
//    @Nullable
//    private String nextStepIdentifier;
//
//    @Nullable
//    @SwiftGetter("nextStepIdentifier")
//    public native String getNextStepIdentifier();
//
//    @Nullable
//    private ArrayList<FlankerStepBranchingRule> branchingNavigationRules;
//
//    @Nullable
//    @SwiftGetter("branchingNavigationRules")
//    public native ArrayList<FlankerStepBranchingRule> getBranchingNavigationRules();
//
//    @Nullable
//    private FlankerStepGroup stepGroup;
//
//    @Nullable
//    @SwiftGetter("stepGroup")
//    public native FlankerStepGroup getStepGroup();
//
//    @Nullable
//    private String stepBackTo;
//
//    @Nullable
//    @SwiftGetter("stepBackTo")
//    public native String getStepBackTo();
//
//    @NonNull
//    @SwiftFunc("init(identifier:stepName:nextStepIdentifier:branchingNavigationRules:stepGroup:stepBackTo:)")
//    public static native FlankerStep init(
//        @NonNull String identifier,
//        @Nullable String stepName,
//        @Nullable String nextStepIdentifier,
//        @Nullable ArrayList<FlankerStepBranchingRule> branchingNavigationRules,
//        @Nullable FlankerStepGroup stepGroup,
//        @Nullable String stepBackTo
//    );
//
//    public static FlankerStep fromFormStep(@NonNull FlankerFormStep formStep) {
//        return init(
//                formStep.getIdentifier(),
//                formStep.getStepName(),
//                formStep.getNextStepIdentifier(),
//                new ArrayList(formStep.getBranchingNavigationRules()),
//                formStep.getStepGroup(),
//                formStep.getStepBackTo()
//                );
//    }
//
//    private FlankerStep() {}
////
////    private long nativePointer = 0L;
////    private native void release();
//}
