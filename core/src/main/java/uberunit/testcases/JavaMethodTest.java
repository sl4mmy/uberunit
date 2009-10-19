/*
 * Copyright (c) 2009, Kent R. Spillner <kspillner@acm.org>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */
package uberunit.testcases;

import uberunit.Test;

import java.lang.reflect.Method;

/**
 * Understands how to verify code correctness by sending messages to
 * objects via reflection.
 */
public class JavaMethodTest implements Test {

        private final Object testClassInstance;

        private final Method testMethod;

        private final Object[] arguments;

        private final boolean ignored;

        private final boolean parallelizable;

        public JavaMethodTest(final Object testClassInstance,
            final Method testMethod, final Object[] arguments,
            final boolean ignored, final boolean parallelizable) {
                this.testClassInstance = testClassInstance;
                this.testMethod = testMethod;
                this.arguments = arguments;
                this.ignored = ignored;
                this.parallelizable = parallelizable;
        }

        public boolean isIgnored() {
                return ignored;
        }

        public boolean isParallelizable() {
                return parallelizable;
        }

        public void test() throws Exception {
                testMethod.invoke(testClassInstance, arguments);
        }
}
