/*
 * Copyright (c) 2020 codeproton-projects
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.king.module.provider.module.info;

import lombok.Getter;
import lombok.NonNull;

/**
 * This class represents the module configuration
 * Containing a description, author, version, and dependencies.
 *
 * @author zkingboos
 * @since 1.0.0
 */
@Getter
public abstract class Configuration {

    private final String name;
    private final String version;
    private final String author;
    private final String website;

    public Configuration(String name, String version, String author) {
        this(name, version, author, null);
    }

    public Configuration(String name, String author) {
        this(name, "Beta+V", author, null);
    }

    public Configuration(@NonNull String name, @NonNull String version, @NonNull String author, String website) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.website = website;
    }
}
