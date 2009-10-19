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
package uberunit.teardowns;

import uberunit.TearDown;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Understands how to clean up after tests by sending messages to
 * objects via reflection.
 */
public class JavaMethodTearDown implements TearDown {

        private final List<TearDown> children;

        private final Object testClassInstance;

        private final Method tearDownMethod;

        private final Object[] arguments;

        public JavaMethodTearDown(final Object testClassInstance,
            final Method tearDownMethod, final Object[] arguments) {
                this.testClassInstance = testClassInstance;
                this.tearDownMethod = tearDownMethod;
                this.arguments = arguments;

                this.children = new ArrayList<TearDown>();
        }

        public JavaMethodTearDown(final TearDown parent,
            final Object testClassInstance, final Method tearDownMethod,
            final Object[] arguments) {
                this(testClassInstance, tearDownMethod, arguments);

                parent.addChild(this);
        }

        public void tearDown() throws Exception {
                tearDownMethod.invoke(testClassInstance, arguments);

                for (TearDown child : children) {
                        child.tearDown();
                }
        }

        public boolean addChild(final TearDown child) {
                return children.add(child);
        }
}
