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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.readdle.codegen.anotation.SwiftFunc;
import com.readdle.codegen.anotation.SwiftGetter;
import com.readdle.codegen.anotation.SwiftValue;

import java.lang.reflect.Type;

@SwiftValue
public class FlankerBranchingCriteria {
    @Nullable
    private String description;

    @Nullable
    @SwiftGetter("description")
    public native String getDescription();

    @Nullable
    private FlankerCriteriaName name;

    @Nullable
    @SwiftGetter("name")
    public native FlankerCriteriaName getName();

    @Nullable
    private FlankerCriteriaType type;

    @Nullable
    @SwiftGetter("type")
    public native FlankerCriteriaType getType();

    @Nullable
    private Integer duration;

    @Nullable
    @SwiftGetter("duration")
    public native Integer getDuration();

    @NonNull
    @SwiftFunc("init(description:name:type:duration:)")
    public native static FlankerBranchingCriteria init(
            @Nullable String description,
            @Nullable FlankerCriteriaName name,
            @Nullable FlankerCriteriaType type,
            @Nullable Integer duration
    );



    private FlankerBranchingCriteria() {}

//    public static JsonDeserializer<FlankerBranchingCriteria> deserializer = new JsonDeserializer<FlankerBranchingCriteria>() {
//        @Override
//        public FlankerBranchingCriteria deserialize(final JsonElement json, final Type typeOfT,
//                final JsonDeserializationContext context) throws JsonParseException {
//            JsonObject jsonObject = json.getAsJsonObject();
//
//
//            FlankerCriteriaName criteriaName = context.deserialize(jsonObject.get("name"), FlankerCriteriaName.class);
//            FlankerCriteriaType criteriaType = context.deserialize(jsonObject.get("type"), FlankerCriteriaType.class);
//
//            FlankerBranchingCriteria criteria = FlankerBranchingCriteria();
//            criteria.description = jsonObject.get("description").getAsString();
//            criter
//
//
//            return FlankerBranchingCriteria.init(
//                    jsonObject.get("description").getAsString(),
//                    criteriaName,
//                    criteriaType,
//                    new Integer(jsonObject.get("duration").getAsInt())
//            );
//        }
//    };
}
