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

import com.readdle.codegen.anotation.SwiftDelegate;
import com.readdle.codegen.anotation.SwiftGetter;
import com.readdle.codegen.anotation.SwiftSetter;

//@SwiftDelegate(protocols = {"FlankerStepResultProtocol"})
//public abstract class FlankerStepResultBase<ResponseType, ScoreType> {
//
//    @NonNull
//    private String identifier;
//
//    @NonNull
//    @SwiftGetter("identifier")
//    public native String getIdentifier();
//
//    @NonNull
//    private Boolean practice;
//
//    @NonNull
//    @SwiftGetter("practice")
//    public native Boolean getPractice();
//
//    @Nullable
//    private Double responseTime;
//
//    @Nullable
//    @SwiftGetter("responseTime")
//    public native Double getResponseTime();
//
//    @Nullable
//    private ResponseType response;
//
//    @Nullable
//    @SwiftGetter("response")
//    public native ResponseType getResponse();
//
//    @SwiftSetter("response")
//    public native void setResponse(@Nullable ResponseType responseType);
//
//    @Nullable
//    private ScoreType score;
//
//    @Nullable
//    @SwiftGetter("score")
//    public native ScoreType getScore();
//
//    @SwiftSetter("score")
//    public native void setScore(@Nullable ScoreType scoreType);
//
//    @Nullable
//    private Integer anticipationError;
//
//    @Nullable
//    @SwiftGetter("anticipationError")
//    public native Integer getAnticipationError();
//
//    @NonNull
//    private Boolean instruction;
//
//    @NonNull
//    @SwiftGetter("instruction")
//    public native Boolean getInstruction();
//
//    private long nativePointer = 0L;
//    public native void init();
//    public native void release();
//
//    public FlankerStepResultBase() {
//        init();
//    }
//}
