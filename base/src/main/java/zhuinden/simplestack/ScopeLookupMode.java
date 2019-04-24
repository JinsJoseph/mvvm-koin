/*
 * Copyright 2019 Gabor Varadi
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
 */
package zhuinden.simplestack;

/**
 * Specifies the mode used for looking up services within the scopes.
 * <p>
 * Allows customizing whether both implicit and explicit parents are used, or only explicit parents.
 */
public enum ScopeLookupMode {
    ALL,
    EXPLICIT
}
