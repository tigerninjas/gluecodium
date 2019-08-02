/*
 * Copyright (C) 2016-2019 HERE Europe B.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 * License-Filename: LICENSE
 */

package com.here.genium.model.lime

interface LimeReferenceResolver {
    val referenceMap: Map<String, LimeElement>
    fun registerElement(element: LimeNamedElement)
    fun registerElement(key: String, element: LimeElement)

    companion object {
        fun getArrayKey(elementTypeKey: String) = "[$elementTypeKey]"

        fun getMapKey(keyTypeKey: String, valueTypeKey: String) = "[$keyTypeKey:$valueTypeKey]"

        fun getSetKey(elementTypeKey: String) = getMapKey(elementTypeKey, "")
    }
}