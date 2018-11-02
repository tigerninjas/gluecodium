/*
 * Copyright (c) 2016-2018 HERE Europe B.V.
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

package com.here.genium.model.java

import org.junit.Assert.assertEquals

import java.util.Arrays
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class JavaPackageTest {
    private val javaPackage = JavaPackage(PACKAGE_LIST)

    @Test
    fun createChildPackageWithNullList() {
        val childPackage = javaPackage.createChildPackage(null)

        assertEquals(PACKAGE_LIST, childPackage.packageNames)
    }

    @Test
    fun createChildPackageWithEmptyList() {
        val childPackage = javaPackage.createChildPackage(emptyList())

        assertEquals(PACKAGE_LIST, childPackage.packageNames)
    }

    @Test
    fun createChildPackageWithNonEmptyList() {
        val childPackage = javaPackage.createChildPackage(listOf("boom"))

        assertEquals(Arrays.asList("com", "example", "test", "boom"), childPackage.packageNames)
    }

    companion object {
        private val PACKAGE_LIST = Arrays.asList("com", "example", "test")
    }
}
