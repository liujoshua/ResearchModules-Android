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

import com.readdle.codegen.anotation.SwiftCallbackFunc;
import com.readdle.codegen.anotation.SwiftDelegate;
import com.readdle.codegen.anotation.SwiftGetter;

import java.util.ArrayList;

//@SwiftDelegate(protocols = {"FlankerStepProtocol"})
//public abstract class FlankerStepBase {
//
//    @NonNull
//    public String identifier;
//
//    @NonNull
//    @SwiftGetter("identifier")
//    public native String getIdentifier();
//
//    @Nullable
//    protected String stepName;
//
//    @Nullable
//    @SwiftGetter("stepName")
//    public native String getStepName();
//
//    @Nullable
//    protected String nextStepIdentifier;
//
//    @Nullable
//    @SwiftGetter("nextStepIdentifier")
//    public native String getNextStepIdentifier();
//
//    @Nullable
//    protected ArrayList<FlankerStepBranchingRule> branchingNavigationRules;
//
//    @Nullable
//    @SwiftGetter("branchingNavigationRules")
//    public native ArrayList<FlankerStepBranchingRule> getBranchingNavigationRules();
//
//    @Nullable
//    protected FlankerStepGroup stepGroup;
//
//    @Nullable
//    @SwiftGetter("stepGroup")
//    public native FlankerStepGroup getStepGroup();
//
//    @Nullable
//    protected String stepBackTo;
//
//    @Nullable
//    @SwiftGetter("stepBackTo")
//    public native String getStepBackTo();
//
//    private long nativePointer = 0L;
//    public native void init();
//    public native void release();
//
//    public FlankerStepBase() {
//        init();
//    }

//}
